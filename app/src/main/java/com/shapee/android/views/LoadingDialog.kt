package com.shapee.android.views

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import com.shapee.android.R

class LoadingDialog(context: Context) :
    Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog_loading)
        val window = this.window
        val wlp = window?.attributes
        wlp?.let {
            it.gravity = Gravity.CENTER
            it.width = WindowManager.LayoutParams.MATCH_PARENT
            it.height = WindowManager.LayoutParams.MATCH_PARENT
            it.flags = it.flags and WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN
        }

        window?.attributes = wlp
        window?.setBackgroundDrawable(ColorDrawable(context.resources.getColor(R.color.colorBgDialog)))
        setCancelable(false)
    }


}
