package com.ntg.testapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ntg.testapp.data.local.ContactItem
import com.ntg.testapp.model.Resource
import com.ntg.testapp.model.res.ResultImageRes

class FakeContactRepository: ContactRepository {

    private val contactItems = mutableListOf<ContactItem>()
    private val observableContactItems = MutableLiveData<List<ContactItem>>(contactItems)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean){
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData(){
        observableContactItems.postValue(contactItems)
    }

    override suspend fun insertContact(contactItem: ContactItem) {
        contactItems.add(contactItem)
        refreshLiveData()
    }

    override suspend fun deleteContact(contactItem: ContactItem) {
        contactItems.remove(contactItem)
        refreshLiveData()
    }

    override fun observeAllContact(): LiveData<List<ContactItem>> {
        return observableContactItems
    }

    override suspend fun findImage(imageQuery: String): Resource<ResultImageRes> {
        TODO("Not yet implemented")
    }

}