package com.github.johnnysc.picsandlogintestapp.domain.login

import com.github.johnnysc.picsandlogintestapp.core.ExceptionType
import com.github.johnnysc.picsandlogintestapp.data.login.WeatherDTO
import com.github.johnnysc.picsandlogintestapp.data.login.WeatherInnerDTO
import com.github.johnnysc.picsandlogintestapp.data.login.WeatherMainInfoDTO
import org.hamcrest.core.Is.`is`
import org.junit.Assert.*
import org.junit.Test

/**
 * Тест для мапера погоды
 *
 * @see WeatherItemMapper
 *
 * @author Asatryan on 10.04.21
 */
class WeatherItemMapperTest {

    private val mapper = WeatherItemMapper()

    @Test
    fun test_empty_data() {
        val source = WeatherDTO(emptyList(), WeatherMainInfoDTO(1f, 2f))
        val result = mapper.map(source)
        assertThat(result is WeatherItem.Error, `is`(true))
        assertThat((result as WeatherItem.Error).exceptionType, `is`(ExceptionType.GENERIC))
    }

    @Test
    fun test_empty_description() {
        val source = WeatherDTO(listOf(WeatherInnerDTO("")), WeatherMainInfoDTO(1f, 2f))
        val result = mapper.map(source)
        assertThat(result is WeatherItem.Error, `is`(true))
        assertThat((result as WeatherItem.Error).exceptionType, `is`(ExceptionType.GENERIC))
    }

    @Test
    fun test_basic_case() {
        val source = WeatherDTO(listOf(WeatherInnerDTO("some")), WeatherMainInfoDTO(1.5f, 2.3f))
        val result = mapper.map(source)
        assertThat(result is WeatherItem.Basic, `is`(true))
        assertThat((result as WeatherItem.Basic).description, `is`("some"))
        assertThat(result.temp, `is`(1))
        assertThat(result.feelsLike, `is`(2))
    }
}