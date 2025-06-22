package com.zepto.data.models

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val weight:Int = 10,
    val imageRes:Int = 0,
    val category: String? = null,
    val description: String? = null,
    val rating: Rating? = null
)
data class Rating(
    val rate : Double,
    val count: Int
)