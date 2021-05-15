package com.github.johnnysc.picsandlogintestapp.ui.pics.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.picsandlogintestapp.databinding.BottomErrorLayoutBinding
import com.github.johnnysc.picsandlogintestapp.databinding.FullsizeErrorLayoutBinding
import com.github.johnnysc.picsandlogintestapp.databinding.PicLayoutBinding

/**
 * Вьюхолдеры для разных видов на экране изображений
 *
 * @author Asatryan on 05.04.21
 **/
abstract class PicBaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    open fun onBind(model: PicUiModel) {}
}

class PicViewHolder(view: View) : PicBaseViewHolder(view) {
    private val binding = PicLayoutBinding.bind(view)
    override fun onBind(model: PicUiModel) = with(binding) {
        model.show(picDescriptionTextView, picImageView)
    }
}

class FullSizeLoaderViewHolder(view: View, private val clickListener: PicsClickListener) :
    PicBaseViewHolder(view) {
    override fun onBind(model: PicUiModel) = clickListener.loadData()
}

class BottomLoaderViewHolder(view: View) : PicBaseViewHolder(view)

class FullSizeErrorViewHolder(view: View, private val clickListener: PicsClickListener) :
    PicBaseViewHolder(view) {
    private val binding = FullsizeErrorLayoutBinding.bind(view)
    override fun onBind(model: PicUiModel) = with(binding) {
        fullSizeErrorTryAgainButton.setOnClickListener {
            clickListener.tryLoadDataAgain()
        }
        model.show(fullSizeErrorTextView)
    }
}

class BottomErrorViewHolder(view: View, private val clickListener: PicsClickListener) :
    PicBaseViewHolder(view) {
    private val binding = BottomErrorLayoutBinding.bind(view)
    override fun onBind(model: PicUiModel) = with(binding) {
        bottomErrorTryAgainButton.setOnClickListener {
            clickListener.tryLoadMoreDataAgain()
        }
        model.show(bottomErrorTextView)
    }
}