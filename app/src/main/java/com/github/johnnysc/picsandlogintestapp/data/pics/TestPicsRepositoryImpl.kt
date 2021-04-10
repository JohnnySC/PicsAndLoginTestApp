package com.github.johnnysc.picsandlogintestapp.data.pics

import com.github.johnnysc.picsandlogintestapp.domain.pics.PicsRepository
import java.net.UnknownHostException

/**
 * Тестовая реализация репозитория изображений
 *
 * @author Asatryan on 10.04.21
 **/
class TestPicsRepositoryImpl : PicsRepository {

    private val dataList = ArrayList<PicDTO>()

    var count = -1

    override fun getCachedData() = dataList

    override suspend fun getData(): List<PicDTO> {
        count++
        if (count % 3 == 0)//имитируем проблемы с сетью каждый третий раз
            throw UnknownHostException()

        dataList.addAll(generateList())
        return dataList
    }

    private fun generateList(): List<PicDTO> {
        val list = ArrayList<PicDTO>()
        for (i in 0..10) {
            val size = dataList.size + list.size
            list.add(
                PicDTO(
                    size.toString(),
                    "stubAuthor $size",
                    "stub",
                    "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"
                )
            )
        }
        return list
    }
}