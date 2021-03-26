package com.shapee.sample

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.shapee.sample.utils.LocaleManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleApplication :MultiDexApplication(){

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.updateContext(base))
        MultiDex.install(this)
    }

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.updateContext(this)
    }
}