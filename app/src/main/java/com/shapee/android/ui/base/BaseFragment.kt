package com.shapee.android.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.shapee.android.R
import com.shapee.android.di.ViewModelFactory
import com.shapee.android.views.LoadingDialog
import com.shapee.android.views.MessageDialog
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<DB : ViewDataBinding, VM : ViewModel> : Fragment() {
    protected lateinit var mBinding: DB
    protected lateinit var mViewModel: VM

    private var mLoadingDialog: LoadingDialog? = null
    protected var mContext: Context? = null
    private lateinit var errorDialog: MessageDialog

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()
    }

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = getViewModel()
        mLoadingDialog = LoadingDialog(mContext!!)
        errorDialog = MessageDialog(mContext!!)
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_enter_in, R.anim.slide_enter_out)
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        activity?.overridePendingTransition(R.anim.slide_enter_in, R.anim.slide_enter_out)
    }

    abstract fun getViewModel(): VM

    open fun showLoading(isShow: Boolean) {
        try {
            if (isShow) {
                mLoadingDialog!!.show()
            } else {
                if (mLoadingDialog!!.isShowing()) {
                    mLoadingDialog!!.dismiss()
                }
            }
        } catch (ex: IllegalArgumentException) {
        }
    }

    fun showErrorDialog(message: String) {
        errorDialog.message = message
        if (!errorDialog.isShowing) {
            errorDialog.show()
        }
    }

    open fun showError(message: String) {
        showErrorDialog(message)
    }
}