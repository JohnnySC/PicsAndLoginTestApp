package com.github.johnnysc.picsandlogintestapp.ui.pics.adapter

import android.widget.ImageView
import android.widget.TextView
import com.github.johnnysc.picsandlogintestapp.core.load

/**
 * Модель изображения для юай слоя
 *
 * @author Asatryan on 05.04.21
 **/
abstract class PicUiModel(val type: PicUiModelType) {
    open fun show(textView: TextView) {}
    open fun show(textView: TextView, imageView: ImageView) {}
}

/**
 * Базовый класс отображения изображения
 */
class Basic(private val text: String, private val url: String) :
    PicUiModel(PicUiModelType.BASIC) {

    override fun show(textView: TextView, imageView: ImageView) {
        textView.text = text
        imageView.load(url)
    }
}

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
class FullSizeError(message: String) : AnyError(message, PicUiModelType.FULL_SIZE_ERROR)

/**
 * Ошибка на дне экрана
 */
class BottomError(message: String) : AnyError(message, PicUiModelType.BOTTOM_ERROR)

abstract class AnyError(private val message: String, type: PicUiModelType) : PicUiModel(type) {
    override fun show(textView: TextView) = textView.setText(message)
}

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