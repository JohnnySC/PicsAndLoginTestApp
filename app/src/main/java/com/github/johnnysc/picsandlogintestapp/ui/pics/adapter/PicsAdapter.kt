package com.github.johnnysc.picsandlogintestapp.ui.pics.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.picsandlogintestapp.R
import com.github.johnnysc.picsandlogintestapp.core.inflate

/**
 * Адаптер для списка изображений
 *
 * @author Asatryan on 01.04.21
 **/
class PicsAdapter(private val clickListener: PicsClickListener) :
    RecyclerView.Adapter<PicBaseViewHolder>() {

    private val items = ArrayList<PicUiModel>()

    /**
     * Обновляем данные в списке
     * @param list новый список элементов
     */
    fun setData(list: List<PicUiModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = items[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        PicUiModelType.BASIC.ordinal -> PicViewHolder(
            parent.inflate(R.layout.pic_layout)
        )
        PicUiModelType.FULL_SIZE_LOADER.ordinal -> FullSizeLoaderViewHolder(
            parent.inflate(R.layout.fullsize_loader_layout), clickListener
        )
        PicUiModelType.FULL_SIZE_ERROR.ordinal -> FullSizeErrorViewHolder(
            parent.inflate(R.layout.fullsize_error_layout), clickListener
        )
        PicUiModelType.BOTTOM_ERROR.ordinal -> BottomErrorViewHolder(
            parent.inflate(R.layout.bottom_error_layout), clickListener
        )
        else -> BottomLoaderViewHolder(parent.inflate(R.layout.bottom_loader_layout))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PicBaseViewHolder, position: Int) =
        holder.onBind(items[position])
}