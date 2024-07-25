package com.works.hackathon.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.works.hackathon.R
import com.works.hackathon.adapter.CategoriesAdapter
import com.works.hackathon.databinding.FragmentHomepageBinding
import com.works.hackathon.databinding.FragmentLoginBinding
import com.works.hackathon.viewmodel.HomepageViewModel
import com.works.hackathon.viewmodel.LoginViewModel

class HomepageFragment : Fragment() {

    private var _binding: FragmentHomepageBinding? = null
    private val binding get() = _binding!!



    private lateinit var viewModel: HomepageViewModel
    private lateinit var categoriesAdapter : CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(HomepageViewModel::class.java)


        viewModel = ViewModelProvider(requireActivity())[HomepageViewModel::class.java]
        viewModel.clearLocalData()

        _binding = FragmentHomepageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getlAllCategories()
        val layoutManagerCategory: RecyclerView.LayoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)

        viewModel.categoryList.observe(viewLifecycleOwner){
            categoriesAdapter = CategoriesAdapter(requireContext(),it)
            binding.categoriesRV.adapter = categoriesAdapter
            binding.categoriesRV.layoutManager = layoutManagerCategory

        }
    }

}