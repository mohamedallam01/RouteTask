package com.example.routetask.network

import ProductResponse

interface ProductRemoteDataSource {

    suspend fun getProducts(): ProductResponse
}