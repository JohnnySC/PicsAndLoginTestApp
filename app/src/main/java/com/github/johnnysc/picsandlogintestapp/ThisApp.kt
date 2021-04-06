package com.github.johnnysc.picsandlogintestapp

import android.app.Application
import com.github.johnnysc.picsandlogintestapp.core.NetworkModule
import com.github.johnnysc.picsandlogintestapp.core.ResourceManager
import com.github.johnnysc.picsandlogintestapp.data.login.LoginRepositoryImpl
import com.github.johnnysc.picsandlogintestapp.domain.pics.PicItemMapper
import com.github.johnnysc.picsandlogintestapp.domain.pics.PicsInteractorImpl
import com.github.johnnysc.picsandlogintestapp.data.pics.PicsRepositoryImpl
import com.github.johnnysc.picsandlogintestapp.domain.login.LoginInteractor
import com.github.johnnysc.picsandlogintestapp.domain.login.LoginInteractorImpl
import com.github.johnnysc.picsandlogintestapp.domain.login.WeatherItemMapper
import com.github.johnnysc.picsandlogintestapp.domain.pics.PicsInteractor

/**
 * Кастомный класс для приложения
 *
 * @author Asatryan on 05.04.21
 **/
class ThisApp : Application() {

    lateinit var picsInteractor: PicsInteractor
    lateinit var loginInteractor: LoginInteractor
    lateinit var resourceManager: ResourceManager

    override fun onCreate() {
        super.onCreate()
        resourceManager = ResourceManager(this)
        val networkModule = NetworkModule()
        picsInteractor =
            PicsInteractorImpl(
                PicsRepositoryImpl(
                    networkModule.picsService
                ),
                PicItemMapper()
            )
        loginInteractor = LoginInteractorImpl(
            LoginRepositoryImpl(networkModule.loginService),
            WeatherItemMapper()
        )
    }
}