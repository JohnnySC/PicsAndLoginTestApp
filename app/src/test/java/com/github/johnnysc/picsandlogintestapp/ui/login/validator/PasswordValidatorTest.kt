package com.github.johnnysc.picsandlogintestapp.ui.login.validator

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

/**
 * @author Asatryan on 31.03.21
 */
class PasswordValidatorTest {

    private val validator = PasswordValidator("stub")

    @Test
    fun test_positive() {
        val validStrings = listOf("aA1", "2gW", "234sdfWER", "234SDFs", "23fsa=9!!%^&X")
        validStrings.forEach {
            assertThat(validator.isValid(it), `is`(true))
        }
    }

    @Test
    fun test_negative() {
        val invalidStrings =
            listOf("1234567", "qwerty", "QWERTY", "qwerty123", "QWERTY123", "QWERTYqwerty", "")
        invalidStrings.forEach {
            assertThat(validator.isValid(it), `is`(false))
        }
    }
}