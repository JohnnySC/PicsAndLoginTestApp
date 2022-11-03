package com.github.johnnysc.picsandlogintestapp.ui.login

import androidx.lifecycle.*
import com.github.johnnysc.picsandlogintestapp.core.MySnackbar
import com.github.johnnysc.picsandlogintestapp.core.UiValidator
import com.github.johnnysc.picsandlogintestapp.databinding.FragmentLoginBinding
import com.github.johnnysc.picsandlogintestapp.domain.login.LoginInteractor
import com.github.johnnysc.picsandlogintestapp.domain.login.WeatherUiMapper
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Asatryan on 03.11.2022
 */
class LoginViewModel(
    private val communication: LoginCommunication,
    private val interactor: LoginInteractor,
    private val mapper: WeatherUiMapper<WeatherUiModel>,
    private val validateEmail: UiValidator,
    private val validatePassword: UiValidator,
    private val dispatchers: DispatchersList
) : ViewModel(), Observe {

    fun login(email: String, password: String) {
        if (validateEmail.isValid(email) && validatePassword.isValid(password)) {
            communication.map(LoginState.Progress)
            viewModelScope.launch(dispatchers.io()) {
                val result = interactor.login()
                withContext(dispatchers.ui()) {
                    result.map(mapper).map(communication)
                }
            }
        } else if (!validateEmail.isValid(email) && !validatePassword.isValid(password))
            communication.map(
                LoginState.TwoErrors(
                    validateEmail.errorMessage(),
                    validatePassword.errorMessage()
                )
            )
        else if (validateEmail.isValid(email) && !validatePassword.isValid(password))
            communication.map(LoginState.PasswordError(validatePassword.errorMessage()))
        else
            communication.map(LoginState.LoginError(validateEmail.errorMessage()))
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<LoginState>) {
        communication.observe(owner, observer)
    }
}

interface DispatchersList {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher

    class Base : DispatchersList {
        override fun io(): CoroutineDispatcher = Dispatchers.IO
        override fun ui(): CoroutineDispatcher = Dispatchers.Main
    }
}

interface Observe {
    fun observe(owner: LifecycleOwner, observer: Observer<LoginState>)
}

interface LoginCommunication : Observe {
    fun map(source: LoginState)

    class Base(
        private val liveData: MutableLiveData<LoginState> = MutableLiveData()
    ) : LoginCommunication {

        override fun map(source: LoginState) {
            liveData.value = source
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<LoginState>) {
            liveData.observe(owner, observer)
        }
    }
}

sealed class LoginState {
    abstract fun handle(binding: FragmentLoginBinding)

    object Progress : LoginState() {
        override fun handle(binding: FragmentLoginBinding) {
            binding.progressBar.show(false)
        }
    }

    data class TwoErrors(
        private val loginError: String,
        private val passwordError: String
    ) : LoginState() {

        override fun handle(binding: FragmentLoginBinding) = with(binding) {
            emailAddressTextInputLayout.show(true, loginError)
            passwordTextInputLayout.show(true, passwordError)
        }
    }

    data class LoginError(private val value: String) : LoginState() {
        override fun handle(binding: FragmentLoginBinding) = with(binding) {
            emailAddressTextInputLayout.show(true, value)
        }
    }

    data class PasswordError(private val value: String) : LoginState() {
        override fun handle(binding: FragmentLoginBinding) = with(binding) {
            passwordTextInputLayout.show(true, value)
        }
    }

    data class Success(private val value: WeatherUiModel) : LoginState() {
        override fun handle(binding: FragmentLoginBinding) = with(binding) {
            progressBar.show(false)
            value.show(MySnackbar(Snackbar.make(binding.progressBar, "", Snackbar.LENGTH_SHORT)))
        }
    }

    data class Error(private val value: WeatherUiModel) : LoginState() {
        override fun handle(binding: FragmentLoginBinding) = with(binding) {
            progressBar.show(false)
            value.show(MySnackbar(Snackbar.make(binding.progressBar, "", Snackbar.LENGTH_SHORT)))
        }
    }
}