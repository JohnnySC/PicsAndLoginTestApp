package com.github.johnnysc.picsandlogintestapp.ui.pics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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

    private val _dataState = MutableLiveData<List<PicUiModel>>()
    val dataState: LiveData<List<PicUiModel>>
        get() = _dataState

    private val mapper = (app as ThisApp).picsInstanceProvider.providePicsUiMapper()

    private val interactor =
        (app as ThisApp).picsInstanceProvider.providePicsInteractor()

    private var lastVisibleItemPos = -1

    /**
     * Отображаем начальное состояние экрана
     */
    init {
        _dataState.value = mapper.map(interactor.getInitialData())
    }

    /**
     * Получаем данные
     */
    fun loadData() = viewModelScope.launch(Dispatchers.IO) {
        _dataState.postValue(mapper.map(interactor.getData()))
    }

    /**
     * Нужно ли загружать еще данные
     * Проверяем что юзер проскролил до дна списка
     */
    fun loadMoreData(lastVisibleItemPosition: Int) {
        if (lastVisibleItemPosition != lastVisibleItemPos) {
            if (interactor.needToLoadMoreData(lastVisibleItemPosition)) {
                lastVisibleItemPos = lastVisibleItemPosition
                loadData()
            }
        }
    }
}