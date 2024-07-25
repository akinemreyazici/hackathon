package com.works.hackathon.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.works.hackathon.model.ExpenseProduct

@Dao
interface ExpenseProductDao {
    @Insert
    fun addProducts(product: ExpenseProduct): Long

    @Query("select * from expenses")
    fun getAllProducts(): List<ExpenseProduct>

    @Query("DELETE FROM expenses")
    fun deleteAllProducts()

    /*
    @Query("SELECT * FROM expenses WHERE expenseProductId = :productId")
    fun getProductById(productId: Int): ExpenseProduct?

     */

    @Delete
    fun deleteProduct(product: ExpenseProduct)
}
