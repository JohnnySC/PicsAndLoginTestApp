package com.github.johnnysc.picsandlogintestapp.ui.login.validator

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

/**
 * Тест для валидатора пустоты
 *
 * @see EmptinessValidator
 *
 * @author Asatryan on 31.03.21
 */
class EmptinessValidatorTest {

    private val validator = EmptinessValidator("stub")

    @Test
    fun test_positive() {
        val validStrings = listOf<String>("a", "12", " ", "s -d", "'")
        validStrings.forEach {
            assertThat(validator.isValid(it), `is`(true))
        }
    }

    @Test
    fun test_negative() {
        val invalidString = ""
        assertThat(validator.isValid(invalidString), `is`(false))
    }
}