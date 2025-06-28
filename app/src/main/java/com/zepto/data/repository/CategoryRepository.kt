package com.zepto.data.repository

import com.zepto.data.models.fakeApi.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getCategories() : Flow<List<Category>>

    suspend fun getCategoryByIdOrName(idOrName : String) : Category?
}