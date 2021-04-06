package com.github.johnnysc.picsandlogintestapp.domain.login

/**
 * Интерактор для логина
 *
 * @author Asatryan on 06.04.21
 **/
interface LoginInteractor {

    suspend fun login(): WeatherItem
}