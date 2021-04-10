package com.github.johnnysc.picsandlogintestapp.core

import android.content.Context
import androidx.annotation.StringRes
import com.github.johnnysc.picsandlogintestapp.R

/**
 * Основная реализация менеджера ресурсов
 *
 * @author Asatryan on 10.04.21
 **/
class ResourceManagerImpl(private val context: Context) : ResourceManager {

    override fun getString(@StringRes resId: Int) = context.getString(resId)

    override fun getString(@StringRes resId: Int, vararg args: Any?) =
        context.getString(resId, *args)

    override fun getErrorMessage(exceptionType: ExceptionType) = getString(
        when (exceptionType) {
            ExceptionType.NETWORK_UNAVAILABLE -> R.string.network_unavailable_message
            else -> R.string.generic_error_message
        }
    )
}