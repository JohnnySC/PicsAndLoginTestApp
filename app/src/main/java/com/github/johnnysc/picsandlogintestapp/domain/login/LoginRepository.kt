package com.github.johnnysc.picsandlogintestapp.domain.login

import com.github.johnnysc.picsandlogintestapp.data.login.WeatherDTO

/**
 * Репозиторий для логина
 *
 * @author Asatryan on 06.04.21
 **/
interface LoginRepository {

    suspend fun getWeather(): WeatherDTO
}