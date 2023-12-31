package com.ntg.testapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ContactItem::class], version = 1)
abstract class ContactDB: RoomDatabase() {

    abstract fun contactsDao(): ContactDao

}