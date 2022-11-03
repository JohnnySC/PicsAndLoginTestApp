package com.github.johnnysc.picsandlogintestapp.ui.login.validator

/**
 * Валидатор пустоты
 *
 * @author Asatryan on 31.03.21
 **/
class EmptinessValidator(errorMessage: String) : MinLengthValidator(errorMessage, 1)