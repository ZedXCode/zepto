package com.zepto.data.models.fakeApi

data class CategoryUiState (
    val categoryId: String = "",
    val categoryName: String = "",
    val products: List<Any> = emptyList(),
    val isLoading : Boolean = false,
    val error:String? = null
)