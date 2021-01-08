package com.shapee.android.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.shapee.android.data.local.AppDatabase
import com.shapee.android.data.local.LocalDataManager
import com.shapee.android.data.preference.SharedPreferenceHelper
import com.shapee.android.di.annotaton.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
internal object AppModule {
    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    fun provideSharedPreferenceHelper(application: Application) =  SharedPreferenceHelper.getInstance(application)

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase::class.java.simpleName)
            .allowMainThreadQueries()/*.addMigrations(AppDatabase.MIGRATION_1_2)*/
            .build()

    @Provides
    @Singleton
    fun provideLocalDataManager(appDatabase:AppDatabase) = LocalDataManager(appDatabase)
}