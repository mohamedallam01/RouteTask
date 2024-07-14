package com.example.routetask.model.repo

import com.example.routetask.model.entities.Product

interface ProductsRepository {
     suspend fun getProducts() : List<Product>

}