package com.example.establish.models

data class Order(
    val id: String,
    val products: List<Product>,
    val totalPrice: Double,
    val status: String // "incomplete", "pending", "complete"
)
