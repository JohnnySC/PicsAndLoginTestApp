package com.github.johnnysc.picsandlogintestapp.ui.login

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.johnnysc.picsandlogintestapp.R
import com.github.johnnysc.picsandlogintestapp.core.SimpleTextChangeListener
import com.github.johnnysc.picsandlogintestapp.core.load
import com.github.johnnysc.picsandlogintestapp.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

/**
 * Экран для логина и пароля
 *
 * @author Asatryan on 31.03.21
 */
class LoginFragment : Fragment() {

    private val loginViewModel by viewModels<LoginViewModel>()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.bringToFront()

        binding.logoImageView.load(getString(R.string.logo_url))

        val emailTextWatcher = object : SimpleTextChangeListener() {
            override fun afterTextChanged(s: Editable?) {
                if (binding.emailAddressTextInputLayout.isErrorEnabled)
                    loginViewModel.clearEmailError()
            }
        }
        binding.emailAddressEditText.addTextChangedListener(emailTextWatcher)

        val passwordTextWatcher = object : SimpleTextChangeListener() {
            override fun afterTextChanged(s: Editable?) {
                if (binding.passwordTextInputLayout.isErrorEnabled)
                    loginViewModel.clearPasswordError()
            }
        }
        binding.passwordEditText.addTextChangedListener(passwordTextWatcher)

        binding.loginButton.setOnClickListener {
            loginViewModel.login(
                binding.emailAddressEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }

        loginViewModel.emailState.observe(viewLifecycleOwner, Observer {
            binding.emailAddressTextInputLayout.isErrorEnabled = it.containsError
            binding.emailAddressTextInputLayout.error = it.errorMessage
        })

        loginViewModel.passwordState.observe(viewLifecycleOwner, Observer {
            binding.passwordTextInputLayout.isErrorEnabled = it.containsError
            binding.passwordTextInputLayout.error = it.errorMessage
        })

        loginViewModel.progressState.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            binding.loginButton.isEnabled = !it
        })

        loginViewModel.messageState.observe(viewLifecycleOwner, Observer {
            it.show(Snackbar.make(binding.progressBar, "", Snackbar.LENGTH_SHORT))
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}