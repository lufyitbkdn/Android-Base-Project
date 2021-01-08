package com.shapee.android.di.module

import androidx.lifecycle.ViewModel
import com.shapee.android.di.annotaton.ViewModelKey
import com.shapee.android.ui.activity.home.HomeViewModel
import com.shapee.android.ui.activity.splash.SplashViewModel
import com.shapee.android.ui.fragment.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}