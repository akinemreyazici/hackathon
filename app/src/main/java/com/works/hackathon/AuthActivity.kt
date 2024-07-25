package com.works.hackathon

import ItemRepository
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val itemRepository = ItemRepository(this)

        val items = itemRepository.getDataFromJson("data.json")
        items?.forEach {
            Log.e("Item", "Name: ${it.name}, Price: ${it.price}")
        }
    }
}