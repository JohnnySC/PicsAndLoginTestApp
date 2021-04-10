package com.github.johnnysc.picsandlogintestapp.ui.login

import com.github.johnnysc.picsandlogintestapp.R
import com.github.johnnysc.picsandlogintestapp.core.Mapper
import com.github.johnnysc.picsandlogintestapp.core.ResourceManager
import com.github.johnnysc.picsandlogintestapp.domain.login.WeatherItem

/**
 * Мапим данные бизнес логики погоды к данным юай слоя
 *
 * @author Asatryan on 09.04.21
 **/
class WeatherUiMapper(private val resourceManager: ResourceManager) :
    Mapper<WeatherUiModel, WeatherItem> {

    override fun map(source: WeatherItem) = if (source is WeatherItem.Basic) {
        WeatherUiModel(
            resourceManager.getString(
                R.string.weather_description,
                source.description,
                source.temp,
                source.feelsLike
            )
        )
    } else
        WeatherUiModel(
            resourceManager.getErrorMessage(
                (source as WeatherItem.Error).exceptionType
            )
        )
}