package com.ntg.testapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntg.testapp.data.local.ContactItem
import com.ntg.testapp.model.Resource
import com.ntg.testapp.model.res.ResultImageRes
import com.ntg.testapp.repositories.ContactRepository
import com.ntg.testapp.repositories.DefaultContactRepository
import com.ntg.testapp.util.Constants.MAX_LENGTH_NAME
import com.ntg.testapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {

    val contacts = repository.observeAllContact()

    private val _images = MutableLiveData<Event<Resource<ResultImageRes>>>()
    val images: LiveData<Event<Resource<ResultImageRes>>> = _images

    private val _currentImage = MutableLiveData<String>()
    val currentImage: LiveData<String> = _currentImage

    private val _insertShoppingImageStatus = MutableLiveData<Event<Resource<ContactItem>>>()
    val insertShoppingImageStatus: LiveData<Event<Resource<ContactItem>>> =
        _insertShoppingImageStatus

    fun setCurrentImageUrl(url: String) {
        _currentImage.postValue(url)
    }

    fun deleteContact(contactItem: ContactItem) = viewModelScope.launch {
        repository.deleteContact(contactItem)
    }

    fun insertContactToDB(contactItem: ContactItem) = viewModelScope
        .launch {
            repository.insertContact(contactItem)
        }


    fun insertContact(contactItem: ContactItem) {
        if (contactItem.name.isEmpty() || contactItem.phoneNumber.isEmpty()) {
            _insertShoppingImageStatus.postValue(Event(Resource.error("filed can not be empty", null)))
            return
        }

        if (contactItem.name.length > MAX_LENGTH_NAME){
            _insertShoppingImageStatus.postValue(Event(Resource.error("too long length", null)))
            return
        }
        insertContactToDB(contactItem)
    }
}