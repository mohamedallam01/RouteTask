package com.example.routetask.model.repo

import com.example.routetask.model.entities.Product
import com.example.routetask.model.entities.ProductResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductsRepository {
      fun getProducts() : Flow<Result<ProductResponse>>

}