package com.works.hackathon.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.works.hackathon.R
import com.works.hackathon.viewmodel.HomepageViewModel

class HomepageFragment : Fragment() {

    companion object {
        fun newInstance() = HomepageFragment()
    }

    private lateinit var viewModel: HomepageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomepageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}