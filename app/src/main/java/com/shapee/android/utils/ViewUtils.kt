package com.shapee.android.utils

import android.graphics.Paint
import android.widget.TextView

object ViewUtils {
    fun TextView.underline() {
        paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }
}