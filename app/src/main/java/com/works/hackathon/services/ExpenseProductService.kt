package com.works.hackathon.services

import com.works.hackathon.model.ExpenseProduct
import com.works.hackathon.model.ExpenseProducts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ExpenseProductService {

    /*
    @GET("products/")
    fun getAllProducts(): Call<ExpenseProducts> // Direk filtreliyorum o yüzden bunada gerek yok

     */

    /*
    @GET("products/{id}")
    fun getProductData(): Call<ExpenseProduct> // Sürem yeterli değil detay sayfası yapmayacağım

     */

    @GET("/products/category/{category}")
    fun getProductsByCategoryData(@Path("category") category: String): Call<List<ExpenseProduct>>
}
