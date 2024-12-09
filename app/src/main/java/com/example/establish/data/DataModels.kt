package com.example.establish.data
h
data class Product(
    val id: String,
    val name: String,
    val price: Double
)

data class Store(
    val id: String,
    val name: String,
    val address: String,
    val isOpen: Boolean
)

data class Order(
    val id: String,
    val userId: String,
    val products: List<Product>,
    val totalPrice: Double
)

data class LoginRequest(val email: String, val password: String)
data class RegisterRequest(val email: String, val password: String)
data class AuthResponse(val token: String, val userId: String)
data class OrderRequest(val userId: String, val productIds: List<String>)
data class OrderResponse(val order: Order)
data class OrderUpdateRequest(val products: List<Product>)
