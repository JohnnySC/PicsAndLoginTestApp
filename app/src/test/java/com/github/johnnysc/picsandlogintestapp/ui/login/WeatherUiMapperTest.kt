package com.github.johnnysc.picsandlogintestapp.ui.login

import com.github.johnnysc.picsandlogintestapp.core.ExceptionType
import com.github.johnnysc.picsandlogintestapp.core.TestResourceManagerImpl
import com.github.johnnysc.picsandlogintestapp.domain.login.WeatherItem
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

/**
 * Тест для мапера погоды
 *
 * @see WeatherUiMapper
 *
 * @author Asatryan on 10.04.21
 */
class WeatherUiMapperTest {

    private val mapper = WeatherUiMapper(TestResourceManagerImpl())

    @Test
    fun test_basic() {
        val item = WeatherItem.Basic("desc", 1, 2)
        val result = mapper.map(item)
        val expected = WeatherUiModel("stub with args")
        assertThat(result, `is`(expected))
    }

    @Test
    fun test_network_error() {
        val item = WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE)
        val result = mapper.map(item)
        val expected = WeatherUiModel("network is not available!")
        assertThat(result, `is`(expected))
    }

    @Test
    fun test_generic_error() {
        val item = WeatherItem.Error(ExceptionType.GENERIC)
        val result = mapper.map(item)
        val expected = WeatherUiModel("just generic error")
        assertThat(result, `is`(expected))
    }
}