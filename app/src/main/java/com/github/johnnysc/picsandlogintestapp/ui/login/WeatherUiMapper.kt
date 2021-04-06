package com.github.johnnysc.picsandlogintestapp.ui.login

import com.github.johnnysc.picsandlogintestapp.R
import com.github.johnnysc.picsandlogintestapp.core.Mapper
import com.github.johnnysc.picsandlogintestapp.core.ResourceManager
import com.github.johnnysc.picsandlogintestapp.domain.login.WeatherItem

/**
 * Мапим данные бизнес логики к юай
 *
 * @author Asatryan on 06.04.21
 **/
class WeatherUiMapper(private val resourceManager: ResourceManager) :
    Mapper<WeatherUiModel, WeatherItem> {

    override fun map(source: WeatherItem) = WeatherUiModel(
        if (source is WeatherItem.Basic)
            resourceManager.getStringWithArgs(
                R.string.weather_description,
                source.description,
                source.temp,
                source.feelsLike
            )
        else
            resourceManager.getString(R.string.generic_error_message)
    )
}