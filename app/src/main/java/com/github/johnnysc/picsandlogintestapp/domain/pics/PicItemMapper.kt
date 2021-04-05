package com.github.johnnysc.picsandlogintestapp.domain.pics

import com.github.johnnysc.picsandlogintestapp.core.Mapper
import com.github.johnnysc.picsandlogintestapp.data.pics.PicDTO
import com.github.johnnysc.picsandlogintestapp.ui.pics.PicItem

/**
 * Мапим данные с сервера к данным бизнес логики
 *
 * @author Asatryan on 05.04.21
 **/
class PicItemMapper : Mapper<List<PicItem>, List<PicDTO>> {

    override fun map(source: List<PicDTO>) = source.map {
        PicItem.Base(
            it.author,
            it.downloadUrl
        )
    }
}