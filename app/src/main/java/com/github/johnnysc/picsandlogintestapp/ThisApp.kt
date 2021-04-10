package com.github.johnnysc.picsandlogintestapp

import android.app.Application
import com.github.johnnysc.picsandlogintestapp.core.*

/**
 * Кастомный класс для приложения
 *
 * @author Asatryan on 05.04.21
 **/
class ThisApp : Application() {

    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()

        serviceLocator = ServiceLocator(BuildConfig.BUILD_TYPE, this)
    }
}