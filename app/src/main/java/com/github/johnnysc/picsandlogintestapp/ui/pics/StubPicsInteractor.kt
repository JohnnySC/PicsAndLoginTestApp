package com.github.johnnysc.picsandlogintestapp.ui.pics

/**
 * Стабовый интерактор для отображения всех сценариев логики
 *
 * @author Asatryan on 01.04.21
 **/
class StubPicsInteractor {

    private val repository = StubPicsRepository()

    val data: List<PicItem>
        get() = repository.data

    fun addMoreData() {
        repository.addMoreData()
    }

    fun addError() = ArrayList<PicItem>().apply {
        addAll(data)
        add(PicItem.Error)
    }

    fun needToLoadMoreData(lastVisibleItemPosition: Int): Boolean {
        return data.isNotEmpty() && data.size - 1 == lastVisibleItemPosition
    }

}