package com.shapee.sample.data.contact.di

import com.shapee.sample.data.contact.local.ContactDao
import com.shapee.sample.data.contact.local.ContactDataSource
import com.shapee.sample.data.contact.local.ContactDataSourceImp
import com.shapee.sample.data.contact.repository.ContactRepository
import com.shapee.sample.data.contact.repository.ContactRepositoryImp
import com.shapee.sample.data.utils.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContactModule {
    @Provides
    @Singleton
    fun provideContactDao(appDatabase: AppDatabase) = appDatabase.contactDao()

    @Provides
    @Singleton
    fun provideContactDataSource(contactDao: ContactDao):ContactDataSource = ContactDataSourceImp(contactDao)

    @Provides
    @Singleton
    fun provideContactRepository(contactDataSource: ContactDataSource):ContactRepository =
        ContactRepositoryImp(contactDataSource)
}