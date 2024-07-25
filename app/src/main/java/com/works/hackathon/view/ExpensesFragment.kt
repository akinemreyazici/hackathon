package com.works.hackathon.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.works.hackathon.adapter.MyExpensesAdapter
import com.works.hackathon.databinding.FragmentExpensesBinding
import com.works.hackathon.viewmodel.ExpensesViewModel

class ExpensesFragment : Fragment() {

    private var _binding: FragmentExpensesBinding? = null
    private val binding get() = _binding!!



    private lateinit var viewModel: ExpensesViewModel
    private lateinit var myExpensesAdapter : MyExpensesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(ExpensesViewModel::class.java)


        viewModel = ViewModelProvider(requireActivity())[ExpensesViewModel::class.java]

        _binding = FragmentExpensesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false)

        viewModel.fetchAllMyExpenses()
        viewModel.expenseProductsList.observe(viewLifecycleOwner){
            myExpensesAdapter = MyExpensesAdapter(requireContext(),it,requireActivity().application)
            binding.myExpensesRV.adapter = myExpensesAdapter
            binding.myExpensesRV.layoutManager = layoutManager
            myExpensesAdapter.updateData(it)
        }

        viewModel.totalPrice.observe(viewLifecycleOwner){
            binding.txtMyExpensesTotalPrice.text = it.toString()
        }
    }

}