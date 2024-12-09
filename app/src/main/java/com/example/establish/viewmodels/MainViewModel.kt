package com.example.establish.viewmodels

import androidx.lifecycle.ViewModel
import com.example.establish.models.Order
import com.example.establish.models.Product
import com.example.establish.repository.DataRepository

class MainViewModel : ViewModel() {
    val products = DataRepository.getProducts()
    val orders = DataRepository.getOrders()

    fun addOrder(order: Order) {
        DataRepository.addOrder(order)
    }

    fun searchProducts(query: String): List<Product> {
        return products.filter { it.name.contains(query, ignoreCase = true) }
    }
}
