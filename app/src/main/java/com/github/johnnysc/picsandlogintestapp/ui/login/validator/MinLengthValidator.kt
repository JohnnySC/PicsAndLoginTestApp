package com.github.johnnysc.picsandlogintestapp.ui.login.validator

/**
 * Валидатор минимальной длины
 *
 * @author Asatryan on 31.03.21
 **/
open class MinLengthValidator(
    override val errorMessage: String,
    private val minLength: Int
) : UiValidator {

    override fun isValid(text: String): Boolean {
        return text.length >= minLength
    }
}