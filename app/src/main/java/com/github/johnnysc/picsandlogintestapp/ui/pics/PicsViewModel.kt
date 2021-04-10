package com.github.johnnysc.picsandlogintestapp.ui.pics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.picsandlogintestapp.ThisApp
import com.github.johnnysc.picsandlogintestapp.ui.pics.adapter.PicUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Вью модель для экрана изображений
 *
 * @author Asatryan on 31.03.21
 */
class PicsViewModel(app: Application) : AndroidViewModel(app) {

    val dataState = MutableLiveData<List<PicUiModel>>()

    private val mapper = (app as ThisApp).picsInstanceProvider.providePicsUiMapper()

    private val interactor =
        (app as ThisApp).picsInstanceProvider.providePicsInteractor()

    private var lastVisibleItemPos = -1

    /**
     * Отображаем начальное состояние экрана
     */
    init {
        dataState.value = mapper.map(interactor.getInitialData())
    }

    /**
     * Получаем данные
     */
    fun loadData() = viewModelScope.launch(Dispatchers.IO) {
        dataState.postValue(mapper.map(interactor.getData()))
    }

    /**
     * Нужно ли загружать еще данные
     * Проверяем что юзер проскролил до дна списка
     */
    fun loadMoreData(lastVisibleItemPosition: Int): Boolean {
        if (lastVisibleItemPosition != lastVisibleItemPos) {
            if (interactor.needToLoadMoreData(lastVisibleItemPosition)) {
                lastVisibleItemPos = lastVisibleItemPosition
                return true
            }
        }
        return false
    }
}