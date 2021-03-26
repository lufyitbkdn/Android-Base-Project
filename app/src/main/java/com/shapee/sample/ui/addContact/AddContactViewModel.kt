package com.shapee.sample.ui.addContact

import androidx.lifecycle.*
import com.shapee.sample.data.contact.entity.Contact
import com.shapee.sample.data.contact.repository.ContactRepository
import com.shapee.sample.utils.Event
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val contactRepository:ContactRepository
) : ViewModel() {
    private val _contact = savedStateHandle.getLiveData<Contact>(SavedInstanceKeys.KEY_CONTACT)
    val contact: LiveData<Contact> = _contact

    private val _navigateBack = MutableLiveData<Event<Long?>>()
    val navigateBack: LiveData<Event<Long?>> = _navigateBack

    private val _isSaveButtonEnabled = MutableLiveData(false)
    val isSaveButtonEnabled: LiveData<Boolean> = _isSaveButtonEnabled


    fun initData(contact: Contact) {
        if (_contact.value == null) {
            _contact.postValue(contact)
        }
    }
    fun onNameChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        contact.value?.name = s.toString()
        _isSaveButtonEnabled.value = contact.value?.name?.isNotBlank() == true &&contact.value?.phoneNumber?.isNotBlank() == true
    }

    fun onPhoneNumberChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        contact.value?.phoneNumber = s.toString()
        _isSaveButtonEnabled.value = contact.value?.name?.isNotBlank() == true &&contact.value?.phoneNumber?.isNotBlank() == true
    }

    fun onSave() {
        viewModelScope.launch {
            contact.value?.let {
                val contactId = contactRepository.insertContact(it)
                _navigateBack.value = Event(contactId)
            }
        }
    }

    fun navigateBack() {
        _navigateBack.value = Event(null)
    }

    companion object {
        object SavedInstanceKeys {
            const val KEY_CONTACT = "contact"
        }
    }
}