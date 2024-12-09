package com.example.establish.iu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.establish.models.Order

@Composable
fun PendingOrdersScreen(orders: List<Order> = emptyList(), onOrderClick: (Order) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pedidos pendientes") },
                backgroundColor = MaterialTheme.colorScheme.primary
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (orders.isEmpty()) {
                item {
                    Text(
                        text = "No tienes pedidos pendientes.",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                items(orders.filter { it.status == "pending" }) { order ->
                    OrderCard(order = order, onClick = { onOrderClick(order) })
                }
            }
        }
    }
}
