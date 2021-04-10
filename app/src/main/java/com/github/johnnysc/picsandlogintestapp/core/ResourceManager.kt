package com.github.johnnysc.picsandlogintestapp.core

import androidx.annotation.StringRes

/**
 * Класс обертка над контекстом для удобного доступа к ресурсам
 *
 * @author Asatryan on 09.04.21
 */
interface ResourceManager {

    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg args: Any?): String

    fun getErrorMessage(exceptionType: ExceptionType): String
}