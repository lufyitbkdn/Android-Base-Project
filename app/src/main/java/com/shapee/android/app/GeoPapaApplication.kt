package com.shapee.android.app

import androidx.multidex.MultiDex
import com.shapee.android.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class GeoPapaApplication : DaggerApplication(){


    override fun onCreate() {
        MultiDex.install(this)
        super.onCreate()
//        Places.initialize(this, "AIzaSyCdi9bMOGb27AZCjOxhSy1MwkV55ZFRMjc")
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

}