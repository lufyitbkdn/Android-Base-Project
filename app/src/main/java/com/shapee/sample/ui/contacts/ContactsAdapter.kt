package com.shapee.sample.ui.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.shapee.sample.data.contact.entity.Contact
import com.shapee.sample.databinding.CellContactBinding

class ContactsAdapter(private val viewModel: ContactsViewModel) :
    ListAdapter<Contact, ContactViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(viewModel, getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CellContactBinding.inflate(layoutInflater, parent, false)
        return ContactViewHolder(binding)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean =
                oldItem.contactId == newItem.contactId

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean =
                oldItem == newItem
        }
    }
}