package com.shapee.android.views

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import com.shapee.android.R
import kotlinx.android.synthetic.main.dialog_message.*

class MessageDialog(context: Context) :
    Dialog(context, R.style.Theme_AppCompat_Dialog) {
    var message: String = ""
        set(value) {
            field = value
            tvMessage?.text = message
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog_message)
        tvMessage.text = message
        tvOk.setOnClickListener {
            dismiss()
        }
        val window = this.window
        val wlp = window?.attributes
        wlp?.let {
            it.gravity = Gravity.CENTER
            it.width = WindowManager.LayoutParams.MATCH_PARENT
            it.height = WindowManager.LayoutParams.MATCH_PARENT
            it.flags = it.flags and WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN
        }

        window?.attributes = wlp
        getWindow()?.setBackgroundDrawable(ColorDrawable(context.resources.getColor(R.color.colorBgDialog)))
        this.window?.attributes = wlp
        setCancelable(false)
    }

}