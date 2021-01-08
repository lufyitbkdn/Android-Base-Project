package com.shapee.android.ui.activity.home

import android.app.Application
import com.shapee.android.data.local.LocalDataManager
import com.shapee.android.data.preference.SharedPreferenceHelper
import com.shapee.android.ui.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val application: Application,
    val sharedPreferenceHelper: SharedPreferenceHelper,
    val localDataManager: LocalDataManager
) : BaseViewModel<HomeNavigator>() {


}