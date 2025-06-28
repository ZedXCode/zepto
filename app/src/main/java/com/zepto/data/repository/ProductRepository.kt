package com.zepto.data.repository

import com.zepto.data.models.fakeApi.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProduct(): Flow<List<Product>>
    fun getProductByCategory(category:String): Flow<List<Product>>
    fun getCategories() : Flow<List<String>>
}