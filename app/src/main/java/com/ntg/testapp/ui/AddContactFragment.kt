package com.ntg.testapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ntg.testapp.R

class AddContactFragment: Fragment(R.layout.fragment_add_contact) {
    lateinit var viewModel: ContactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ContactsViewModel::class.java)
    }
}