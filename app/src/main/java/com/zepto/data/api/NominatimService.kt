package com.zepto.data.api

import com.zepto.data.models.location.NominatimResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NominatimService {

    @GET("reverse")
    suspend fun reverseGeocode(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("format") format: String = "json",
        @Query("addressdetails") addressDetails: Int = 1,
        @Header("User-Agent") userAgent: String = "Zepto/1.0(nsb95696@gmail.com)"
    ): NominatimResponse
    @GET("search")
    suspend fun searchNearBy(
        @Query("q") query: String,
        @Query("format") format: String,
        @Query("addressdetails") addressDetails: Int = 1,
        @Query("limit") limit: Int = 5,
        @Header("User-Agent") userAgent: String = "Zepto/1.0(nsb95696@email.com)"
    ):  NominatimResponse
    @GET("search")
    suspend fun searchInBoundingBox(
        @Query("q") query: String,
        @Query("format") format: String,
        @Query("addressdetails") addressDetails: Int = 1,
        @Query("limit") limit: Int = 5,
        @Query("viewbox") viewbox: String,
        @Query("bounded") bounded: Int = 1,
        @Header("User-Agent") userAgent: String = "Zepto/1.0(nsb95696@email.com)"
        ) : List<NominatimResponse>
}