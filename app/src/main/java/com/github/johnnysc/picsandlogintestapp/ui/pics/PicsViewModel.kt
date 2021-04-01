package com.github.johnnysc.picsandlogintestapp.ui.pics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author Asatryan on 31.03.21
 */
class PicsViewModel : ViewModel() {

    val dataState = MutableLiveData<List<PicUiModel>>()

    private val mapper = PicsUiMapper()
    private val interactor = StubPicsInteractor()

    private var count = 0
    private var secondCount = 0
    private var lastVisibleItemPos = -1

    fun init() {
        dataState.value = mapper.map(interactor.data)
    }

    /**
     * Имитируем перебои с сетью. В первый раз отдаем ошибку, при повторной попытке грузим данные
     */
    fun loadData() {
        if (count == 0) {
            dataState.value = mapper.map(interactor.addError())
            count++
        } else {
            interactor.addMoreData()
            init()
        }
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

    /**
     * Имитируем перебои с сетью при каждом третьем запросе
     */
    fun loadMoreData() {
        if (secondCount % 3 == 0) {
            dataState.value = mapper.map(interactor.addError())
        } else {
            interactor.addMoreData()
            dataState.value = mapper.map(interactor.data)
        }
        secondCount++
    }
}