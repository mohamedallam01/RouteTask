package com.example.routetask.model.repo

import com.example.routetask.model.entities.ProductResponse
import com.example.routetask.network.ProductRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ProductsRepositoryImpl @Inject constructor(
    private val productRemoteDataSource: ProductRemoteDataSource
) : ProductsRepository {

    override  fun getProducts(): Flow<Result<ProductResponse>> = flow {
        emit(productRemoteDataSource.getProducts())
    }

}