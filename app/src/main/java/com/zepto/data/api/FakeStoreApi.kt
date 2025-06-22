package com.zepto.data.api

import com.zepto.data.models.Product
import com.zepto.data.models.Rating
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class FakeStoreApi {
    private val baseUrl = "https://fakestoreapi.com"

    /*suspend fun fetchProduct(): List<Product> = withContext(Dispatchers.IO) {
        val url = URL("baseUrl/products")
        val connection = url.openConnection() as HttpURLConnection
        try {
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            connection.requestMethod = "GET"
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                prepareProductResponse(response)
            } else {
                emptyList()
            }
        } finally {
            connection.disconnect()
        }
    }*/

    /*private fun prepareProductResponse(response: String): List<Product> {
        val products = mutableListOf<Product>()
        val jsonArray = JSONArray(response)
        for (i in 0 until jsonArray.length()) {
            val jsonObj = jsonArray.getJSONObject(i)
            val id = jsonObj.getInt("id")
            val title = jsonObj.getInt("title")
            val name = jsonObj.getString("name")
            val price = jsonObj.getDouble("price")
            val imageUrl = jsonObj.getString("image")
            val category = jsonObj.getString("category")
            val description = jsonObj.getString("description")
            val rating = jsonObj.getJSONObject("rating")
            val rate = rating.getDouble("rate")
            val count = rating.getInt("count")
            products.add(
                Product(
                    id = id,
                    name = name,
                    price = price,
                    imageUrl = imageUrl,
                    category = category,
                    description = description,
                    rating = Rating(
                        rate = rate,
                        count = count
                    )
                )
            )
        }
    }*/
}