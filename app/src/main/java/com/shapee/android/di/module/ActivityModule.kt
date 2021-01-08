package com.shapee.android.di.module

import com.shapee.android.di.annotaton.ActivityScope
import com.shapee.android.ui.activity.home.HomeActivity
import com.shapee.android.ui.activity.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector
    fun contributeSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun contributeHomeActivity(): HomeActivity
}
