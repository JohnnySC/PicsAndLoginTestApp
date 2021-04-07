package com.github.johnnysc.picsandlogintestapp.core

import com.github.johnnysc.picsandlogintestapp.data.login.LoginService
import com.github.johnnysc.picsandlogintestapp.data.pics.PicsService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Модуль для работы с сетью
 *
 * @author Asatryan on 05.04.21
 **/
class NetworkModule {

    companion object {
        const val PICS_BASE_URL = "https://picsum.photos/v2/";
        const val LOGIN_BASE_URL = "https://api.openweathermap.org/data/2.5/";
    }

    private val converterFactory: GsonConverterFactory = GsonConverterFactory.create()

    fun getPicsService(): PicsService {
        val picsRetrofit = Retrofit.Builder()
            .baseUrl(PICS_BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
        return picsRetrofit.create(PicsService::class.java)
    }

    fun getLoginService(): LoginService {
        val loginClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor {
                val original = it.request()
                val originalUrl = original.url
                val url = originalUrl.newBuilder()
                    .addQueryParameter("appid", "c35880b49ff95391b3a6d0edd0c722eb")
                    .build()
                val request = original.newBuilder()
                    .url(url)
                    .build()
                it.proceed(request)
            })
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val loginRetrofit = Retrofit.Builder()
            .baseUrl(LOGIN_BASE_URL)
            .addConverterFactory(converterFactory)
            .client(loginClient)
            .build()
        return loginRetrofit.create(LoginService::class.java)
    }
}