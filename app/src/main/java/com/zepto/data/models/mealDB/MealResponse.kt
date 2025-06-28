package com.zepto.data.models.mealDB

import com.google.gson.annotations.SerializedName
import com.zepto.data.models.fakeApi.Product
import com.zepto.data.models.fakeApi.Rating
import kotlin.random.Random

data class MealResponse(
    @SerializedName("meals")
    val meals: List<MealDto>?
)

data class CategoryResponse(
    @SerializedName("categories")
    val categories: List<CategoryDto>?
)

data class MealDto(
    @SerializedName("idMeal")
    val id: String,

    @SerializedName("strMeal")
    val name: String,

    @SerializedName("strCategory")
    val category: String?,

    @SerializedName("strArea")
    val area: String?,

    @SerializedName("strInstructions")
    val instructions: String?,

    @SerializedName("strMealThumb")
    val thumbnailUrl: String?,

    @SerializedName("strTags")
    val tags: String?
) {
    // Convert to Product model
    fun toProduct(): Product {
        // Generate random price between 50 and 500
        val randomPrice = Random.nextDouble(from = 50.0, until = 500.0)

        // Generate random rating between 3.5 and 5.0
        val randomRating = Random.nextDouble(from = 3.5, until = 5.0)

        // Generate random review count between 10 and 200
        val randomReviewCount = Random.nextInt(from = 10, until = 200)

        return Product(
            id = id.toInt(),
            name = name,
            price = randomPrice,
            category = category ?: "",
            imageUrl = thumbnailUrl ?: "",
            imageRes = 0, // No local resource
            description = instructions ?: "",
            rating = Rating(
                rate = randomRating,
                count = randomReviewCount
            )
        )
        // Remove any fields that don't exist in your Product class
    }
}

data class CategoryDto(
    @SerializedName("idCategory")
    val id: String,
    @SerializedName("strCategory")
    val name: String,
    @SerializedName("strCategoryThumb")
    val thumnailUrl:String,
    @SerializedName("strCategoryDescription")
    val description: String?
){
    fun toCategory(): MealCategory{
        return MealCategory(
            id = id,
            name = name,
            imageRes = thumnailUrl
        )
    }
}