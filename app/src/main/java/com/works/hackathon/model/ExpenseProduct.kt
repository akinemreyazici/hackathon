package com.works.hackathon.model


data class ExpenseProducts(
    val products: List<ExpenseProduct>
)

data class ExpenseProduct (
    val title: String,
    val price: Double,
    val category: String,
    val image: String,
)
