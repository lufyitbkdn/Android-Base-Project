package com.shapee.sample.data.sharedpref

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesHelperImp @Inject constructor(private val sharedPreferences: SharedPreferences) :
    SharedPreferencesHelper {

    override val currentUserId: MutableLiveData<String> = MutableLiveData(getCurrentUserId())

    override fun getCurrentUserId(): String =
        sharedPreferences.getString(PREF_USER_ID, "") ?: ""

    override fun setCurrentUserId(userId: String) {
        sharedPreferences.edit().apply {
            putString(PREF_USER_ID, userId)
        }.apply()
        this.currentUserId.postValue(userId)
    }

    companion object {
        const val PREF_USER_ID = "store_id"
    }
}
