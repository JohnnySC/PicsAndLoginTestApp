package com.github.johnnysc.picsandlogintestapp.ui.pics.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.picsandlogintestapp.R
import com.github.johnnysc.picsandlogintestapp.core.load

/**
 * Вьюхолдеры для разных видов на экране изображений
 *
 * @author Asatryan on 05.04.21
 **/
abstract class PicBaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    open fun onBind(model: PicUiModel) {}
}

class PicViewHolder(view: View) : PicBaseViewHolder(view) {
    override fun onBind(model: PicUiModel) = with(itemView) {
        findViewById<TextView>(R.id.picDescriptionTextView).text =
            (model as Basic).text
        findViewById<ImageView>(R.id.picImageView).load(model.url)
    }
}

class FullSizeLoaderViewHolder(view: View, private val clickListener: PicsClickListener) :
    PicBaseViewHolder(view) {
    override fun onBind(model: PicUiModel) = clickListener.loadData()
}

class BottomLoaderViewHolder(view: View) : PicBaseViewHolder(view)

class FullSizeErrorViewHolder(view: View, private val clickListener: PicsClickListener) :
    PicBaseViewHolder(view) {
    override fun onBind(model: PicUiModel) = with(itemView) {
        findViewById<View>(R.id.fullSizeErrorTryAgainButton).setOnClickListener {
            clickListener.tryLoadDataAgain()
        }
        findViewById<TextView>(R.id.fullSizeErrorTextView).text = (model as FullSizeError).message
    }
}

class BottomErrorViewHolder(view: View, private val clickListener: PicsClickListener) :
    PicBaseViewHolder(view) {
    override fun onBind(model: PicUiModel) = with(itemView) {
        findViewById<View>(R.id.bottomErrorTryAgainButton).setOnClickListener {
            clickListener.tryLoadMoreDataAgain()
        }
        findViewById<TextView>(R.id.bottomErrorTextView).text = (model as BottomError).message
    }
}