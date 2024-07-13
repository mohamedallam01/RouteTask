package com.example.routetask.network

import ProductResponse
import android.util.Log
import com.example.routetask.di.ProductApiModule

class ProductRemoteDataSourceImpl: ProductRemoteDataSource {

    private val TAG = "ProductRemoteDataSourceImpl"

    private val retrofit by lazy {
        ProductApiModule.ProductApiModule().provideRetrofit()
    }

    private val productService :ProductService  by lazy {
        ProductApiModule.ProductApiModule().provideProductService(retrofit)
    }
    override suspend fun getProducts(): ProductResponse {
        val response = productService.getProducts()
        Log.d(TAG, "getProducts: $response ")
        return response
    }
}