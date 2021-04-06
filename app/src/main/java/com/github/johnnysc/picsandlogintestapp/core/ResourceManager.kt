package com.github.johnnysc.picsandlogintestapp.core

import android.content.Context
import androidx.annotation.StringRes

/**
 * Класс для удобного доступа к ресурсам
 *
 * @author Asatryan on 06.04.21
 **/
class ResourceManager(private val context: Context) {

    fun getString(@StringRes resId: Int) = context.getString(resId)

    fun getStringWithArgs(@StringRes resId: Int, vararg args: Any) = context.getString(resId, args)
}