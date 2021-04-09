package com.github.johnnysc.picsandlogintestapp.ui.login

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.johnnysc.picsandlogintestapp.R
import com.github.johnnysc.picsandlogintestapp.core.SimpleTextChangeListener
import com.github.johnnysc.picsandlogintestapp.core.load
import com.github.johnnysc.picsandlogintestapp.domain.login.WeatherItem
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * Экран для логина и пароля
 *
 * @author Asatryan on 31.03.21
 */
class LoginFragment : Fragment() {

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        val logoImageView: ImageView = root.findViewById(R.id.logoImageView)
        val emailTextInputLayout: TextInputLayout = root.findViewById(R.id.emailAddressTextInputLayout)
        val passwordTextInputLayout: TextInputLayout = root.findViewById(R.id.passwordTextInputLayout)
        val emailEditText: TextInputEditText = root.findViewById(R.id.emailAddressEditText)
        val passwordEditText: TextInputEditText = root.findViewById(R.id.passwordEditText)
        val loginButton: View = root.findViewById(R.id.loginButton)
        val progressBar: View = root.findViewById(R.id.progressBar)
        progressBar.bringToFront()

        logoImageView.load(getString(R.string.logo_url))

        val emailTextWatcher = object : SimpleTextChangeListener() {
            override fun afterTextChanged(s: Editable?) {
                if (emailTextInputLayout.isErrorEnabled)
                    loginViewModel.clearEmailError()
            }
        }
        emailEditText.addTextChangedListener(emailTextWatcher)

        val passwordTextWatcher = object : SimpleTextChangeListener() {
            override fun afterTextChanged(s: Editable?) {
                if (passwordTextInputLayout.isErrorEnabled)
                    loginViewModel.clearPasswordError()
            }
        }
        passwordEditText.addTextChangedListener(passwordTextWatcher)

        loginButton.setOnClickListener {
            loginViewModel.login(emailEditText.text.toString(), passwordEditText.text.toString())
        }

        loginViewModel.emailState.observe(viewLifecycleOwner, Observer {
            emailTextInputLayout.isErrorEnabled = it.containsError
            emailTextInputLayout.error = it.errorMessage
        })

        loginViewModel.passwordState.observe(viewLifecycleOwner, Observer {
            passwordTextInputLayout.isErrorEnabled = it.containsError
            passwordTextInputLayout.error = it.errorMessage
        })

        loginViewModel.progressState.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
            loginButton.isEnabled = !it
        })

        loginViewModel.messageState.observe(viewLifecycleOwner, Observer {
            Snackbar.make(progressBar, it, Snackbar.LENGTH_SHORT).show()
        })

        return root
    }
}