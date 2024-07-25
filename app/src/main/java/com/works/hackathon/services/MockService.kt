package com.works.hackathon.services

import com.works.hackathon.model.Item
import retrofit2.Call
import retrofit2.http.GET

interface MockService {
    @GET("data.json") // assets/data.json dosyasına göre yol belirtiliyor
    fun getData(): Call<List<Item>> // data.json dosyasındaki yapıya uygun bir model kullanılmalı
}
