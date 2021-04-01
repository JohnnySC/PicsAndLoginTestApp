package com.github.johnnysc.picsandlogintestapp.ui.pics

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

/**
 * @author Asatryan on 01.04.21
 */
class PicsUiMapperTest {

    private val mapper = PicsUiMapper()

    @Test
    fun test_empty_list() {
        val source = emptyList<PicItem>()
        val result = mapper.map(source)
        assertThat(result.size, `is`(1))
        assertThat(result[0] is FullSizeLoader, `is`(true))
    }

    @Test
    fun test_single_error() {
        val source = listOf(PicItem.Error)
        val result = mapper.map(source)
        assertThat(result.size, `is`(1))
        assertThat(result[0] is FullSizeError, `is`(true))
    }

    @Test
    fun test_basic_case() {
        val source = listOf(
            PicItem.Base("txt1", "url1"),
            PicItem.Base("txt2", "url2"),
            PicItem.Base("txt3", "url3"),
            PicItem.Base("txt4", "url4"),
            PicItem.Base("txt5", "url5")
        )
        val result = mapper.map(source)
        assertThat(result.size, `is`(source.size + 1))
        result.forEachIndexed { index, item ->
            if (index < result.size - 1)
                assertThat(item.type == PicUiModelType.BASIC, `is`(true))
            else
                assertThat(item.type == PicUiModelType.BOTTOM_LOADER, `is`(true))
        }
    }

    @Test
    fun test_error_after_data_case() {
        val source = listOf(
            PicItem.Base("txt1", "url1"),
            PicItem.Base("txt2", "url2"),
            PicItem.Base("txt3", "url3"),
            PicItem.Base("txt4", "url4"),
            PicItem.Base("txt5", "url5"),
            PicItem.Error
        )
        val result = mapper.map(source)
        result.forEachIndexed { index, item ->
            if (index < result.size - 1)
                assertThat(item.type == PicUiModelType.BASIC, `is`(true))
            else
                assertThat(item.type == PicUiModelType.BOTTOM_ERROR, `is`(true))
        }
    }
}