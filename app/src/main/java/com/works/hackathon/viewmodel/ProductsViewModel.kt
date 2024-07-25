package com.works.hackathon.viewmodel

import ItemRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.works.hackathon.config.RetrofitClient
import com.works.hackathon.model.Category
import com.works.hackathon.model.ExpenseProduct

class ProductsViewModel(application: Application) : AndroidViewModel(application) {

    val client = RetrofitCustomerClient.getClient()
    val homepageService = client.create(CustomerService::class.java)
    val expenseProductsList: LiveData<List<ExpenseProduct>> get() = _expenseProductsList
    private val _expenseProductsList = MutableLiveData<List<ExpenseProduct>>()

    private val itemRepository =  ItemRepository(application.applicationContext)

    fun getAllExpenseProductsByAPI(category : String){

    }


    fun getAllExpenseProductsByMockService(category : String){
        val categories = itemRepository.getDataFromJson("expenses.json")
        _expenseProductsList.value = categories
    }

}