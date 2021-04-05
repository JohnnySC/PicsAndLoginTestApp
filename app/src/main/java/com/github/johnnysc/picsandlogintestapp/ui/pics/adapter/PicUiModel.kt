package com.github.johnnysc.picsandlogintestapp.ui.pics.adapter

/**
 * Модель изображения для юай слоя
 *
 * @author Asatryan on 05.04.21
 **/
abstract class PicUiModel(val type: PicUiModelType)

/**
 * Базовый класс отображения изображения
 */
data class Basic(val text: String, val url: String) : PicUiModel(PicUiModelType.BASIC)

/**
 * Полноэкранный загрузчик
 */
object FullSizeLoader : PicUiModel(PicUiModelType.FULL_SIZE_LOADER)

/**
 * Загрузчик на дне экрана
 */
object BottomLoader : PicUiModel(PicUiModelType.BOTTOM_LOADER)

/**
 * Полноэкранная ошибка
 */
object FullSizeError : PicUiModel(PicUiModelType.FULL_SIZE_ERROR)

/**
 * Ошибка на дне экрана
 */
object BottomError : PicUiModel(PicUiModelType.BOTTOM_ERROR)

/**
 * Виды элементов списка изображений
 */
enum class PicUiModelType {
    BASIC,
    FULL_SIZE_LOADER,
    BOTTOM_LOADER,
    FULL_SIZE_ERROR,
    BOTTOM_ERROR
}