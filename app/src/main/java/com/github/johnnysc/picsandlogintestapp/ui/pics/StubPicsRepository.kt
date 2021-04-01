package com.github.johnnysc.picsandlogintestapp.ui.pics

/**
 * Стабовый репозиторий для хранения фейковых данных картинок
 *
 * @author Asatryan on 01.04.21
 **/
class StubPicsRepository {

    val data = ArrayList<PicItem>()

    fun addMoreData() {
        for (i in 0..4)
            data.add(PicItem.Base("txt0${data.size}", "url01"))
    }
}