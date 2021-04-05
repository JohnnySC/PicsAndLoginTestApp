package com.github.johnnysc.picsandlogintestapp.data.pics

import com.github.johnnysc.picsandlogintestapp.core.stringSuspending
import com.github.johnnysc.picsandlogintestapp.domain.pics.PicsRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

/**
 * Основная реализация репозитория изображений
 *
 * @author Asatryan on 05.04.21
 **/
class PicsRepositoryImpl(private val service: PicsService) : PicsRepository {

    private val gson = Gson()
    private val type = object : TypeToken<List<PicDTO>>() {}.type

    private val dataList = ArrayList<PicDTO>()

    private var page = 0

    override fun getCachedData() = dataList

    override suspend fun getData(): List<PicDTO> {
        val data = service.getPicsAsync(page, 30)
        val list: List<PicDTO> = gson.fromJson(data.stringSuspending(), type)
        if (list.isNotEmpty()) {
            dataList.addAll(list)
            page++
        }
        return dataList
    }
}