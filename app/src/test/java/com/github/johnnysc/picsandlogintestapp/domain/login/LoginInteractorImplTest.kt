package com.github.johnnysc.picsandlogintestapp.domain.login

import com.github.johnnysc.picsandlogintestapp.core.ExceptionHandlerImpl
import com.github.johnnysc.picsandlogintestapp.data.login.WeatherDTO
import com.github.johnnysc.picsandlogintestapp.data.login.WeatherInnerDTO
import com.github.johnnysc.picsandlogintestapp.data.login.WeatherMainInfoDTO
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import java.net.UnknownHostException

/**
 * Тест для базовой имплементации интерактора логина
 *
 * @see LoginInteractorImpl
 *
 * @author Asatryan on 10.04.21
 */
class LoginInteractorImplTest {

    private val mapper = WeatherItemMapper()
    private val exceptionHandler = ExceptionHandlerImpl()

    @Test
    fun test_positive_case() = runBlocking {
        val repository = TestLoginRepository(true)
        val interactor =
            LoginInteractorImpl(repository, mapper, exceptionHandler)
        val result = interactor.login()
        assertThat(result is WeatherItem.Basic, `is`(true))
    }

    @Test
    fun test_negative_case() = runBlocking {
        val repository = TestLoginRepository(false)
        val interactor =
            LoginInteractorImpl(repository, mapper, exceptionHandler)
        val result = interactor.login()
        assertThat(result is WeatherItem.Error, `is`(true))
    }

    private inner class TestLoginRepository(private val getSuccess: Boolean) : LoginRepository {

        override suspend fun getWeather(): WeatherDTO {
            if (getSuccess)
                return WeatherDTO(listOf(WeatherInnerDTO("a")), WeatherMainInfoDTO(1.3f, 2.2f))
            else
                throw UnknownHostException()
        }
    }
}