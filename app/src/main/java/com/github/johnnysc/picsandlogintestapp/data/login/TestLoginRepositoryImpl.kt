package com.github.johnnysc.picsandlogintestapp.data.login

import com.github.johnnysc.picsandlogintestapp.domain.login.LoginRepository
import java.net.UnknownHostException

/**
 * Тестовая реализация репозитория для логина
 *
 * @author Asatryan on 10.04.21
 **/
class TestLoginRepositoryImpl : LoginRepository {

    var count = 0

    override suspend fun getWeather(): WeatherDTO {
        count++
        if (count % 2 == 0) //имитируем проблемы с сетью через раз
            throw UnknownHostException()
        return WeatherDTO(
            listOf(WeatherInnerDTO("testing description")),
            WeatherMainInfoDTO(5.6f, 7f)
        )
    }
}