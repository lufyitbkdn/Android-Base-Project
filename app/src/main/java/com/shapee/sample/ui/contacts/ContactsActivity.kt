package com.shapee.sample.ui.contacts

import android.os.Bundle
import com.shapee.sample.R
import com.shapee.sample.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
        supportFragmentManager.beginTransaction().replace(R.id.container, ContactsFragment())
            .commit()
    }
}