package com.works.hackathon.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.works.hackathon.model.ExpenseProduct
import com.works.hackathon.room.ExpenseProductDao



@Database(entities = [ExpenseProduct::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseProductDao(): ExpenseProductDao
}