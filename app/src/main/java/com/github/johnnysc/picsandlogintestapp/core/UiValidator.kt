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
    fun errorMessage(): String

    /**
     * @return {@code true} если аргумент прошел валидацию, иначе {@code false}
     */
    fun isValid(text: String): Boolean

    abstract class Abstract(private val message: String) : UiValidator {
        override fun errorMessage(): String {
            return message
        }
    }
}