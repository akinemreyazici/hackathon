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
import com.works.hackathon.adapter.ExpenseProductsAdapter
import com.works.hackathon.databinding.FragmentHomepageBinding
import com.works.hackathon.databinding.FragmentProductsBinding
import com.works.hackathon.viewmodel.HomepageViewModel
import com.works.hackathon.viewmodel.ProductsViewModel

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!



    private lateinit var viewModel: ProductsViewModel
    private lateinit var expensesAdapter : ExpenseProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        viewModel = ViewModelProvider(requireActivity())[ProductsViewModel::class.java]
        val category = arguments?.getString("category") ?: ""
        viewModel.getAllExpenseProducts(category)

        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)

        viewModel.expenseProductsList.observe(viewLifecycleOwner) { expenseProducts ->
            expensesAdapter = ExpenseProductsAdapter(requireContext(), expenseProducts)
            binding.expenseProductsRW.adapter = expensesAdapter
            binding.expenseProductsRW.layoutManager = layoutManager
        }
    }

}