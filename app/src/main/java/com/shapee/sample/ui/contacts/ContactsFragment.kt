package com.shapee.sample.ui.contacts

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.shapee.sample.R
import com.shapee.sample.base.BaseFragment
import com.shapee.sample.databinding.FragmentContactsBinding
import com.shapee.sample.ui.addContact.AddContactsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactsFragment : BaseFragment(R.layout.fragment_contacts) {

    private val viewModel by viewModels<ContactsViewModel>()
    private lateinit var contactAdapter: ContactsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactAdapter = ContactsAdapter(viewModel)
        rvContacts.apply {
            adapter = contactAdapter
        }
    }

    override fun setViewModel(viewDataBinding: ViewDataBinding?) {
        (viewDataBinding as FragmentContactsBinding).apply {
            fragmentViewModel = viewModel
        }
    }

    override fun startObservingValues() {
        viewModel.apply {
            contacts.observe(viewLifecycleOwner){
                contactAdapter.submitList(it)
                contactAdapter.notifyDataSetChanged()
            }
            navigateToAddContact.observe(viewLifecycleOwner) {
                activity?.let { AddContactsActivity.start(it, null) }
            }
        }

    }



}