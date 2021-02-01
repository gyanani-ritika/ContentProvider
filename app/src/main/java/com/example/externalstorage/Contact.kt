
package com.example.externalstorage;

import android.app.Activity
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.provider.Contacts.People
import android.provider.ContactsContract.CommonDataKinds.Phone

public class Contact : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        addContact("James", "5656215521");
        addContact("John", "4545454545");
        addContact("Mary", "9632587410");
        addContact("Peter", "4561237890");
    }

    private fun addContact(name: String, phone: String) {
        val values = ContentValues()
        values.put(People.NUMBER, phone)
        values.put(People.TYPE, Phone.TYPE_CUSTOM)
        values.put(People.LABEL, name)
        values.put(People.NAME, name)
        val dataUri: Uri? = contentResolver.insert(People.CONTENT_URI, values)
        var updateUri: Uri? = Uri.withAppendedPath(dataUri, People.Phones.CONTENT_DIRECTORY)
        values.clear()
        values.put(People.Phones.TYPE, People.TYPE_MOBILE)
        values.put(People.NUMBER, phone)
        updateUri = updateUri?.let { contentResolver.insert(it, values) }

    }

}