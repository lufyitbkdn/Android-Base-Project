package com.shapee.android.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.lifecycle.ViewModelProvider
import com.shapee.android.R
import com.shapee.android.databinding.ActivitySplashBinding
import com.shapee.android.ui.activity.home.HomeActivity
import com.shapee.android.ui.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {
    private var mCountDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.viewModel = mViewModel
        //if home activity is running, it must move to home directly (in case user tap on notification)

    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModel(): SplashViewModel {
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
        viewModel.setNavigator(this)
        return viewModel
    }

    override fun onStart() {
        super.onStart()
        mCountDownTimer = object : CountDownTimer(3000, 3000) {
            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java).apply {
                    intent?.extras?.let {
                        //Pass all extras to home to handle push notification
                        putExtras(it)
                    }
                })
            }

            override fun onTick(millisUntilFinished: Long) {

            }
        }
        mCountDownTimer?.start()
    }

    override fun onPause() {
        super.onPause()
        if(mCountDownTimer!=null){
            mCountDownTimer?.cancel()
            mCountDownTimer = null
        }
    }

}
