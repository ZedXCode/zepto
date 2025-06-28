package com.zepto.data.repository

import android.util.Log
import com.zepto.data.api.FakeStoreApiService
import com.zepto.data.models.fakeApi.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
private const val TAG = "ProductRepository"
@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val apiService: FakeStoreApiService
) : ProductRepository{
    override fun getProduct(): Flow<List<Product>> = flow{
        Log.d(TAG, "Getting All Products")
        val products = apiService.fetchProducts()
        Log.d(TAG, "Fetched : ${products.size} products")
        emit(products)
    }.catch {e ->
        Log.e(TAG, "Error in fetching products :: ${e.message}")
        emit(emptyList())
    }

    override fun getProductByCategory(category: String): Flow<List<Product>> = flow {
        Log.d(TAG, "Getting products for category: $category")
        val products = apiService.fetchProductsByCategories(category)
        Log.d(TAG, "Fetched ${products.size} products for category: $category")
        emit(products)
    }.catch { e ->
        Log.e(TAG, "Error fetching products for category $category: ${e.message}", e)

        // Use a separate flow for the fallback to avoid transparency violations
        val fallbackProducts = try {
            Log.d(TAG, "Attempting fallback with all products")
            val allProducts = apiService.fetchProducts()
            Log.d(TAG, "Fallback: fetched ${allProducts.size} total products")

            // Try case-insensitive match
            val filtered = allProducts.filter {
                it.category?.lowercase() == category.lowercase()
            }
            Log.d(TAG, "Fallback: filtered ${filtered.size} products")
            filtered
        } catch (e2: Exception) {
            Log.e(TAG, "Fallback also failed: ${e2.message}", e2)
            emptyList()
        }

        emit(fallbackProducts)
    }

    override fun getCategories(): Flow<List<String>> = flow{
        Log.d(TAG, "Getting All Categories")
        val categories = apiService.fetchCategories()
        Log.d(TAG, "Fetched ${categories.size} Categories")
        emit(categories)
    }.catch {e ->
        Log.e(TAG, "Error : fetching categories :: ${e.message}", e)
        emit(emptyList())
    }

}