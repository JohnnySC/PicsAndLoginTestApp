package com.github.johnnysc.picsandlogintestapp.core

import android.text.Editable
import android.text.TextWatcher

/**
 * Обертка над TextWatcher для более удобного использования
 *
 * @author Asatryan on 31.03.21
 **/
abstract class SimpleTextChangeListener : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}