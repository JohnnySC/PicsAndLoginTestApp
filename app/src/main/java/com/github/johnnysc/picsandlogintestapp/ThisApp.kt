package com.github.johnnysc.picsandlogintestapp

import android.app.Application
import com.github.johnnysc.picsandlogintestapp.core.*

/**
 * Кастомный класс для приложения
 *
 * @author Asatryan on 05.04.21
 **/
class ThisApp : Application() {

    lateinit var loginInstanceProvider: LoginUiInstanceProvider
    lateinit var picsInstanceProvider: PicsUiInstanceProvider

    override fun onCreate() {
        super.onCreate()

        ServiceLocator(BuildConfig.BUILD_TYPE, this).instanceProvider.let {
            loginInstanceProvider = it
            picsInstanceProvider = it
        }
    }
}