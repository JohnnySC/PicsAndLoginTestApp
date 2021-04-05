package com.github.johnnysc.picsandlogintestapp

import android.app.Application
import com.github.johnnysc.picsandlogintestapp.core.NetworkModule
import com.github.johnnysc.picsandlogintestapp.domain.pics.PicItemMapper
import com.github.johnnysc.picsandlogintestapp.domain.pics.PicsInteractorImpl
import com.github.johnnysc.picsandlogintestapp.data.pics.PicsRepositoryImpl
import com.github.johnnysc.picsandlogintestapp.domain.pics.PicsInteractor

/**
 * Кастомный класс для приложения
 *
 * @author Asatryan on 05.04.21
 **/
class ThisApp : Application() {

    lateinit var picsInteractor: PicsInteractor

    override fun onCreate() {
        super.onCreate()

        val networkModule = NetworkModule()
        picsInteractor =
            PicsInteractorImpl(
                PicsRepositoryImpl(
                    networkModule.getPicsService()
                ),
                PicItemMapper()
            )
    }
}