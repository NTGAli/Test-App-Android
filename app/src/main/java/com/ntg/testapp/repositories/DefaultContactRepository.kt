package com.ntg.testapp.repositories

import androidx.lifecycle.LiveData
import com.ntg.testapp.data.local.ContactDao
import com.ntg.testapp.data.local.ContactItem
import com.ntg.testapp.model.Resource
import com.ntg.testapp.model.res.Feed
import com.ntg.testapp.model.res.ResultImageRes
import com.ntg.testapp.remote.UnsplashApi
import retrofit2.Response
import javax.inject.Inject

class DefaultContactRepository @Inject constructor(
    private val contactDao: ContactDao,
    private val unsplashApi: UnsplashApi
) : ContactRepository {
    override suspend fun insertContact(contactItem: ContactItem) {
        contactDao.insert(contactItem)
    }

    override suspend fun deleteContact(contactItem: ContactItem) {
        contactDao.delete(contactItem)
    }

    override fun observeAllContact(): LiveData<List<ContactItem>> {
        return contactDao.getAllContacts()
    }

    override suspend fun findImage(imageQuery: String): Resource<ResultImageRes> {
        return try {
            val response = unsplashApi.searchInImages(imageQuery, 1)
            if (response.isSuccessful) {
                response.body().let {
                    Resource.success(it)
                }
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. check your internet", null)
        }
    }


}