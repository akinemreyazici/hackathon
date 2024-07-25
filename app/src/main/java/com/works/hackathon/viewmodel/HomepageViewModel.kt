package com.works.hackathon.viewmodel

import CategoryRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.works.hackathon.model.Category




class HomepageViewModel(application: Application) : AndroidViewModel(application) {

    val categoryList: LiveData<List<Category>> get() = _categoryList
    private val _categoryList = MutableLiveData<List<Category>>()

    private val categoryRepository =  CategoryRepository(application.applicationContext)

    fun getlAllCategories() {
        val categories = categoryRepository.getCategoriesFromJson("categories.json")
        _categoryList.value = categories
    }
}
