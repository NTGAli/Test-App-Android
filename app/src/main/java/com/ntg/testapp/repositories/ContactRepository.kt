package com.ntg.testapp.repositories

import androidx.lifecycle.LiveData
import com.ntg.testapp.data.local.ContactItem
import com.ntg.testapp.model.Resource
import com.ntg.testapp.model.res.Feed
import com.ntg.testapp.model.res.ResultImageRes
import retrofit2.Response
import retrofit2.http.Query

interface ContactRepository {

    suspend fun insertContact(contactItem: ContactItem)

    suspend fun deleteContact(contactItem: ContactItem)

    fun observeAllContact(): LiveData<List<ContactItem>>

    suspend fun findImage(imageQuery: String): Resource<ResultImageRes>


}