package com.example.establish.iu.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.establish.models.Product
import com.example.establish.ui.*

@Composable
fun NavGraph(navController: NavHostController, products: List<Product>, onOrderUpdate: () -> Unit) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(products = products, onProductClick = { product ->
                navController.navigate("product/${product.id}")
            })
        }
        composable("cart") {
            CartScreen(onOrderClick = { navController.navigate("order_details") })
        }
        composable("order_history") {
            OrderHistoryScreen()
        }
        composable("pending_orders") {
            PendingOrdersScreen()
        }
        composable("profile") {
            ProfileScreen()
        }
        composable("product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            val product = products.find { it.id == productId }
            if (product != null) {
                ProductDetailsScreen(product = product, onBack = { navController.popBackStack() })
            }
        }
    }
}
