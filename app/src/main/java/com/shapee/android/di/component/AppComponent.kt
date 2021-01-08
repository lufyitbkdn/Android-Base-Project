package com.shapee.android.di.component

import android.app.Application
import com.shapee.android.di.module.*
import com.shapee.android.di.module.AppModule
import com.shapee.android.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidInjectionModule::class, NetworkModule::class, AppModule::class, ActivityModule::class, FragmentModule::class,
        ViewModelFactoryModule::class, ViewModelsModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}