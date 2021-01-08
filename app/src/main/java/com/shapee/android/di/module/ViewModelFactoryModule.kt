package com.shapee.android.di.module

import androidx.lifecycle.ViewModelProvider
import com.shapee.android.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun provideViewModelFactory (viewModelProviderFactory: ViewModelFactory) : ViewModelProvider.Factory
}