package com.example.routetask.model.repo

import ProductResponse
import android.util.Log
import com.example.routetask.network.ProductRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productRemoteDataSource: ProductRemoteDataSource
) : ProductsRepository {

    private val TAG = "ProductsRepositoryImpl"
    override suspend fun getProductResponse(): ProductResponse {
        Log.d(TAG, "getWeatherResponse: ${productRemoteDataSource.getProducts()} ")
        return productRemoteDataSource.getProducts()
    }

}