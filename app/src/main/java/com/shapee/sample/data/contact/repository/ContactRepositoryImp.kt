package com.shapee.sample.data.contact.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Transaction
import com.shapee.sample.data.contact.entity.Contact
import com.shapee.sample.data.contact.local.ContactDataSource

class ContactRepositoryImp internal constructor(private val dataSource: ContactDataSource) :
    ContactRepository {
    override suspend fun insertContact(contact: Contact): Long = dataSource.insertContact(contact)

    override fun getAllContacts(): LiveData<List<Contact>> = dataSource.getAllContacts()

    override suspend fun deleteContact(contact: Contact) = dataSource.deleteContact(contact)

//    override fun getAllContactsAsPage() = dataSource.getAllContactsAsPage()
}