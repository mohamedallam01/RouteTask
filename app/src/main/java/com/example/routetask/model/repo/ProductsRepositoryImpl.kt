package com.example.routetask.model.repo

import android.util.Log
import com.example.routetask.model.entities.Product
import com.example.routetask.network.ProductRemoteDataSource
import javax.inject.Inject


class ProductsRepositoryImpl @Inject constructor(
    private val productRemoteDataSource: ProductRemoteDataSource
) : ProductsRepository {

    private val TAG = "ProductsRepositoryImpl"
    override suspend fun getProducts(): List<Product> {
        Log.d(TAG, "getWeatherResponse: ${productRemoteDataSource.getProducts()} ")
        return productRemoteDataSource.getProducts().products
    }

}