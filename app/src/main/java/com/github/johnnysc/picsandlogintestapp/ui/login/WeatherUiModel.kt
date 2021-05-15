package com.github.johnnysc.picsandlogintestapp.ui.login

import com.github.johnnysc.picsandlogintestapp.core.TextContainer

/**
 * Отображение погоды в юай слое
 *
 * @author Asatryan on 06.04.21
 **/
data class WeatherUiModel(private val description: String) {

    fun show(textContainer: TextContainer) = textContainer.show(description)
}