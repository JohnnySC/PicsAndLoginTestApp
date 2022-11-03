package com.github.johnnysc.picsandlogintestapp.ui.login

import com.github.johnnysc.picsandlogintestapp.core.TextContainer

/**
 * Отображение погоды в юай слое
 *
 * @author Asatryan on 06.04.21
 **/
data class WeatherUiModel(
    private val description: String,
    private val isError: Boolean = false
) {

    fun map(communication: LoginCommunication) =
        communication.map(
            if (isError)
                LoginState.Error(this)
            else
                LoginState.Success(this)
        )

    fun show(textContainer: TextContainer) = textContainer.show(description)
}