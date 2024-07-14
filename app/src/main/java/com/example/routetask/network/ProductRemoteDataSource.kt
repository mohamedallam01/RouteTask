package com.example.routetask.network

import com.example.routetask.model.entities.ProductResponse


interface ProductRemoteDataSource {

    suspend fun getProducts(): ProductResponse
}