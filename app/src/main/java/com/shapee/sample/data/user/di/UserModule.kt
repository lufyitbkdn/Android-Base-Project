package com.shapee.sample.data.user.di

import com.shapee.sample.data.user.remote.RemoteUserDataSource
import com.shapee.sample.data.user.remote.RemoteUserDataSourceImp
import com.shapee.sample.data.utils.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserModule {
    @Provides
    @Singleton
    fun provideRemoteUserDataSource(apiClient: ApiClient): RemoteUserDataSource =
        RemoteUserDataSourceImp(apiClient)
}