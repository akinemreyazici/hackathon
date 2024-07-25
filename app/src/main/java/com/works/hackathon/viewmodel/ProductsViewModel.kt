package com.works.hackathon.viewmodel

import com.works.hackathon.services.ItemRepository
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.works.hackathon.config.AppDatabase
import com.works.hackathon.config.RetrofitClient
import com.works.hackathon.model.ExpenseProduct
import com.works.hackathon.room.ExpenseProductRoomRepositories
import com.works.hackathon.services.ExpenseProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsViewModel(application: Application) : AndroidViewModel(application) {

    private val client = RetrofitClient.getClient()
    private val expenseService = client.create(ExpenseProductService::class.java)
    private val itemRepository = ItemRepository(application.applicationContext)



    val expenseProductsList: LiveData<List<ExpenseProduct>> get() = _expenseProductsList
    private val _expenseProductsList = MutableLiveData<List<ExpenseProduct>>()

    fun getAllExpenseProducts(category: String) {
        fetchFromAPI(category)
    }

    private fun fetchFromAPI(category: String) {
        expenseService.getProductsByCategoryData(category).enqueue(object : Callback<List<ExpenseProduct>> {
            override fun onResponse(
                call: Call<List<ExpenseProduct>>,
                response: Response<List<ExpenseProduct>>
            ) {
                val apiData = response.body()
                Log.e("data",apiData!!.toString())
                val mockData = itemRepository.getDataFromJsonByCategory("expenses.json",category)


                if (apiData != null) {
                    val combinedData = apiData + (mockData ?: emptyList())
                    _expenseProductsList.value = combinedData
                } else {
                    _expenseProductsList.value = mockData
                }

            }

            override fun onFailure(call: Call<List<ExpenseProduct>>, t: Throwable) {
                Log.e("RETROFİT",t.message.toString())
                Log.e("RETROFİT",t.cause.toString())
                _expenseProductsList.value = itemRepository.getDataFromJsonByCategory("expenses.json",category)
            }
        })
    }
}
