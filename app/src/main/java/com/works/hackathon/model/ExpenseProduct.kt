package com.works.hackathon.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class ExpenseProducts(
    val products: List<ExpenseProduct>
)
// Bunu kullanamadım obje olarak algıladığından retrofit hata veriyordu.

@Entity(tableName = "expenses")
data class ExpenseProduct (
    @PrimaryKey(autoGenerate = true)
    val expenseProductId: Int? = null,

    val title: String?,
    val price: Double,
    val category: String?,
    val image: String?,

    val quantity: Int = 1
)
