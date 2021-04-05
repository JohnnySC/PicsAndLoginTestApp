package com.github.johnnysc.picsandlogintestapp.core

/**
 * Валидатор юай слоя (для полей ввода)
 *
 * @author Asatryan on 31.03.21
 **/
interface UiValidator {

    /**
     * Сообщение об ошибке
     */
    val errorMessage: String

    /**
     * @return {@code true} если аргумент прошел валидацию, иначе {@code false}
     */
    fun isValid(text: String): Boolean
}