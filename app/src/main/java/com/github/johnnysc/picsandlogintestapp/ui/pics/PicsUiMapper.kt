package com.github.johnnysc.picsandlogintestapp.ui.pics

import com.github.johnnysc.picsandlogintestapp.core.ExceptionType
import com.github.johnnysc.picsandlogintestapp.core.Mapper
import com.github.johnnysc.picsandlogintestapp.core.ResourceManager
import com.github.johnnysc.picsandlogintestapp.domain.pics.PicItem
import com.github.johnnysc.picsandlogintestapp.domain.pics.PicItemUiMapper
import com.github.johnnysc.picsandlogintestapp.ui.pics.adapter.*
import java.lang.IllegalArgumentException

/**
 * Мапим данные логики к данным юай слоя
 *
 * @author Asatryan on 01.04.21
 **/
class PicsUiMapper(resourceManager: ResourceManager) : Mapper<List<PicUiModel>, List<PicItem>> {

    private val fullSizeErrorMapper = PicUiMapper(PicUiModelType.FULL_SIZE_ERROR, resourceManager)
    private val bottomError = PicUiMapper(PicUiModelType.BOTTOM_ERROR, resourceManager)
    private val baseMapper = PicUiMapper(PicUiModelType.BASIC, resourceManager)

    override fun map(source: List<PicItem>): List<PicUiModel> {
        val result = ArrayList<PicUiModel>()
        when {
            source.isEmpty() -> result.add(FullSizeLoader)
            source.size == 1 && source[0] is PicItem.Error ->
                result.add(source[0].map(fullSizeErrorMapper))
            source.last() is PicItem.Base -> {
                result.addAll(
                    source.map {
                        it.map(baseMapper)
                    }
                )
                result.add(BottomLoader)
            }
            source.last() is PicItem.Error -> {
                for (item in source) {
                    if (item is PicItem.Base)
                        result.add(item.map(baseMapper))
                }
                result.add(source.last().map(bottomError))
            }
        }
        return result
    }
}

class PicUiMapper(
    private val type: PicUiModelType,
    private val resourceManager: ResourceManager
) : PicItemUiMapper<PicUiModel> {

    override fun map(text: String, url: String) = if (type == PicUiModelType.BASIC)
        Basic(text, url)
    else
        throw IllegalArgumentException("using basic mapper for error")

    override fun map(exceptionType: ExceptionType) =
        resourceManager.getErrorMessage(exceptionType).let {
            if (type == PicUiModelType.FULL_SIZE_ERROR)
                FullSizeError(it)
            else
                BottomError(it)
        }
}