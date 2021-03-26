package com.shapee.sample.utils.extensions

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager


internal fun View.hideKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

internal fun View.show() {
    visibility = View.VISIBLE
}

internal fun View.hide() {
    visibility = View.GONE
}

internal fun View.shouldShow(shouldShow: Boolean) {
    if (shouldShow) show() else hide()
}

internal fun View.setMarginsInPixels(start: Int = 0, top: Int = 0, end: Int = 0, bottom: Int = 0) {
    if (this.layoutParams is ViewGroup.MarginLayoutParams) {
        val p = this.layoutParams as ViewGroup.MarginLayoutParams
        p.marginStart = start
        p.topMargin = top
        p.marginEnd = end
        p.bottomMargin = bottom
        this.requestLayout()
    }
}