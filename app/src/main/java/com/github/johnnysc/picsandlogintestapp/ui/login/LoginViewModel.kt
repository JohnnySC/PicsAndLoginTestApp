package com.github.johnnysc.picsandlogintestapp.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.picsandlogintestapp.R
import com.github.johnnysc.picsandlogintestapp.ThisApp
import com.github.johnnysc.picsandlogintestapp.domain.login.WeatherItem
import com.github.johnnysc.picsandlogintestapp.ui.login.validator.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Asatryan on 31.03.21
 */
class LoginViewModel(app: Application) : AndroidViewModel(app) {

    val emailState = MutableLiveData<InputState>()
    val passwordState = MutableLiveData<InputState>()
    val progressState = MutableLiveData<Boolean>()
    val messageState = MutableLiveData<String>()

    //region private fields
    private val passwordMinLength = 6
    private val emptinessValidator by lazy {
        EmptinessValidator(app.getString(R.string.empty_string_error_message))
    }
    private val emailValidators by lazy {
        listOf(
            emptinessValidator,
            EmailValidator(app.getString(R.string.invalid_email_error_message))
        )
    }
    private val passwordValidators by lazy {
        listOf(
            emptinessValidator,
            MinLengthValidator(
                app.getString(
                    R.string.invalid_min_length_error_message,
                    passwordMinLength
                ), passwordMinLength
            ),
            PasswordValidator(app.getString(R.string.invalid_password_error_message))
        )
    }
    private val interactor by lazy {
        (app as ThisApp).loginInstanceProvider.provideLoginInteractor()
    }
    private val mapper = (app as ThisApp).loginInstanceProvider.provideWeatherUiMapper()
    //endregion

    fun login(email: String, password: String) {
        var success = true
        for (validator in emailValidators) {
            if (!validator.isValid(email)) {
                emailState.value = InputState(true, validator.errorMessage)
                success = false
                break
            }
        }
        for (validator in passwordValidators) {
            if (!validator.isValid(password)) {
                passwordState.value = InputState(true, validator.errorMessage)
                success = false
                break
            }
        }
        if (success) {
            progressState.value = true
            viewModelScope.launch(Dispatchers.IO) {
                val result = interactor.login()
                messageState.postValue(mapper.map(result).description)
                progressState.postValue(false)
            }
        }
    }

    fun clearEmailError() {
        emailState.value = InputState()
    }

    fun clearPasswordError() {
        passwordState.value = InputState()
    }
}

data class InputState(
    val containsError: Boolean = false,
    val errorMessage: String = ""
)