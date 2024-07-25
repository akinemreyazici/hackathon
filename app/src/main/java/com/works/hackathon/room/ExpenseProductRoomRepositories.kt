package com.works.hackathon.room


import com.works.hackathon.model.ExpenseProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExpenseProductRoomRepositories(private val productDao: ExpenseProductDao) {

    suspend fun addProducts(product: ExpenseProduct): Long {
        return withContext(Dispatchers.IO) {
            productDao.addProducts(product)
        }
    }

    suspend fun getAllProducts(): List<ExpenseProduct> {
        return withContext(Dispatchers.IO) {
            productDao.getAllProducts()
        }
    }


    suspend fun deleteAllProducts() {
        withContext(Dispatchers.IO) {
            productDao.deleteAllProducts()
        }
    }

    suspend fun deleteProduct(product: ExpenseProduct) {
        withContext(Dispatchers.IO) {
            productDao.deleteProduct(product)
        }
    }
}

// Burada coroutine özelliği olan suspend fonksiyonları kullanıyorum çünkü
// lokal database işlemleri uygulamamı etkilemesin ve hata almayayım

