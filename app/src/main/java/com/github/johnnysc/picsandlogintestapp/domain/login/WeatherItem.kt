package com.github.johnnysc.picsandlogintestapp.domain.login

import com.github.johnnysc.picsandlogintestapp.core.ExceptionType

/**
 * Данные по погоде для слоя бизнес логики
 *
 * @author Asatryan on 06.04.21
 **/
sealed class WeatherItem {
    abstract fun <T> map(mapper: WeatherUiMapper<T>): T
    data class Basic(
        private val description: String,
        private val temp: Int,
        private val feelsLike: Int
    ) : WeatherItem() {
        override fun <T> map(mapper: WeatherUiMapper<T>) =
            mapper.map(feelsLike, description, temp)
    }

    data class Error(private val exceptionType: ExceptionType = ExceptionType.GENERIC) : WeatherItem() {
        override fun <T> map(mapper: WeatherUiMapper<T>) = mapper.map(exceptionType)
    }
}

interface WeatherUiMapper<T> {

    fun map(feelsLike: Int, description: String, temp: Int): T

    fun map(exceptionType: ExceptionType): T
}