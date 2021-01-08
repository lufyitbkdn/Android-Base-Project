package com.shapee.android.ui.base

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.observe
import com.shapee.android.R
import com.shapee.android.di.ViewModelFactory
import com.shapee.android.utils.Constants
import com.shapee.android.views.LoadingDialog
import com.shapee.android.views.MessageDialog
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject


abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel<*>> : AppCompatActivity() {
    protected lateinit var mBinding: DB
    protected lateinit var mViewModel: VM
    private lateinit var errorDialog: MessageDialog


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var mLoadingDialog: LoadingDialog? = null

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        setLocale()
        mBinding = DataBindingUtil.setContentView(this, getLayoutRes())
        mViewModel = getViewModel()
        errorDialog = MessageDialog(this)
        mLoadingDialog = LoadingDialog(this)
        intViewModel()
    }

    fun setLocale() {
        val locale = Constants.DEFAULT_LOCALE
        Locale.setDefault(locale)
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun intViewModel() {
        mViewModel.isLoading.observe(this) { isShow: Boolean ->
            this.showLoading(isShow)
        }
        mViewModel.errorMessage.observe(this) { message: String? ->
            message?.let {
                showError(it)
            }
        }
    }

    open fun showError(message: String) {
        showErrorDialog(message)
    }


    fun showErrorDialog(message: String) {
        errorDialog.message = message
        if (!errorDialog.isShowing) {
            errorDialog.show()
        }
    }

    abstract fun getViewModel(): VM

    open fun showLoading(isShow: Boolean) {
        if (!isFinishing && !isDestroyed) {
            if (isShow) {
                mLoadingDialog?.show()
            } else {
                if (mLoadingDialog?.isShowing == true) {
                    mLoadingDialog?.dismiss()
                }
            }
        }
    }

    fun hideKeyBoard() {
        try {
            runOnUiThread {
                try {
                    val inputManager = this@BaseActivity
                        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(
                        this@BaseActivity.currentFocus?.applicationWindowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )
                } catch (e: IllegalStateException) {
                } catch (e: Exception) {
                }
            }

        } catch (e: IllegalStateException) {
        } catch (e: Exception) {
        }
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.slide_enter_in, R.anim.slide_enter_out)
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        overridePendingTransition(R.anim.slide_enter_in, R.anim.slide_enter_out)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.exit_to_right)
    }
}