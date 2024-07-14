package com.example.routetask.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.routetask.model.entities.Product
import com.example.routetask.model.repo.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val TAG = "HomeViewModel"

    private val _allProducts: MutableStateFlow<List<Product>?> = MutableStateFlow(null)
    val allProducts = _allProducts.asStateFlow()


    init {
        getAllProducts()
    }

     private fun getAllProducts() {

        viewModelScope.launch(Dispatchers.IO) {
            _allProducts.value = productsRepository.getProducts()
        }

        Log.d(TAG, "getAllProducts: ${allProducts.value}")

    }

}