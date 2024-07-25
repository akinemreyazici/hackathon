package com.works.hackathon.viewmodel

import com.works.hackathon.services.CategoryRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.works.hackathon.config.AppDatabase
import com.works.hackathon.model.Category




class HomepageViewModel(application: Application) : AndroidViewModel(application) {

    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "appDatabase"
    ).build()
    val categoryList: LiveData<List<Category>> get() = _categoryList
    private val _categoryList = MutableLiveData<List<Category>>()

    private val categoryRepository =  CategoryRepository(application.applicationContext)

    fun getlAllCategories() {
        val categories = categoryRepository.getCategoriesFromJson("categories.json")
        _categoryList.value = categories
    }
}
