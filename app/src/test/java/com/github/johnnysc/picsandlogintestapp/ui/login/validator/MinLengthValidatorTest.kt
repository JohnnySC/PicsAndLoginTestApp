package com.github.johnnysc.picsandlogintestapp.ui.login.validator

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

/**
 * @author Asatryan on 31.03.21
 */
class MinLengthValidatorTest {

    private lateinit var validator: MinLengthValidator

    @Test
    fun test_positive_2_signs() {
        validator = MinLengthValidator("stub", 2)

        val validStrings = listOf("12", "  ", " 2", "2 ", "123", "234r5", "as;ldkfs")
        validStrings.forEach {
            assertThat(validator.isValid(it), `is`(true))
        }
    }

    @Test
    fun test_positive_3_signs() {
        validator = MinLengthValidator("stub", 3)

        val validStrings = listOf("1 2", " _ ", " 2 ", "2 3", "1234", "234 r5", "as;ldk asdf s")
        validStrings.forEach {
            assertThat(validator.isValid(it), `is`(true))
        }
    }

    @Test
    fun test_negative() {
        validator = MinLengthValidator("stub", 5)

        val invalidStrings = listOf("", " ", "1", "12", "123", "1234", "asdf")
        invalidStrings.forEach {
            assertThat(validator.isValid(it), `is`(false))
        }
    }
}