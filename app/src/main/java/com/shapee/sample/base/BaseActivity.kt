package com.shapee.sample.base

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import com.shapee.sample.utils.LocaleManager

/**
 * Created by Alaa Moataz on 9/6/20.
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.updateContext(base))
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        overrideConfiguration?.let {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.updateContext(this)
    }

}