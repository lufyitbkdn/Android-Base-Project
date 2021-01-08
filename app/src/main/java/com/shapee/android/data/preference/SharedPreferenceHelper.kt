package com.shapee.android.data.preference

import android.content.Context
import com.shapee.android.model.Child
import com.google.gson.Gson

class SharedPreferenceHelper private constructor(context: Context) {
    private val sharedPreferenceHelper = context.getSharedPreferences("data", Context.MODE_PRIVATE)

    var accessToken: String?
        get() = sharedPreferenceHelper.getString(PREF_ACCESS_TOKEN, null).toString()
        set(token) {
            sharedPreferenceHelper.edit().putString(PREF_ACCESS_TOKEN, token).apply()
        }


    fun clearAll() {
        sharedPreferenceHelper.edit().clear().apply()
    }

    companion object {
        const val PREF_ACCESS_TOKEN = "access_token"

        private var mInstance: SharedPreferenceHelper? = null
        fun getInstance(context: Context): SharedPreferenceHelper {
            if (mInstance == null) {
                mInstance = SharedPreferenceHelper(context)
            }
            return mInstance!!
        }
    }

}