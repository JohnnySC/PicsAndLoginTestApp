package com.github.johnnysc.picsandlogintestapp.ui.login.validator

/**
 * Валидатор пароля: текст должен содержать минимум 1 букву в нижнем регистре, 1 букву в верхнем регистре и 1 цифру
 *
 * @author Asatryan on 31.03.21
 **/
class PasswordValidator(
    override val errorMessage: String
) : UiValidator {

    override fun isValid(text: String): Boolean {
        var containsUpperCaseLetter = false
        var containsLowerCaseLetter = false
        var containsDigit = false

        var char: Char
        for (i in text.indices) {
            char = text[i]
            when {
                Character.isUpperCase(char) -> containsUpperCaseLetter = true
                Character.isLowerCase(char) -> containsLowerCaseLetter = true
                Character.isDigit(char) -> containsDigit = true
            }
        }
        return containsUpperCaseLetter && containsLowerCaseLetter && containsDigit
    }
}