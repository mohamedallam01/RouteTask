package com.example.routetask.model.repo

import ProductResponse
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
     suspend fun getProductResponse() : ProductResponse

}