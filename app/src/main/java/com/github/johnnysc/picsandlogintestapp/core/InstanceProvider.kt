package com.github.johnnysc.picsandlogintestapp.core

import com.github.johnnysc.picsandlogintestapp.data.login.WeatherDTO
import com.github.johnnysc.picsandlogintestapp.data.pics.PicDTO
import com.github.johnnysc.picsandlogintestapp.domain.login.*
import com.github.johnnysc.picsandlogintestapp.domain.pics.*
import com.github.johnnysc.picsandlogintestapp.ui.login.WeatherUiModel
import com.github.johnnysc.picsandlogintestapp.ui.pics.adapter.PicUiModel

/**
 * Провайдер инстансов
 *
 * @author Asatryan on 10.04.21
 **/
interface InstanceProvider : LoginInstancesProvider, PicsInstancesProvider {

    fun provideExceptionHandler(): ExceptionHandler

    fun provideResourceManager(): ResourceManager
}

interface LoginInstancesProvider {

    fun provideWeatherUiMapper(): WeatherUiMapper<WeatherUiModel>

    fun provideWeatherItemMapper(): Mapper<WeatherItem, WeatherDTO>

    fun provideLoginRepository(): LoginRepository

    fun provideLoginInteractor(): LoginInteractor
}

interface PicsInstancesProvider {

    fun providePicsUiMapper(): Mapper<List<PicUiModel>, List<PicItem>>

    fun providePicItemMapper(): Mapper<List<PicItem>, List<PicDTO>>

    fun providePicsRepository(): PicsRepository

    fun providePicsInteractor(): PicsInteractor
}