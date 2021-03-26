package com.shapee.sample.data.sharedpref

import androidx.lifecycle.MutableLiveData

interface SharedPreferencesHelper {
    val currentUserId: MutableLiveData<String>
    fun setCurrentUserId(storeId: String)
    fun getCurrentUserId(): String
}