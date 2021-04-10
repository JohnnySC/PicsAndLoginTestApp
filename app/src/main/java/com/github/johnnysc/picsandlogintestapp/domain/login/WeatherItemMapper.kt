package com.github.johnnysc.picsandlogintestapp.domain.login

import com.github.johnnysc.picsandlogintestapp.core.Mapper
import com.github.johnnysc.picsandlogintestapp.data.login.WeatherDTO

/**
 * Мапим данные погоды от сервера к данным бизнес логики
 *
 * @author Asatryan on 06.04.21
 **/
class WeatherItemMapper : Mapper<WeatherItem, WeatherDTO> {

    override fun map(source: WeatherDTO) = when {
        source.weatherInnerDTO.isEmpty() -> WeatherItem.Error()
        source.weatherInnerDTO[0].description.isEmpty() -> WeatherItem.Error()
        else -> WeatherItem.Basic(
            source.weatherInnerDTO[0].description,
            source.main.temp.toInt(),
            source.main.feelsLike.toInt()
        )
    }
}