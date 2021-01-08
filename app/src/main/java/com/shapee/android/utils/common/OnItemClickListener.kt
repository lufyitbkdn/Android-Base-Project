package com.shapee.android.utils.common

interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}