package com.github.johnnysc.picsandlogintestapp.core

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

/**
 * @author Asatryan on 15.05.21
 **/
class MyTextView : androidx.appcompat.widget.AppCompatTextView, TextContainer {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(text: String) {
        setText(text)
    }
}

class MySnackbar(private val snackbar: Snackbar) : TextContainer {

    override fun show(text: String) = with(snackbar) {
        setText(text)
        show()
    }
}

interface TextContainer {

    fun show(text: String)
}

class MyImageView : androidx.appcompat.widget.AppCompatImageView, ImageContainer {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(url: String) {
        load(url)
    }
}

interface ImageContainer {

    fun show(url: String)
}

class MyTextInputLayout : TextInputLayout, ErrorContainer {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(show: Boolean, message: String) {
        isErrorEnabled = show
        error = message
    }
}

interface ErrorContainer {

    fun show(show: Boolean, message: String)
}

class MyFrameLayout : FrameLayout, ViewContainer {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(show: Boolean) {
        visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun enable(enable: Boolean) {
        isEnabled = enable
    }
}

class MyButton : androidx.appcompat.widget.AppCompatButton, ViewContainer {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(show: Boolean) {
        visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun enable(enable: Boolean) {
        isEnabled = enable
    }
}

interface ViewContainer {

    fun show(show: Boolean)

    fun enable(enable: Boolean)
}