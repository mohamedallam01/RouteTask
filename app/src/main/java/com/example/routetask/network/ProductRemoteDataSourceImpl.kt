package com.example.routetask.network

import android.util.Log
import com.example.routetask.model.entities.ProductResponse
import retrofit2.Response
import javax.inject.Inject


class ProductRemoteDataSourceImpl @Inject constructor(
    private val productService: ProductService
): ProductRemoteDataSource {


    override suspend fun getProducts(): Result<ProductResponse> {
        return try {
            val response = productService.getProducts()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to fetch products: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}