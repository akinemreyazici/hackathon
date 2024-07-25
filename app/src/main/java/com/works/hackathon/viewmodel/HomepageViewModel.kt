package com.works.hackathon.viewmodel

import com.works.hackathon.services.CategoryRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.works.hackathon.config.AppDatabase
import com.works.hackathon.model.Category
import com.works.hackathon.room.ExpenseProductRoomRepositories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomepageViewModel(application: Application) : AndroidViewModel(application) {

    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "appDatabase"
    ).fallbackToDestructiveMigration()
        .build()
    val categoryList: LiveData<List<Category>> get() = _categoryList
    private val _categoryList = MutableLiveData<List<Category>>()

    private val productDao = db.expenseProductDao()
    private val productRoomRepositories = ExpenseProductRoomRepositories(productDao)

    private val categoryRepository =  CategoryRepository(application.applicationContext)

    fun getlAllCategories() {
        val categories = categoryRepository.getCategoriesFromJson("categories.json")
        _categoryList.value = categories
    }

    fun clearLocalData(){
        CoroutineScope(Dispatchers.Main).launch {
            productRoomRepositories.deleteAllProducts()
        }
    }
}
