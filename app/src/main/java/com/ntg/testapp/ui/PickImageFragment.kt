package com.ntg.testapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ntg.testapp.R

class PickImageFragment: Fragment(R.layout.fragment_pick_image) {
    lateinit var viewModel: ContactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ContactsViewModel::class.java)
    }
}