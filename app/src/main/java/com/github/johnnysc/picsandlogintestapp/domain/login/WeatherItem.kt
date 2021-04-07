package com.github.johnnysc.picsandlogintestapp.domain.login

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

    object Error : WeatherItem()
}