package com.github.johnnysc.picsandlogintestapp.domain.login

import com.github.johnnysc.picsandlogintestapp.core.ExceptionType

/**
 * Данные по погоде для слоя бизнес логики
 *
 * @author Asatryan on 06.04.21
 **/
sealed class WeatherItem {
    data class Basic(
        val description: String,
        val temp: Int,
        val feelsLike: Int
    ) : WeatherItem()

    data class Error(val exceptionType: ExceptionType  = ExceptionType.GENERIC) : WeatherItem()
}