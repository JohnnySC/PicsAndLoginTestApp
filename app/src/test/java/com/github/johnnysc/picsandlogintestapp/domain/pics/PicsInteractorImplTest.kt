package com.github.johnnysc.picsandlogintestapp.domain.pics

import com.github.johnnysc.picsandlogintestapp.core.ExceptionHandlerImpl
import com.github.johnnysc.picsandlogintestapp.data.pics.PicDTO
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test
import java.net.UnknownHostException

/**
 * Тест для базовой реализации интерактора изображений
 *
 * @see PicsInteractorImpl
 *
 * @author Asatryan on 10.04.21
 */
class PicsInteractorImplTest {

    private val mapper = PicItemMapper()
    private val exceptionHandler = ExceptionHandlerImpl()

    @Test
    fun test_positive_case() = runBlocking {
        val repository = TestPicsRepositoryImpl(true)
        val interactor = PicsInteractorImpl(repository, mapper, exceptionHandler)

        val initialData = interactor.getInitialData()
        assertThat(initialData.size, `is`(6))

        var needLoadMoreData = interactor.needToLoadMoreData(5)
        assertThat(needLoadMoreData, `is`(true))
        for (i in 0..4) {
            needLoadMoreData = interactor.needToLoadMoreData(i)
            assertThat(needLoadMoreData, `is`(false))
        }

        val data = interactor.getData()
        assertThat(data.size, `is`(10))
        for (item in data) {
            assertThat(item is PicItem.Base, `is`(true))
        }
    }

    @Test
    fun test_negative_case() = runBlocking {
        val repository = TestPicsRepositoryImpl(false)
        val interactor = PicsInteractorImpl(repository, mapper, exceptionHandler)

        val initialData = interactor.getInitialData()
        assertThat(initialData.size, `is`(0))

        for (i in 0..5) {
            val needLoadMoreData = interactor.needToLoadMoreData(i)
            assertThat(needLoadMoreData, `is`(false))
        }

        val data = interactor.getData()
        assertThat(data.size, `is`(1))
        assertThat(data[0] is PicItem.Error, `is`(true))
    }

    private inner class TestPicsRepositoryImpl(private val getSuccess: Boolean) : PicsRepository {
        override fun getCachedData(): ArrayList<PicDTO> {
            return ArrayList<PicDTO>().apply {
                if (getSuccess) {
                    for (i in 0..5)
                        add(PicDTO("id$i", "author$i", "url1$i", "url2$i"))

                }
            }
        }

        override suspend fun getData(): List<PicDTO> {
            return if (getSuccess) {
                ArrayList<PicDTO>().apply {
                    if (getSuccess) {
                        for (i in 0..9)
                            add(PicDTO("id$i", "author$i", "url1$i", "url2$i"))

                    }
                }
            } else
                throw UnknownHostException()
        }
    }
}