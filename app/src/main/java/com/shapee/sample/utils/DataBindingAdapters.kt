package com.shapee.sample.utils

import android.view.View
import androidx.databinding.BindingAdapter

object DataBindingAdapters {
    interface OnDebouncedClicked {
        fun click()
    }

    @BindingAdapter("app:deBouncedOnClick")
    @JvmStatic
    fun setDebouncedOnClick(view: View, onDebouncedClicked: OnDebouncedClicked) {
        val onClickListener = object : DebouncedOnClickListener() {
            override fun onDebouncedClick(v: View?) {
                onDebouncedClicked.click()
            }
        }
        view.setOnClickListener {
            onClickListener.onClick(view)
        }
    }
}