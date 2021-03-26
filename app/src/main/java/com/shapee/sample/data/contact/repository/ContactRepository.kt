package com.shapee.sample.data.contact.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Transaction
import com.shapee.sample.data.contact.entity.Contact

interface ContactRepository {
    suspend fun insertContact(contact: Contact): Long
    fun getAllContacts(): LiveData<List<Contact>>
    suspend fun deleteContact(contact: Contact)
//    fun getAllContactsAsPage(): PagingSource<Int, Contact>
}