package com.example.establish.uiNavigation

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Cart : Screens("cart")
    object OrderHistory : Screens("order_history")
    object PendingOrders : Screens("pending_orders")
    object Profile : Screens("profile")
    object IncompleteOrder : Screens("incomplete_order/{orderId}") {
        fun createRoute(orderId: String) = "incomplete_order/$orderId"
    }
    object ProductDetail : Screens("product_detail/{productId}") {
        fun createRoute(productId: String) = "product_detail/$productId"
    }
    object StoreDetail : Screens("store_detail/{storeId}") {
        fun createRoute(storeId: String) = "store_detail/$storeId"
    }
}
