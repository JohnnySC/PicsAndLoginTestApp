package com.github.johnnysc.picsandlogintestapp.core

import android.content.Context
import androidx.annotation.StringRes
import com.github.johnnysc.picsandlogintestapp.R

/**
 * Класс обертка над контекстом для удобного доступа к ресурсам
 *
 * @author Asatryan on 09.04.21
 */
class ResourceManager(private val context: Context) {

    fun getString(@StringRes resId: Int) = context.getString(resId)

    fun getString(@StringRes resId: Int, vararg args: Any?) = context.getString(resId, *args)

    fun getErrorMessage(exceptionType: ExceptionType) = getString(
        when (exceptionType) {
            ExceptionType.NETWORK_UNAVAILABLE -> R.string.network_unavailable_message
            else -> R.string.generic_error_message
        }
    )
}