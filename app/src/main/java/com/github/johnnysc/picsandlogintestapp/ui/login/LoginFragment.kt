package com.github.johnnysc.picsandlogintestapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.johnnysc.picsandlogintestapp.R
import com.github.johnnysc.picsandlogintestapp.ThisApp
import com.github.johnnysc.picsandlogintestapp.core.listenChanges
import com.github.johnnysc.picsandlogintestapp.core.load
import com.github.johnnysc.picsandlogintestapp.databinding.FragmentLoginBinding

/**
 * Экран для логина и пароля
 *
 * @author Asatryan on 31.03.21
 */
class LoginFragment : Fragment() {

    private val viewModel by lazy {
        (requireActivity().application as ThisApp).viewModel()
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.bringToFront()
        binding.logoImageView.load(getString(R.string.logo_url))

        viewModel.observe(this) {
            it.handle(binding)
        }
        binding.loginButton.setOnClickListener {
            viewModel.login(
                binding.emailAddressEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
        binding.emailAddressEditText.listenChanges {
            binding.emailAddressTextInputLayout.show(false, "")
        }
        binding.passwordEditText.listenChanges {
            binding.passwordTextInputLayout.show(false, "")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}