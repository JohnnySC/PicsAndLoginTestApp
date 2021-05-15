package com.github.johnnysc.picsandlogintestapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.johnnysc.picsandlogintestapp.R
import com.github.johnnysc.picsandlogintestapp.core.MySnackbar
import com.github.johnnysc.picsandlogintestapp.core.listenChanges
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
        binding.emailAddressEditText.listenChanges {
            loginViewModel.clearEmailError(binding.emailAddressTextInputLayout)
        }
        binding.passwordEditText.listenChanges {
            loginViewModel.clearPasswordError(binding.passwordTextInputLayout)
        }
        binding.loginButton.setOnClickListener {
            loginViewModel.login(
                binding.emailAddressEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
        loginViewModel.emailState.observe(viewLifecycleOwner, Observer {
            it.show(binding.emailAddressTextInputLayout)
        })
        loginViewModel.passwordState.observe(viewLifecycleOwner, Observer {
            it.show(binding.passwordTextInputLayout)
        })
        loginViewModel.progressState.observe(viewLifecycleOwner, Observer {
            it.apply(binding.progressBar, binding.loginButton)
        })
        loginViewModel.messageState.observe(viewLifecycleOwner, Observer {
            it.show(MySnackbar(Snackbar.make(binding.progressBar, "", Snackbar.LENGTH_SHORT)))
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}