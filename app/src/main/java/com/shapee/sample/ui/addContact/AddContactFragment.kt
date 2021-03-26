package com.shapee.sample.ui.addContact

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import com.shapee.sample.R
import com.shapee.sample.base.BaseFragment
import com.shapee.sample.data.contact.entity.Contact
import com.shapee.sample.databinding.FragmentAddContactBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddContactFragment : BaseFragment(R.layout.fragment_add_contact) {

    private val viewModel by viewModels<AddContactViewModel>()

    override fun setViewModel(viewDataBinding: ViewDataBinding?) {
        (viewDataBinding as FragmentAddContactBinding).apply {
            fragmentViewModel = viewModel
        }
    }

    override fun startObservingValues() {
        viewModel.navigateBack.observe(viewLifecycleOwner) {
            if (it.peekContent() != null) {
                activity?.setResult(Activity.RESULT_OK, Intent().apply {
                    putExtra(AddContactsActivity.EXTRA_CONTACT_ID, it.peekContent())
                })

            }
            activity?.finish()
        }
    }

    companion object {
        private const val EXTRA_CONTACT = "contact"

        fun newInstance(contact: Contact): AddContactFragment {
            return AddContactFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(EXTRA_CONTACT, contact)
                }
            }
        }
    }

}