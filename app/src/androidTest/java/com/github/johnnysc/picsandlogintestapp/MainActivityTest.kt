package com.github.johnnysc.picsandlogintestapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Asatryan on 03.11.2022
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test() {
        onView(withId(R.id.navigation_login)).perform(click())
        onView(withId(R.id.logoImageView)).check(matches(isDisplayed()))

        onView(withId(R.id.loginButton)).perform(click())
        onView(withId(R.id.emailAddressTextInputLayout)).check(matches(HasTextInputLayoutErrorMatcher("Input is empty")))
        onView(withId(R.id.passwordTextInputLayout)).check(matches(HasTextInputLayoutErrorMatcher("Input is empty")))

        onView(withId(R.id.emailAddressEditText)).perform(typeText("j"), closeSoftKeyboard())
        onView(withId(R.id.emailAddressTextInputLayout)).check(
            matches(
                TextInputLayoutHasNoError()
            )
        )
        onView(withId(R.id.loginButton)).perform(click())
        onView(withId(R.id.emailAddressTextInputLayout)).check(matches(HasTextInputLayoutErrorMatcher("Email is incorrect")))

        onView(withId(R.id.emailAddressEditText)).perform(
            replaceText("johnnysc091@gmail.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.emailAddressTextInputLayout)).check(
            matches(
                TextInputLayoutHasNoError()
            )
        )

        onView(withId(R.id.passwordEditText)).perform(replaceText("12345"), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())
        onView(withId(R.id.passwordTextInputLayout)).check(matches(HasTextInputLayoutErrorMatcher("Input should be at least 6 signs long")))

        listOf("123456", "asdfgh", "ASDFGH", "!@#$%^&").forEach {
            onView(withId(R.id.passwordEditText)).perform(replaceText(it), closeSoftKeyboard())
            onView(withId(R.id.loginButton)).perform(click())
            onView(withId(R.id.passwordTextInputLayout)).check(matches(HasTextInputLayoutErrorMatcher("Password should contain at least 1 lowercase letter, 1 uppercase letter and 1 digit")))
        }

        onView(withId(R.id.passwordEditText)).perform(
            replaceText("asdfASDF123"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.loginButton)).perform(click())
        onView(withId(R.id.passwordTextInputLayout)).check(matches(TextInputLayoutHasNoError()))

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("stub with args")))
    }
}