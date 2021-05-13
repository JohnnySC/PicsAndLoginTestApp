package com.github.johnnysc.picsandlogintestapp.domain.pics

import com.github.johnnysc.picsandlogintestapp.core.ExceptionType

/**
 * Изображение для бизнес логики
 *
 * @author Asatryan on 06.04.21
 **/
sealed class PicItem {
    abstract fun <T> map(mapper: PicItemUiMapper<T>): T
    class Base(private val text: String, private val url: String) : PicItem() {
        override fun <T> map(mapper: PicItemUiMapper<T>) = mapper.map(text, url)
    }

    class Error(private val exceptionType: ExceptionType = ExceptionType.GENERIC) : PicItem() {
        override fun <T> map(mapper: PicItemUiMapper<T>) = mapper.map(exceptionType)
    }
}

interface PicItemUiMapper<T> {

    fun map(text: String, url: String): T

    fun map(exceptionType: ExceptionType): T
}