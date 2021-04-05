package com.github.johnnysc.picsandlogintestapp.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

/**
 * Экстеншн функции
 *
 * @author Asatryan on 05.04.21
 **/

@Suppress("BlockingMethodInNonBlockingContext")
suspend fun ResponseBody.stringSuspending(): String =
    withContext(Dispatchers.IO) { string() }

fun ImageView.load(url: String) {
    val width = this.context.resources.displayMetrics.widthPixels
    Picasso.get().load(url)
        .centerInside()
        .resize(width / 2, width / 4)
        .onlyScaleDown()
        .into(this)
}

fun ViewGroup.inflate(@LayoutRes layoutResId: Int): View =
    LayoutInflater.from(context).inflate(layoutResId, this, false)