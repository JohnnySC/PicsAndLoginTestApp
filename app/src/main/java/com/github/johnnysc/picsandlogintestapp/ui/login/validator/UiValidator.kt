package com.github.johnnysc.picsandlogintestapp.ui.login.validator

/**
 * Валидатор юай слоя (для полей ввода)
 *
 * @author Asatryan on 31.03.21
 **/
interface UiValidator {

    val errorMessage: String

    /**
     * @return {@code true} если аргумент прошел валидацию, иначе {@code false}
     */
    fun isValid(text: String): Boolean
}