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

    private val mapper = WeatherUiMapperImpl(TestResourceManagerImpl())

    @Test
    fun test_basic() {
        val item = WeatherItem.Basic("desc", 1, 2)
        val result = item.map(mapper)
        val expected = WeatherUiModel("stub with args")
        assertThat(result, `is`(expected))
    }

    @Test
    fun test_network_error() {
        val item = WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE)
        val result = item.map(mapper)
        val expected = WeatherUiModel("network is not available!")
        assertThat(result, `is`(expected))
    }

    @Test
    fun test_generic_error() {
        val item = WeatherItem.Error(ExceptionType.GENERIC)
        val result = item.map(mapper)
        val expected = WeatherUiModel("just generic error")
        assertThat(result, `is`(expected))
    }
}