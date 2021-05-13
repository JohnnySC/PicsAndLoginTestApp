package com.github.johnnysc.picsandlogintestapp.ui.login

import android.app.Application
import android.view.View
import android.widget.Button
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.picsandlogintestapp.R
import com.github.johnnysc.picsandlogintestapp.ThisApp
import com.github.johnnysc.picsandlogintestapp.core.UiValidatorChain
import com.github.johnnysc.picsandlogintestapp.ui.login.validator.*
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Asatryan on 31.03.21
 */
class LoginViewModel(app: Application) : AndroidViewModel(app) {

    private val _emailState = MutableLiveData<InputState>()
    val emailState: LiveData<InputState>
        get() = _emailState
    private val _passwordState = MutableLiveData<InputState>()
    val passwordState: LiveData<InputState>
        get() = _passwordState
    private val _progressState = MutableLiveData<ProgressState>()
    val progressState: LiveData<ProgressState>
        get() = _progressState
    private val _messageState = MutableLiveData<WeatherUiModel>()
    val messageState: LiveData<WeatherUiModel>
        get() = _messageState

    //region private fields
    private val passwordMinLength = 6
    private val emptinessValidator by lazy {
        EmptinessValidator(app.getString(R.string.empty_string_error_message))
    }
    private val emailValidators by lazy {
        UiValidatorChain(
            emptinessValidator,
            EmailValidator(app.getString(R.string.invalid_email_error_message))
        )
    }
    private val passwordValidators by lazy {
        UiValidatorChain(
            emptinessValidator,
            UiValidatorChain(
                MinLengthValidator(
                    app.getString(
                        R.string.invalid_min_length_error_message,
                        passwordMinLength
                    ), passwordMinLength
                ),
                PasswordValidator(app.getString(R.string.invalid_password_error_message))
            )
        )
    }
    private val interactor by lazy {
        (app as ThisApp).loginInstanceProvider.provideLoginInteractor()
    }
    private val mapper = (app as ThisApp).loginInstanceProvider.provideWeatherUiMapper()
    //endregion

    fun login(email: String, password: String) {
        var success = true
        if (!emailValidators.isValid(email)) {
            _emailState.value = InputState(true, emailValidators.errorMessage)
            success = false
        }
        if (!passwordValidators.isValid(password)) {
            _passwordState.value = InputState(true, passwordValidators.errorMessage)
            success = false
        }
        if (success) {
            _progressState.value = ProgressState(true)
            viewModelScope.launch(Dispatchers.IO) {
                val result = interactor.login()
                _messageState.postValue(result.map(mapper))
                _progressState.postValue(ProgressState(false))
            }
        }
    }

    fun clearEmailError(textInputLayout: TextInputLayout) {
        if (textInputLayout.isErrorEnabled)
            _emailState.value = InputState()
    }

    fun clearPasswordError(textInputLayout: TextInputLayout) {
        if (textInputLayout.isErrorEnabled)
            _passwordState.value = InputState()
    }
}

class InputState(
    private val containsError: Boolean = false,
    private val errorMessage: String = ""
) {
    fun show(inputLayout: TextInputLayout) = with(inputLayout) {
        isErrorEnabled = containsError
        error = errorMessage
    }
}

class ProgressState(private val visible: Boolean) {
    fun apply(progressBar: View, button: Button) {
        progressBar.visibility = if (visible) View.VISIBLE else View.GONE
        button.isEnabled = !visible
    }
}