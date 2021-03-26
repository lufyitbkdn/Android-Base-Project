package com.shapee.sample.ui.contacts

import androidx.recyclerview.widget.RecyclerView
import com.shapee.sample.data.contact.entity.Contact
import com.shapee.sample.databinding.CellContactBinding

class ContactViewHolder(private val binding: CellContactBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        contactsViewModel: ContactsViewModel,
        contact: Contact?
    ) {
        binding.apply {
            this.viewModel = contactsViewModel
            this.contact = contact
        }

    }
}