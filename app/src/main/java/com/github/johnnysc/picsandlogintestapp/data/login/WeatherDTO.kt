package com.github.johnnysc.picsandlogintestapp.data.login

import com.google.gson.annotations.SerializedName

/**
 * Данные по погоде от сервера
 *
 * @author Asatryan on 06.04.21
 **/
data class WeatherDTO(
    @SerializedName("weather") val weatherInnerDTO: List<WeatherInnerDTO>
)

data class WeatherInnerDTO(
    @SerializedName("description") val description: String,
    @SerializedName("main") val main: WeatherMainInfoDTO
)

data class WeatherMainInfoDTO(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double
)