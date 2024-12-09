package com.example.establish.data

import com.example.establish.models.*
import retrofit2.http.*

interface ApiService {

    // Autenticación
    @POST("/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): AuthResponse

    @POST("/auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): AuthResponse

    // Productos
    @GET("/products")
    suspend fun getProducts(): List<Product>

    @GET("/products/{id}")
    suspend fun getProduct(@Path("id") id: String): Product

    // Establecimientos
    @GET("/stores")
    suspend fun getStores(): List<Store>

    @GET("/stores/{id}")
    suspend fun getStore(@Path("id") id: String): Store

    // Pedidos
    @POST("/orders")
    suspend fun createOrder(@Body orderRequest: OrderRequest): OrderResponse

    @GET("/orders/{id}")
    suspend fun getOrder(@Path("id") id: String): Order

    @PATCH("/orders/{id}")
    suspend fun updateOrder(@Path("id") id: String, @Body orderUpdate: OrderUpdateRequest): Order

    @DELETE("/orders/{id}")
    suspend fun deleteOrder(@Path("id") id: String): Response<Unit>
}
