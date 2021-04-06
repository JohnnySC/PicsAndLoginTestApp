package com.github.johnnysc.picsandlogintestapp.domain.login

import java.lang.Exception

/**
 * Основная реализация интерактора для логина
 *
 * @author Asatryan on 06.04.21
 **/
class LoginInteractorImpl(
    private val repository: LoginRepository,
    private val mapper: WeatherItemMapper
) : LoginInteractor {

    override suspend fun login() = try {
        mapper.map(repository.getWeather())
    } catch (e: Exception) {
        WeatherItem.Error
    }
}