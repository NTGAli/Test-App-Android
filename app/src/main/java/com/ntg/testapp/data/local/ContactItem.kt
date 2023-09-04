package com.ntg.testapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Contacts")
data class ContactItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var phoneNumber: String,
    var userProfile: String
        )