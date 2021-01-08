package com.shapee.android.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {
    @JvmStatic
    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}