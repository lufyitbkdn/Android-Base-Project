package com.shapee.sample.base

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by Alaa Moataz on 9/6/20.
 */
abstract class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewDataBinding = DataBindingUtil.bind<ViewDataBinding>(view)
        setViewModel(viewDataBinding)
        viewDataBinding?.lifecycleOwner = this.viewLifecycleOwner
        startObservingValues()
    }
    abstract fun setViewModel(viewDataBinding: ViewDataBinding?)
    abstract fun startObservingValues()
}