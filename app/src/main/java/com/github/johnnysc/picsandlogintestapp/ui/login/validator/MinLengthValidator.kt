package com.github.johnnysc.picsandlogintestapp.ui.login.validator

import com.github.johnnysc.picsandlogintestapp.core.UiValidator

/**
 * Валидатор минимальной длины
 *
 * @author Asatryan on 31.03.21
 **/
open class MinLengthValidator(
    errorMessage: String,
    private val minLength: Int
) : UiValidator.Abstract(errorMessage) {

    override fun isValid(text: String): Boolean {
        return text.length >= minLength
    }
}