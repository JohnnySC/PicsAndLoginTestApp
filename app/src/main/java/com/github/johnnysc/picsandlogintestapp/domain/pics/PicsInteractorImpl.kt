package com.github.johnnysc.picsandlogintestapp.domain.pics

import com.github.johnnysc.picsandlogintestapp.core.ExceptionHandler
import com.github.johnnysc.picsandlogintestapp.core.Mapper
import com.github.johnnysc.picsandlogintestapp.data.pics.PicDTO
import java.lang.Exception

/**
 * Интерактор для изображений, основная реализация
 *
 * @author Asatryan on 05.04.21
 **/
class PicsInteractorImpl(
    private val repository: PicsRepository,
    private val mapper: Mapper<List<PicItem>, List<PicDTO>>,
    private val exceptionHandler: ExceptionHandler
) : PicsInteractor {

    override fun getInitialData() = mapper.map(repository.getCachedData())

    override suspend fun getData() = try {
        mapper.map(repository.getData());
    } catch (e: Exception) {
        ArrayList<PicItem>().apply {
            addAll(getInitialData())
            add(PicItem.Error(exceptionHandler.defineExceptionType(e)))
        }
    }

    override fun needToLoadMoreData(lastVisibleItemPosition: Int): Boolean =
        with(repository.getCachedData()) {
            return isNotEmpty() && size - 1 == lastVisibleItemPosition
        }
}