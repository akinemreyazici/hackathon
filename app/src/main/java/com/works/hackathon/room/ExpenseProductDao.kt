package com.works.hackathon.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.works.hackathon.model.ExpenseProduct

@Dao
interface ExpenseProductDao {
    @Insert
    fun addProducts(product: ExpenseProduct): Long

    @Query("select * from expenses")
    fun getAllProducts(): List<ExpenseProduct>

    @Query("DELETE FROM expenses")
    fun deleteAllProducts()

    @Query("SELECT * FROM expenses WHERE title = :title AND category = :category LIMIT 1")
    fun getProductByTitleAndCategory(title: String?, category: String?): ExpenseProduct?

    @Query("UPDATE expenses SET quantity = quantity + 1, price = price + :additionalPrice WHERE expenseProductId = :productId")
    fun updateProductQuantityAndPrice(productId: Int, additionalPrice: Double?): Int

    @Delete
    fun deleteProduct(product: ExpenseProduct)
}
