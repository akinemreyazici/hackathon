package com.works.hackathon.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.works.hackathon.R
import com.works.hackathon.activities.AuthActivity
import com.works.hackathon.adapter.ExpenseProductsAdapter
import com.works.hackathon.databinding.FragmentProductsBinding
import com.works.hackathon.databinding.FragmentProfileBinding
import com.works.hackathon.manager.PrefManager
import com.works.hackathon.viewmodel.ProductsViewModel
import com.works.hackathon.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!



    private lateinit var viewModel: ProfileViewModel
    private lateinit var prefManager: PrefManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        viewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]


        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefManager = PrefManager(requireContext())

        binding.constraintLayoutLogout.setOnClickListener {
            val intent = Intent(requireActivity(),AuthActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.constraintLayoutForgetMe.setOnClickListener {
            prefManager.clearData()
        }
    }
}