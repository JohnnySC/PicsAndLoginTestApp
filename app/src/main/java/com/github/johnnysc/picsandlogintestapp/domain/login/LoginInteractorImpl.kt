package com.github.johnnysc.picsandlogintestapp.domain.login

import com.github.johnnysc.picsandlogintestapp.core.ExceptionHandler
import java.lang.Exception

/**
 * Основная реализация интерактора для логина
 *
 * @author Asatryan on 06.04.21
 **/
class LoginInteractorImpl(
    private val repository: LoginRepository,
    private val mapper: WeatherItemMapper,
    private val exceptionHandler: ExceptionHandler
) : LoginInteractor {

    override suspend fun login() = try {
        mapper.map(repository.getWeather())
    } catch (e: Exception) {
        WeatherItem.Error(exceptionHandler.defineExceptionType(e))
    }
}