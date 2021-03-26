package com.shapee.sample.ui.addContact

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.shapee.sample.R
import com.shapee.sample.base.BaseActivity
import com.shapee.sample.data.contact.entity.Contact
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddContactsActivity : BaseActivity() {
    lateinit var contact: Contact
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
        with(intent) {
            contact = getSerializableExtra(EXTRA_CONTACT) as Contact
        }
        supportFragmentManager.beginTransaction().replace(
            R.id.container, AddContactFragment.newInstance(contact)
        ).commit()
    }

    companion object {
        const val EXTRA_CONTACT_ID = "contact_id"
        private const val EXTRA_CONTACT = "contact"

        fun start(activity: Activity, contact: Contact?) {
            activity.startActivity(Intent(activity, AddContactsActivity::class.java).apply {
                putExtra(EXTRA_CONTACT, contact ?: Contact())
            })
//            activity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        }
    }


}