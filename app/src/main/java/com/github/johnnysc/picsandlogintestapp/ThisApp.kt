package com.github.johnnysc.picsandlogintestapp

import android.app.Application
import com.github.johnnysc.picsandlogintestapp.core.NetworkModule
import com.github.johnnysc.picsandlogintestapp.data.login.LoginRepositoryImpl
import com.github.johnnysc.picsandlogintestapp.domain.pics.PicItemMapper
import com.github.johnnysc.picsandlogintestapp.domain.pics.PicsInteractorImpl
import com.github.johnnysc.picsandlogintestapp.data.pics.PicsRepositoryImpl
import com.github.johnnysc.picsandlogintestapp.domain.login.LoginInteractorImpl
import com.github.johnnysc.picsandlogintestapp.domain.login.WeatherItemMapper

/**
 * Кастомный класс для приложения
 *
 * @author Asatryan on 05.04.21
 **/
class ThisApp : Application() {

    private lateinit var networkModule: NetworkModule

    override fun onCreate() {
        super.onCreate()
        networkModule = NetworkModule()
    }

    fun getPicsInteractor() = PicsInteractorImpl(
        PicsRepositoryImpl(networkModule.getPicsService()),
        PicItemMapper()
    )

    fun getLoginInteractor() = LoginInteractorImpl(
        LoginRepositoryImpl(networkModule.getLoginService()),
        WeatherItemMapper()
    )
}