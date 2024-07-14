package com.example.routetask.network

import com.example.routetask.model.entities.ProductResponse
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    suspend fun getProducts(): ProductResponse
}