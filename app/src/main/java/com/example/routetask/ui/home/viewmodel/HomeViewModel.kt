package com.example.routetask.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.routetask.model.entities.Product
import com.example.routetask.model.entities.ProductResponse
import com.example.routetask.model.repo.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val _allProducts = MutableStateFlow(Result.success(ProductResponse(emptyList())))
    val allProducts : StateFlow<Result<ProductResponse>> get() = _allProducts


    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            productsRepository.getProducts().collect { result ->
                _allProducts.value = result
            }
        }
    }

}