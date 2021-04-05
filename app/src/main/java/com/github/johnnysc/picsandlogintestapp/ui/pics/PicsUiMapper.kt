package com.github.johnnysc.picsandlogintestapp.ui.pics

import com.github.johnnysc.picsandlogintestapp.core.Mapper
import com.github.johnnysc.picsandlogintestapp.ui.pics.adapter.*

/**
 * Мапим данные логики к данным юай слоя
 *
 * @author Asatryan on 01.04.21
 **/
class PicsUiMapper :
    Mapper<List<PicUiModel>, List<PicItem>> {

    override fun map(source: List<PicItem>): List<PicUiModel> {
        val result = ArrayList<PicUiModel>()
        when {
            source.isEmpty() -> result.add(FullSizeLoader)
            source.size == 1 && source[0] is PicItem.Error -> result.add(FullSizeError)
            source.last() is PicItem.Base -> {
                result.addAll(
                    source.map {
                        it as PicItem.Base
                        Basic(
                            it.text,
                            it.url
                        )
                    }
                )
                result.add(BottomLoader)
            }
            source.last() is PicItem.Error -> {
                for (item in source) {
                    if (item is PicItem.Base)
                        result.add(
                            Basic(
                                item.text,
                                item.url
                            )
                        )
                }
                result.add(BottomError)
            }
        }
        return result
    }
}

sealed class PicItem {
    data class Base(val text: String, val url: String) : PicItem()
    object Error : PicItem()
}