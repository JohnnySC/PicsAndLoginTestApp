package com.github.johnnysc.picsandlogintestapp.data.pics

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Сервис для получения изображений
 *
 * @author Asatryan on 05.04.21
 **/
interface PicsService {

    @GET("list")
    suspend fun getPicsAsync(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): ResponseBody
}