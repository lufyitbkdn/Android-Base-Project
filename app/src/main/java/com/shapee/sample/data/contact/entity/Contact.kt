package com.shapee.sample.data.contact.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(
    tableName = "contacts", indices = [
        Index(value = ["name"])
    ]
)
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var contactId: Long = 0,
    var name: String = "",
    var phoneNumber: String = "",
    var createdDate: Date = Date()
):Serializable