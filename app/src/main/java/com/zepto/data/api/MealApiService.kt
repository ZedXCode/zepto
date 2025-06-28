package com.zepto.data.api

import com.zepto.data.models.mealDB.CategoryResponse
import com.zepto.data.models.mealDB.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {
    @GET("search.php") // Correct
    suspend fun searchMeals(@Query("s") searchQuery: String): MealResponse

    @GET("api/json/v1/1/lookup.php")
    suspend fun getMealById(@Query("i") id: String): MealResponse

    @GET("categories.php") // Correct
    suspend fun getCategories(): CategoryResponse

    @GET("filter.php") // Correct
    suspend fun getMealsByCategory(@Query("c") categoryName: String): MealResponse

    @GET("random.php") // Correct
    suspend fun getRandomMeal(): MealResponse
}