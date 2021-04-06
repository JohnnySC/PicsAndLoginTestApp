package com.github.johnnysc.picsandlogintestapp.domain.login

import com.github.johnnysc.picsandlogintestapp.core.Mapper
import com.github.johnnysc.picsandlogintestapp.data.login.WeatherDTO

/**
 * Мапим данные погоды от сервера к данным бизнес логики
 *
 * @author Asatryan on 06.04.21
 **/
class WeatherItemMapper : Mapper<WeatherItem, WeatherDTO> {

    override fun map(source: WeatherDTO) = with(source.weatherInnerDTO) {
        when {
            isEmpty() -> WeatherItem.Error
            this[0].description.isEmpty() -> WeatherItem.Error
            else -> WeatherItem.Basic(
                this[0].description,
                this[0].main.temp.toString(),
                this[0].main.feelsLike.toString()
            )
        }
    }
}