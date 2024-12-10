package com.example.establish.uiNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.establish.ui.screens.*

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.Home.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screens.Home.route) {
            HomeScreen(
                onCartClick = { navController.navigate(Screens.Cart.route) },
                onHistoryClick = { navController.navigate(Screens.OrderHistory.route) },
                onPendingOrdersClick = { navController.navigate(Screens.PendingOrders.route) },
                onProfileClick = { navController.navigate(Screens.Profile.route) },
                onSearchResultClick = { result ->
                    if (result.isProduct) {
                        navController.navigate(Screens.ProductDetail.createRoute(result.id))
                    } else {
                        navController.navigate(Screens.StoreDetail.createRoute(result.id))
                    }
                }
            )
        }
        composable(Screens.Cart.route) {
            CartScreen(
                cartItems = getCartItems(), // Implementa esta función para obtener los ítems del carrito
                onOrderClick = { orderId ->
                    navController.navigate(Screens.IncompleteOrder.createRoute(orderId))
                },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screens.OrderHistory.route) {
            OrderHistoryScreen(
                completedOrders = getCompletedOrders(), // Implementa esta función para obtener los pedidos completados
                onOrderClick = { orderId ->
                    navController.navigate(Screens.OrderDetail.createRoute(orderId))
                },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screens.PendingOrders.route) {
            PendingOrdersScreen(
                pendingOrders = getPendingOrders(), // Implementa esta función para obtener los pedidos pendientes
                onOrderClick = { orderId ->
                    navController.navigate(Screens.OrderDetail.createRoute(orderId))
                },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screens.Profile.route) {
            ProfileScreen(
                onPasswordChange = { /* Implementa lógica para cambiar contraseña */ },
                onEmailChange = { /* Implementa lógica para cambiar email */ },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screens.IncompleteOrder.routeWithArgs) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: return@composable
            IncompleteOrderScreen(
                orderId = orderId,
                onDiscardProduct = { /* Implementa lógica para descartar productos */ },
                onPlaceOrder = { /* Implementa lógica para realizar el pedido */ },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screens.ProductDetail.routeWithArgs) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: return@composable
            ProductDetailScreen(
                productId = productId,
                onAddToOrder = { /* Implementa lógica para agregar producto a una orden */ },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screens.StoreDetail.routeWithArgs) { backStackEntry ->
            val storeId = backStackEntry.arguments?.getString("storeId") ?: return@composable
            StoreDetailScreen(
                storeId = storeId,
                onViewProducts = { /* Implementa lógica para ver productos de la tienda */ },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
