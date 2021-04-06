package com.github.johnnysc.picsandlogintestapp.domain.pics

/**
 * Изображение для бизнес логики
 *
 * @author Asatryan on 06.04.21
 **/
sealed class PicItem {
    data class Base(val text: String, val url: String) : PicItem()
    object Error : PicItem()
}