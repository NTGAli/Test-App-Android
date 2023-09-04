package com.ntg.testapp.data.local

import androidx.room.Database

@Database(entities = [ContactItem::class], version = 1)
abstract class ContactDB {

    abstract fun contactsDao(): ContactDao

}