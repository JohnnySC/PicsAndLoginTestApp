package com.github.johnnysc.picsandlogintestapp.ui.login

import com.google.android.material.snackbar.Snackbar

/**
 * Отображение погоды в юай слое
 *
 * @author Asatryan on 06.04.21
 **/
data class WeatherUiModel(private val description: String) {

    fun show(snackbar: Snackbar) {
        snackbar.setText(description)
        snackbar.show()
    }
}