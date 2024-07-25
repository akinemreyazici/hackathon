package com.works.hackathon.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.works.hackathon.config.AppDatabase
import com.works.hackathon.model.ExpenseProduct
import com.works.hackathon.room.ExpenseProductRoomRepositories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpensesViewModel(application: Application) : AndroidViewModel(application) {
    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "appDatabase"
    ).build()

    private val productDao = db.expenseProductDao()
    private val productRoomRepositories = ExpenseProductRoomRepositories(productDao)

    val expenseProductsList: LiveData<List<ExpenseProduct>> get() = _expenseProductsList
    private val _expenseProductsList = MutableLiveData<List<ExpenseProduct>>()

    val totalPrice: LiveData<Double> get() = _totalPrice
    private val _totalPrice = MutableLiveData<Double>()

    fun fetchAllMyExpenses() {
        CoroutineScope(Dispatchers.Main).launch {
            val products = productRoomRepositories.getAllProducts()
            _expenseProductsList.value = products
            calculateTotalPrice(products)
        }
    }

    private fun calculateTotalPrice(products: List<ExpenseProduct>) {
        CoroutineScope(Dispatchers.IO).launch {
            val total = products.sumOf { it.price * it.quantity }
            _totalPrice.postValue(total)
        }
    }
}
