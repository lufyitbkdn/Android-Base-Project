package com.shapee.android.utils

interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}