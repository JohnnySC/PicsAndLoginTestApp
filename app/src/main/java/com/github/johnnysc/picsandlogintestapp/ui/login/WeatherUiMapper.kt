package com.github.johnnysc.picsandlogintestapp.ui.login

import com.github.johnnysc.picsandlogintestapp.R
import com.github.johnnysc.picsandlogintestapp.core.ExceptionType
import com.github.johnnysc.picsandlogintestapp.core.ResourceManager
import com.github.johnnysc.picsandlogintestapp.domain.login.WeatherUiMapper

/**
 * Мапим данные бизнес логики погоды к данным юай слоя
 *
 * @author Asatryan on 09.04.21
 **/
class WeatherUiMapperImpl(private val resourceManager: ResourceManager) :
    WeatherUiMapper<WeatherUiModel> {

    override fun map(feelsLike: Int, description: String, temp: Int) =
        WeatherUiModel(
            resourceManager.getString(
                R.string.weather_description,
                description,
                temp,
                feelsLike
            )
        )

    override fun map(exceptionType: ExceptionType) =
        WeatherUiModel(resourceManager.getErrorMessage(exceptionType))
}