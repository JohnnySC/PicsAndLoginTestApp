package com.github.johnnysc.picsandlogintestapp.domain.pics

import com.github.johnnysc.picsandlogintestapp.core.ExceptionType

/**
 * Изображение для бизнес логики
 *
 * @author Asatryan on 06.04.21
 **/
sealed class PicItem {
    data class Base(val text: String, val url: String) : PicItem()
    data class Error(val exceptionType: ExceptionType = ExceptionType.GENERIC) : PicItem()
}