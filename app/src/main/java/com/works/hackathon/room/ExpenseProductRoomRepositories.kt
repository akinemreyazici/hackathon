package com.works.hackathon.room


import com.works.hackathon.model.ExpenseProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExpenseProductRoomRepositories(private val productDao: ExpenseProductDao) {

    suspend fun addOrUpdateProduct(product: ExpenseProduct): Long {
        return withContext(Dispatchers.IO) {
            val existingProduct = productDao.getProductByTitleAndCategory(product.title, product.category)
            if (existingProduct != null) {
                productDao.updateProductQuantityAndPrice(
                    existingProduct.expenseProductId ?: -1,
                    product.price
                )
                existingProduct.expenseProductId?.toLong() ?: -1L
            } else {
                productDao.addProducts(
                    ExpenseProduct(
                        title = product.title,
                        price = product.price,
                        category = product.category,
                        image = product.image,
                        quantity = 1)) // Varsayılan olarak 1
            }
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

