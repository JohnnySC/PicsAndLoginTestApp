package com.github.johnnysc.picsandlogintestapp.ui.pics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.picsandlogintestapp.R

/**
 * @author Asatryan on 01.04.21
 **/
class PicsAdapter(private val clickListener: PicsClickListener) :
    RecyclerView.Adapter<PicsAdapter.PicBaseViewHolder>() {

    private val items = ArrayList<PicUiModel>()

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
        else -> BottomLoaderViewHolder(
            parent.inflate(R.layout.bottom_loader_layout)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PicBaseViewHolder, position: Int) =
        holder.onBind(items[position])

    abstract class PicBaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun onBind(model: PicUiModel) {}
    }

    class PicViewHolder(view: View) : PicBaseViewHolder(view) {
        override fun onBind(model: PicUiModel) {
            itemView.findViewById<TextView>(R.id.picDescriptionTextView).text =
                (model as Basic).text
//            todo picasso model.url
        }
    }

    class FullSizeLoaderViewHolder(view: View, private val clickListener: PicsClickListener) :
        PicBaseViewHolder(view) {
        override fun onBind(model: PicUiModel) = clickListener.loadData()
    }

    class BottomLoaderViewHolder(view: View) : PicBaseViewHolder(view)

    class FullSizeErrorViewHolder(view: View, private val clickListener: PicsClickListener) :
        PicBaseViewHolder(view) {
        override fun onBind(model: PicUiModel) =
            itemView.findViewById<View>(R.id.fullSizeErrorTryAgainButton).setOnClickListener {
                clickListener.tryLoadDataAgain()
            }
    }

    class BottomErrorViewHolder(view: View, private val clickListener: PicsClickListener) :
        PicBaseViewHolder(view) {
        override fun onBind(model: PicUiModel) =
            itemView.findViewById<View>(R.id.bottomErrorTryAgainButton).setOnClickListener {
                clickListener.tryLoadMoreDataAgain()
            }
    }

    private fun ViewGroup.inflate(@LayoutRes layoutResId: Int) =
        LayoutInflater.from(context).inflate(layoutResId, this, false)
}

interface PicsClickListener {

    fun loadData()

    fun tryLoadMoreDataAgain()

    fun tryLoadDataAgain()
}

abstract class PicUiModel(val type: PicUiModelType)

data class Basic(val text: String, val url: String) : PicUiModel(PicUiModelType.BASIC)
object FullSizeLoader : PicUiModel(PicUiModelType.FULL_SIZE_LOADER)
object BottomLoader : PicUiModel(PicUiModelType.BOTTOM_LOADER)
object FullSizeError : PicUiModel(PicUiModelType.FULL_SIZE_ERROR)
object BottomError : PicUiModel(PicUiModelType.BOTTOM_ERROR)

enum class PicUiModelType {
    BASIC,
    FULL_SIZE_LOADER,
    BOTTOM_LOADER,
    FULL_SIZE_ERROR,
    BOTTOM_ERROR
}