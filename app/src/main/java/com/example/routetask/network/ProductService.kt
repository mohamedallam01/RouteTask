package com.example.routetask.network

import ProductResponse
import android.provider.UserDictionary.Words.APP_ID
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("products")
    suspend fun getProducts(): ProductResponse
}