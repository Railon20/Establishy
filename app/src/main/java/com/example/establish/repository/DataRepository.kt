package com.example.establish.repository

import com.example.establish.models.Product
import com.example.establish.models.Order

object DataRepository {
    val products = listOf(
        Product("1", "Producto A", 100.0, "url_imagen_1", "comida"),
        Product("2", "Producto B", 150.0, "url_imagen_2", "limpieza")
    )

    val orders = mutableListOf<Order>()

    fun getProducts(): List<Product> = products
    fun getOrders(): List<Order> = orders
    fun addOrder(order: Order) {
        orders.add(order)
    }
}
