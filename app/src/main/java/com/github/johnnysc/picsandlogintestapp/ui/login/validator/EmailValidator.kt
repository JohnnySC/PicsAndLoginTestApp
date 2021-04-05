package com.github.johnnysc.picsandlogintestapp.ui.login.validator

import com.github.johnnysc.picsandlogintestapp.core.UiValidator

/**
 * Валидатор электронной почты
 *
 * @author Asatryan on 31.03.21
 **/
class EmailValidator(override val errorMessage: String) :
    UiValidator {

    override fun isValid(text: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches();
    }
}