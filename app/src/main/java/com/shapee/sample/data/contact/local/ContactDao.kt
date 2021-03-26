package com.shapee.sample.data.contact.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.shapee.sample.data.contact.entity.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact): Long

    @Query("SELECT * FROM contacts ORDER BY name ASC")
    fun getAllContacts(): LiveData<List<Contact>>

    @Delete
    suspend fun deleteContact(contact: Contact)

//    @Query("SELECT * FROM contacts ORDER BY name ASC")
//    fun getAllContactsAsPage(): PagingSource<Int, Contact>

}