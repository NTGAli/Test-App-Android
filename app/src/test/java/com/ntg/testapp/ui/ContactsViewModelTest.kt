package com.ntg.testapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
//import com.ntg.testapp.MainCoroutineRule
import com.ntg.testapp.data.local.ContactItem
import com.ntg.testapp.getOrAwaitValue
import com.ntg.testapp.model.Status
import com.ntg.testapp.repositories.FakeContactRepository
import com.ntg.testapp.util.Constants.MAX_LENGTH_NAME
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContactsViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

//    @get:Rule
//    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ContactsViewModel

    @Before
    fun setup(){
        viewModel = ContactsViewModel(FakeContactRepository())
    }

    @Test
    fun `insert contact with empty field, returns error`(){
        viewModel.insertContact(ContactItem(0,"name", "",""))
        val value = viewModel.insertShoppingImageStatus.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }


    @Test
    fun `insert too long name, returns error`(){
//        runTest {
            val string = buildString {
                for (i in 0 .. MAX_LENGTH_NAME + 1){
                    append(1)
                }
            }

            viewModel.insertContact(ContactItem(0,string, "23345648455",""))
            val value = viewModel.insertShoppingImageStatus.getOrAwaitValue()

            assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
//        }
    }

}