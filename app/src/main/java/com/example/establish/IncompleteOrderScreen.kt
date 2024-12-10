package com.example.establish.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun IncompleteOrderScreen(
    navController: NavController,
    orderId: String,
    products: List<String>, // Lista de productos simulada
    onRemoveProduct: (String) -> Unit,
    onCompleteOrder: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Header
        Text(
            text = "Orden Incompleta: $orderId",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        // Product List
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(products.size) { index ->
                ProductItemCard(
                    productName = products[index],
                    onRemove = { onRemoveProduct(products[index]) }
                )
            }
        }

        // Footer: Total Price and Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total: \$120.00", style = MaterialTheme.typography.bodyLarge)
            Button(
                onClick = {
                    coroutineScope.launch {
                        val preferenceId = createPaymentPreference(
                            title = "Pedido de productos",
                            price = totalOrderPrice,
                            email = userEmail
                        )
                        navController.navigate("payment/$preferenceId")
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Realizar Pedido")
            }

        }
        }
    }
}

@Composable
fun ProductItemCard(productName: String, onRemove: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = productName, style = MaterialTheme.typography.bodyLarge)
            IconButton(onClick = onRemove) {
                Icon(Icons.Default.Delete, contentDescription = "Remove")
            }
        }
    }
}
