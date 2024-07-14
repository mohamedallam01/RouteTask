package com.example.routetask.network

import com.example.routetask.model.entities.ProductResponse
import retrofit2.Response


interface ProductRemoteDataSource {

    suspend fun getProducts(): Result<ProductResponse>
}