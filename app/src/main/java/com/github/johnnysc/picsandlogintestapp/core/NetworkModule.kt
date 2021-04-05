package com.github.johnnysc.picsandlogintestapp.core

import com.github.johnnysc.picsandlogintestapp.data.pics.PicsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Модуль для работы с сетью
 *
 * @author Asatryan on 05.04.21
 **/
class NetworkModule {

    companion object {
        const val BASE_URL = "https://picsum.photos/v2/";
    }

    private var retrofit: Retrofit? = null
    private var picsService: PicsService? = null

    private fun getRetrofit(): Retrofit {
        if (retrofit == null)
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit!!
    }

    fun getPicsService(): PicsService {
        if (picsService == null)
            picsService = getRetrofit().create(PicsService::class.java)
        return picsService!!
    }
}