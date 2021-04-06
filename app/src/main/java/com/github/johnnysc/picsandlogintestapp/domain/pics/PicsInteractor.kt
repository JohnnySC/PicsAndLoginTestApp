package com.github.johnnysc.picsandlogintestapp.domain.pics

/**
 * Интерактор для изображений
 *
 * @author Asatryan on 05.04.21
 **/
interface PicsInteractor {

    /**
     * @return начальный список данных
     */
    fun getInitialData(): List<PicItem>

    /**
     * @return данные бизнес логики
     */
    suspend fun getData(): List<PicItem>

    /**
     * @param lastVisibleItemPosition позиция последнего видимого элемента
     */
    fun needToLoadMoreData(lastVisibleItemPosition: Int): Boolean
}