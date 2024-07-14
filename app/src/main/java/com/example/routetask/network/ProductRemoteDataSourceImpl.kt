package com.example.routetask.network

import android.util.Log
import com.example.routetask.model.entities.ProductResponse
import javax.inject.Inject


class ProductRemoteDataSourceImpl @Inject constructor(
    private val productService: ProductService
): ProductRemoteDataSource {

    private val TAG = "ProductRemoteDataSourceImpl"

    override suspend fun getProducts(): ProductResponse {
        val response = productService.getProducts()
        Log.d(TAG, "getProducts: $response ")
        return response
    }
}