package com.ntg.testapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ntg.testapp.data.local.ContactItem

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contactItem: ContactItem)

    @Delete
    suspend fun delete(contactItem: ContactItem)

    @Query("SELECT * FROM Contacts")
    fun getAllContacts(): LiveData<List<ContactItem>>

    @Query("SELECT * FROM Contacts WHERE (name LIKE '%' || :query || '%')")
    fun searchInUsers(query: String): LiveData<List<ContactItem>?>
}