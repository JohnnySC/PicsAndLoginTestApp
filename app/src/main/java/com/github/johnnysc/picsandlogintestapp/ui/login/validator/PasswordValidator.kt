package com.github.johnnysc.picsandlogintestapp.ui.login.validator

import com.github.johnnysc.picsandlogintestapp.core.UiValidator
import java.util.regex.Pattern

/**
 * Валидатор пароля: текст должен содержать минимум 1 букву в нижнем регистре, 1 букву в верхнем регистре и 1 цифру
 *
 * @author Asatryan on 31.03.21
 **/
class PasswordValidator(
    override val errorMessage: String
) : UiValidator {

    override fun isValid(text: String) =
        Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\W]{3,}")
            .matcher(text)
            .matches()

}