package com.github.johnnysc.picsandlogintestapp.ui.login

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.picsandlogintestapp.core.ExceptionType
import com.github.johnnysc.picsandlogintestapp.core.UiValidator
import com.github.johnnysc.picsandlogintestapp.domain.login.LoginInteractor
import com.github.johnnysc.picsandlogintestapp.domain.login.WeatherItem
import com.github.johnnysc.picsandlogintestapp.domain.login.WeatherUiMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Asatryan on 03.11.2022
 */
class LoginViewModelTest {

    @Test
    fun test_both_empty() = runBlocking {
        val communication = FakeLoginCommunication.Base()
        val interactor = FakeInteractor(WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE))
        val empty = FakeValidator(false, "Input is empty")
        val viewModel = LoginViewModel(
            communication,
            interactor,
            FakeMapper(""),
            empty,
            empty,
            TestDispatchersList()
        )
        viewModel.login(email = "", password = "")
        assertEquals(
            LoginState.TwoErrors(
                loginError = "Input is empty",
                passwordError = "Input is empty"
            ), communication.state()
        )
    }

    @Test
    fun test_empty_email_valid_password() = runBlocking {
        val communication = FakeLoginCommunication.Base()
        val interactor = FakeInteractor(WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE))
        val email = FakeValidator(false, "Input is empty")
        val password = FakeValidator(true, "")
        val viewModel = LoginViewModel(
            communication,
            interactor,
            FakeMapper(""),
            email,
            password,
            TestDispatchersList()
        )
        viewModel.login(email = "", password = "asdfASDF123")
        assertEquals(
            LoginState.LoginError(
                value = "Input is empty"
            ),
            communication.state()
        )
    }

    @Test
    fun test_invalid_email_valid_password() = runBlocking {
        val communication = FakeLoginCommunication.Base()
        val interactor = FakeInteractor(WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE))
        val email = FakeValidator(false, "Email is incorrect")
        val password = FakeValidator(true, "")
        val viewModel = LoginViewModel(
            communication,
            interactor,
            FakeMapper(""),
            email,
            password,
            TestDispatchersList()
        )
        viewModel.login(email = "a", password = "asdfASDF123")
        assertEquals(
            LoginState.LoginError(
                value = "Email is incorrect"
            ),
            communication.state()
        )
    }

    @Test
    fun test_invalid_email() = runBlocking {
        val communication = FakeLoginCommunication.Base()
        val interactor = FakeInteractor(WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE))
        val email = FakeValidator(false, "Email is incorrect")
        val password = FakeValidator(false, "Input is empty")
        val viewModel = LoginViewModel(
            communication,
            interactor,
            FakeMapper(""),
            email,
            password,
            TestDispatchersList()
        )
        viewModel.login(email = "a", password = "")
        assertEquals(
            LoginState.TwoErrors(
                loginError = "Email is incorrect",
                passwordError = "Input is empty"
            ),
            communication.state()
        )
    }

    @Test
    fun test_valid_email() = runBlocking {
        val communication = FakeLoginCommunication.Base()
        val interactor = FakeInteractor(WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE))
        val email = FakeValidator(true, "")
        val password = FakeValidator(false, "Input is empty")
        val viewModel = LoginViewModel(
            communication,
            interactor,
            FakeMapper(""),
            email,
            password,
            TestDispatchersList()
        )
        viewModel.login(email = "johnnysc091@gmail.com", password = "")
        assertEquals(LoginState.PasswordError(value = "Input is empty"), communication.state())
    }

    @Test
    fun test_password_invalid_length() = runBlocking {
        val communication = FakeLoginCommunication.Base()
        val interactor = FakeInteractor(WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE))
        val email = FakeValidator(true, "")
        val password = FakeValidator(false, "Input should be at least 6 signs long")
        val viewModel = LoginViewModel(
            communication,
            interactor,
            FakeMapper(""),
            email,
            password,
            TestDispatchersList()
        )
        viewModel.login(email = "johnnysc091@gmail.com", password = "asdf")
        assertEquals(
            LoginState.PasswordError(value = "Input should be at least 6 signs long"),
            communication.state()
        )
    }

    @Test
    fun test_password_invalid() = runBlocking {
        val communication = FakeLoginCommunication.Base()
        val interactor = FakeInteractor(WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE))
        val email = FakeValidator(true, "")
        val password = FakeValidator(
            false,
            "Password should contain at least 1 lowercase letter, 1 uppercase letter and 1 digit"
        )
        val viewModel = LoginViewModel(
            communication,
            interactor,
            FakeMapper(""),
            email,
            password,
            TestDispatchersList()
        )
        listOf("123456", "asdfgh", "ASDFGH", "!@#$%^&").forEach {
            viewModel.login(email = "johnnysc091@gmail.com", password = it)
            assertEquals(
                LoginState.PasswordError(value = "Password should contain at least 1 lowercase letter, 1 uppercase letter and 1 digit"),
                communication.state()
            )
        }
    }

    @Test
    fun test_input_valid_no_connection() = runBlocking {
        val communication = FakeLoginCommunication.Base()
        val interactor = FakeInteractor(WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE))
        val valid = FakeValidator(true, "")
        val viewModel = LoginViewModel(
            communication,
            interactor,
            FakeMapper("Network is unavailable"),
            valid,
            valid,
            TestDispatchersList()
        )
        viewModel.login(email = "johnnysc091@gmail.com", password = "asdfASDF123")
        assertEquals(
            communication.state(),
            LoginState.Error(value = WeatherUiModel("Network is unavailable", true))
        )
    }

    @Test
    fun test_valid_response() = runBlocking {
        val communication = FakeLoginCommunication.Base()
        val interactor = FakeInteractor(WeatherItem.Basic("description", 20, 22))
        val valid = FakeValidator(true, "")
        val viewModel = LoginViewModel(
            communication,
            interactor,
            FakeMapper(""),
            valid,
            valid,
            TestDispatchersList()
        )
        viewModel.login(email = "johnnysc091@gmail.com", password = "asdfASDF123")
        assertEquals(
            communication.state(),
            LoginState.Success(value = WeatherUiModel("description 20 22"))
        )
    }

    private class TestDispatchersList(
        private val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    ) : DispatchersList {

        override fun io(): CoroutineDispatcher = dispatcher
        override fun ui(): CoroutineDispatcher = dispatcher
    }

    private class FakeMapper(private val error: String) : WeatherUiMapper<WeatherUiModel> {
        override fun map(feelsLike: Int, description: String, temp: Int): WeatherUiModel {
            return WeatherUiModel("$description $temp $feelsLike")
        }

        override fun map(exceptionType: ExceptionType): WeatherUiModel {
            return WeatherUiModel(error, true)
        }
    }

    private class FakeValidator(private val valid: Boolean, private val message: String) :
        UiValidator {
        override fun errorMessage(): String {
            return message
        }

        override fun isValid(text: String): Boolean {
            return valid
        }
    }

    private interface FakeLoginCommunication : LoginCommunication {

        fun state(): LoginState

        class Base : FakeLoginCommunication {
            private var state: LoginState? = null

            override fun state(): LoginState = state!!

            override fun map(source: LoginState) {
                state = source
            }

            override fun observe(owner: LifecycleOwner, observer: Observer<LoginState>) = Unit
        }
    }

    private class FakeInteractor(
        private val item: WeatherItem
    ) : LoginInteractor {

        override suspend fun login(): WeatherItem {
            return item
        }
    }
}