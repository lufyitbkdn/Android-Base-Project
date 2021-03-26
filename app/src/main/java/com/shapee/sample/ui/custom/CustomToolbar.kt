package com.shapee.sample.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.shapee.sample.R
import kotlinx.android.synthetic.main.view_custom_toolbar.view.*

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs) {
    private var text: String = ""
    private var showBack: Boolean = true
    private var centerTitle: Boolean = true
    private var textSize: Float = 20f

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.view_custom_toolbar, this)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomToolbar,
            0, 0
        ).apply {

            try {
                text = getString(R.styleable.CustomToolbar_title) ?: ""
                showBack = getBoolean(R.styleable.CustomToolbar_showBack, true)
                centerTitle = getBoolean(R.styleable.CustomToolbar_centerTitle, true)
                textSize = getDimensionPixelSize(R.styleable.CustomToolbar_fontSize, 18).toFloat()
            } finally {
                recycle()
            }
        }
        ibToolbarBack.visibility = if (showBack) View.VISIBLE else View.GONE
        tvToolbarText.text = text
        tvToolbarText.gravity = if (centerTitle) Gravity.CENTER else Gravity.START
        tvToolbarText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
    }


    fun setBackClicked(onBackClicked: () -> Unit) {
        ibToolbarBack.setOnClickListener {
            onBackClicked.invoke()
        }
    }

    fun setTitle(title: String) {
        text = title
        tvToolbarText.text = title
    }

    companion object {
        @BindingAdapter("app:onBackClickListener")
        @JvmStatic
        fun setOnClickListener(toolbar: CustomToolbar, onClickListener: () -> Unit) {
            toolbar.setBackClicked {
                onClickListener()
            }
        }

        @BindingAdapter("app:title")
        @JvmStatic
        fun setTitle(toolbar: CustomToolbar, title: String) {
            toolbar.setTitle(title)
        }
    }
}