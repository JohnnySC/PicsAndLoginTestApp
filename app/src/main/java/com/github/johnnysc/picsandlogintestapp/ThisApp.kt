package com.github.johnnysc.picsandlogintestapp

import android.app.Application
import com.github.johnnysc.picsandlogintestapp.core.LoginUiInstanceProvider
import com.github.johnnysc.picsandlogintestapp.core.PicsUiInstanceProvider
import com.github.johnnysc.picsandlogintestapp.core.ServiceLocator
import com.github.johnnysc.picsandlogintestapp.core.UiValidatorChain
import com.github.johnnysc.picsandlogintestapp.ui.login.DispatchersList
import com.github.johnnysc.picsandlogintestapp.ui.login.LoginCommunication
import com.github.johnnysc.picsandlogintestapp.ui.login.LoginViewModel
import com.github.johnnysc.picsandlogintestapp.ui.login.validator.EmailValidator
import com.github.johnnysc.picsandlogintestapp.ui.login.validator.EmptinessValidator
import com.github.johnnysc.picsandlogintestapp.ui.login.validator.MinLengthValidator
import com.github.johnnysc.picsandlogintestapp.ui.login.validator.PasswordValidator

/**
 * Кастомный класс для приложения
 *
 * @author Asatryan on 05.04.21
 **/
class ThisApp : Application() {

    private lateinit var loginInstanceProvider: LoginUiInstanceProvider
    lateinit var picsInstanceProvider: PicsUiInstanceProvider

    override fun onCreate() {
        super.onCreate()

        ServiceLocator(BuildConfig.BUILD_TYPE, this).instanceProvider.let {
            loginInstanceProvider = it
            picsInstanceProvider = it
        }
    }

    fun viewModel(): LoginViewModel {
        val max = 6
        val empty = EmptinessValidator(getString(R.string.empty_string_error_message))
        return LoginViewModel(
            LoginCommunication.Base(),
            loginInstanceProvider.provideLoginInteractor(),
            loginInstanceProvider.provideWeatherUiMapper(),
            UiValidatorChain(
                empty,
                EmailValidator(getString(R.string.invalid_email_error_message))
            ),
            UiValidatorChain(
                empty,
                UiValidatorChain(
                    MinLengthValidator(
                        getString(
                            R.string.invalid_min_length_error_message,
                            max
                        ), max
                    ),
                    PasswordValidator(getString(R.string.invalid_password_error_message))
                )
            ),
            DispatchersList.Base()
        )
    }
}