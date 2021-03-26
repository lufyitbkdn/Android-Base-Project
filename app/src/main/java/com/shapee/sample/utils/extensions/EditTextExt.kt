package com.shapee.sample.utils.extensions

import android.os.Build
import android.widget.EditText

internal fun EditText.hideSoftInputOnFocus(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        this.showSoftInputOnFocus = false
    else
        this.setTextIsSelectable(true)
}

internal fun EditText.setTextAndMoveCursorToEnd(textToSet: String){
    setText(textToSet)
    setSelection(text.toString().length)
}