package com.github.johnnysc.picsandlogintestapp.core

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

/**
 * Тестируем цепочки валидаторов
 *
 * @author Asatryan on 19.04.21
 **/
class UiValidatorChainTest {

    @Test
    fun test_two_validators_first_and_second_valid() {
        val chain = UiValidatorChain(
            TestUiValidator("first error message", "validOne"),
            TestUiValidator("second error message", "validOne")
        )

        assertThat(chain.isValid("validOne"), `is`(true))
    }

    @Test
    fun test_two_validators_first_invalid() {
        val chain = UiValidatorChain(
            TestUiValidator("first error message", "validOne"),
            TestUiValidator("second error message", "validOne")
        )

        assertThat(chain.isValid("invalid"), `is`(false))
        assertThat(chain.errorMessage(), `is`("first error message"))
    }

    @Test
    fun test_two_validators_second_invalid() {
        val chain = UiValidatorChain(
            TestUiValidator("first error message", "validOne"),
            TestUiValidator("second error message", "validTwo")
        )

        assertThat(chain.isValid("validOne"), `is`(false))
        assertThat(chain.errorMessage(), `is`("second error message"))
    }

    @Test
    fun test_three_validators_all_valid() {
        val chain = UiValidatorChain(
            TestUiValidator("first error message", "validOne"),
            UiValidatorChain(
                TestUiValidator("second error message", "validOne"),
                TestUiValidator("third error message", "validOne")
            )
        )

        assertThat(chain.isValid("validOne"), `is`(true))
    }

    @Test
    fun test_three_validators_first_invalid() {
        val chain = UiValidatorChain(
            TestUiValidator("first error message", "validOne"),
            UiValidatorChain(
                TestUiValidator("second error message", "validOne"),
                TestUiValidator("third error message", "validOne")
            )
        )

        assertThat(chain.isValid("invalid"), `is`(false))
        assertThat(chain.errorMessage(), `is`("first error message"))
    }

    @Test
    fun test_three_validators_second_invalid() {
        val chain = UiValidatorChain(
            TestUiValidator("first error message", "validOne"),
            UiValidatorChain(
                TestUiValidator("second error message", "validTwo"),
                TestUiValidator("third error message", "validOne")
            )
        )

        assertThat(chain.isValid("validOne"), `is`(false))
        assertThat(chain.errorMessage(), `is`("second error message"))
    }

    @Test
    fun test_three_validators_third_invalid() {
        val chain = UiValidatorChain(
            UiValidatorChain(
                TestUiValidator("first error message", "validOne"),
                TestUiValidator("second error message", "validOne")
            ),
            TestUiValidator("third error message", "validThird")
        )

        assertThat(chain.isValid("validOne"), `is`(false))
        assertThat(chain.errorMessage(), `is`("third error message"))
    }

    private inner class TestUiValidator(
        errorMessage: String,
        private val validText: String
    ) : UiValidator.Abstract(errorMessage) {

        override fun isValid(text: String): Boolean {
            return validText == text
        }
    }
}