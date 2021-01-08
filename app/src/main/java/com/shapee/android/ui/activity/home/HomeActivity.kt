package com.shapee.android.ui.activity.home

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.shapee.android.R
import com.shapee.android.databinding.ActivityHomeBinding
import com.shapee.android.ui.base.BaseActivity
import com.shapee.android.ui.fragment.profile.ProfileFragment

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeNavigator {

    override fun getViewModel(): HomeViewModel {
        val viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        viewModel.setNavigator(this)
        return viewModel
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_home
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.viewModel = mViewModel
        supportFragmentManager.beginTransaction().replace(R.id.frm_main, ProfileFragment()).commit()
    }


}
