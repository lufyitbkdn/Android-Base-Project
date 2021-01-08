package com.shapee.android.ui.fragment.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.shapee.android.R
import com.shapee.android.databinding.FragmentProfileBinding
import com.shapee.android.model.Child
import com.shapee.android.ui.base.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(),
    ProfileNavigator {
    private var mAdapter: ChildAdapter? = null

    override fun getLayoutRes() = R.layout.fragment_profile

    override fun getViewModel(): ProfileViewModel {
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
        viewModel.setNavigator(this)
        return viewModel
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.viewModel = mViewModel
        val children = arrayListOf(
            Child().apply {
                name = "Habbot"
                email = "habbot.phan@gmail.com"
            },
            Child().apply {
                name = "Men Dang"
                email = "dcm.it.bkdn@gmail.com"
            },
            Child().apply {
                name = "Kevin Ninh"
                email = "nvngoc2409@gmail.com"
            }
        )
        mAdapter = ChildAdapter(children) { item, position ->
            showError("Item clicked: " + item.name)
        }
        mBinding.rcItems.adapter = mAdapter
    }

    override fun showMessage() {
        activity?.let {
            showError("Hello world.")
        }
    }

}
