package com.ntg.testapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.ntg.testapp.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
@ExperimentalCoroutinesApi
class ContactDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var contactDB: ContactDB
    private lateinit var dao: ContactDao

    @Before
    fun setup(){
        contactDB = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ContactDB::class.java
        ).allowMainThreadQueries().build()

        dao = contactDB.contactsDao()
    }

    @After
    fun tearDown(){
        contactDB.close()
    }

    @Test
    fun insertNewContact() = runTest {
        val contact = ContactItem(id=1, name = "John", "+1234567890", userProfile = "")
        dao.insert(contact)
        val contacts = dao.getAllContacts().getOrAwaitValue()
        assertThat(contacts).contains(contact)
    }

    @Test
    fun deleteContact() = runTest {
        val contact = ContactItem(id=1, name = "John", "+1234567890", userProfile = "")
        dao.insert(contact)
        dao.delete(contact)
        val contacts = dao.getAllContacts().getOrAwaitValue()
        assertThat(contacts).doesNotContain(contact)
    }

    @Test
    fun searchInUsers() = runTest {
        val contact1 = ContactItem(id=1, name = "John", "+1234567890", userProfile = "")
        val contact2 = ContactItem(id=2, name = "Alis", "+19876543210", userProfile = "")
        val contact3 = ContactItem(id=3, name = "Bob", "+1741852963", userProfile = "")
        dao.insert(contact1)
        dao.insert(contact2)
        dao.insert(contact3)

        val contacts = dao.searchInUsers("o").getOrAwaitValue()

        assertThat(contacts).doesNotContain(contact2)
        assertThat(contacts).contains(contact1)
        assertThat(contacts).contains(contact3)
    }

}