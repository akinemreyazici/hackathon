package com.works.hackathon.services

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.works.hackathon.model.Category

class CategoryRepository(private val context: Context) {

    fun getCategoriesFromJson(fileName: String): List<Category>? {
        val jsonString = readJsonFromAssets(fileName)
        return jsonString?.let {
            parseJsonToList(it)
        }
    }

    private fun readJsonFromAssets(fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun parseJsonToList(jsonString: String): List<Category> {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<List<Category>>() {}.type)
    }
}


