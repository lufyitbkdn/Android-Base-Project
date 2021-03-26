package com.shapee.sample.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shapee.sample.data.contact.entity.Contact
import com.shapee.sample.data.contact.repository.ContactRepository
import com.shapee.sample.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {

    val contacts: LiveData<List<Contact>> = contactRepository.getAllContacts()

    private val _navigateToAddContact = MutableLiveData<Event<Unit>>()
    val navigateToAddContact: LiveData<Event<Unit>> = _navigateToAddContact

    fun navigateToAddContact() {
        _navigateToAddContact.value = Event(Unit)
    }

    fun onContactClick(contact: Contact) {

    }

    companion object {
        private const val PAGE_SIZE = 10
        private const val PAGE_MAX_SIZE = 200
    }
}