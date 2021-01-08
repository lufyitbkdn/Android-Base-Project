package com.shapee.android.utils

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern


object StringUtils{
    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

}