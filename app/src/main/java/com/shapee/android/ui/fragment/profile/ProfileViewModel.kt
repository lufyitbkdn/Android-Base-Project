package com.shapee.android.ui.fragment.profile

import android.app.Application
import com.shapee.android.data.local.LocalDataManager
import com.shapee.android.ui.base.BaseViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(val localDataManager: LocalDataManager,
                                           private val application: Application) :
    BaseViewModel<ProfileNavigator>() {
    fun showMessage(){
        mNavigator?.showMessage()
    }
}
