package com.shapee.android.di.module

import com.shapee.android.ui.fragment.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment
}
