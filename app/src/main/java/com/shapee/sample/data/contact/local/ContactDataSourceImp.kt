package com.shapee.sample.data.contact.local

import androidx.lifecycle.LiveData
import com.shapee.sample.data.contact.entity.Contact

class ContactDataSourceImp internal constructor(private val contactDAO: ContactDao):ContactDataSource{
    override suspend fun insertContact(contact: Contact): Long  = contactDAO.insertContact(contact)

    override fun getAllContacts(): LiveData<List<Contact>> = contactDAO.getAllContacts()

    override suspend fun deleteContact(contact: Contact) = contactDAO.deleteContact(contact)
//    override fun getAllContactsAsPage()  = contactDAO.getAllContactsAsPage()

}