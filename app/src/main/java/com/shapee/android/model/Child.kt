package com.shapee.android.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Children")
class Child : Serializable {

    @PrimaryKey
    var id: Long = 0
    var name: String = ""
    var email: String = ""

    constructor()

//    constructor(id: Long, name: String, email: String) {
//        this.id = id
//        this.name = name
//        this.email = email
//    }

}
