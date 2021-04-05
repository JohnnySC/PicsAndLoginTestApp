package com.github.johnnysc.picsandlogintestapp.ui.pics.adapter

/**
 * Слушатель событий списка изображений
 *
 * @author Asatryan on 05.04.21
 **/
interface PicsClickListener {

    /**
     * Загрузить начальные данные
     */
    fun loadData()

    /**
     * Попробовать загрузить еще данные
     */
    fun tryLoadDataAgain()

    /**
     * Попробовать загрузить еще данные повторно
     */
    fun tryLoadMoreDataAgain()
}