package com.example.establish.iu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.cyompose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.establish.models.Order

@Composable
fun CartScreen(orders: List<Order> = emptyList(), onOrderClick: (Order) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrito") },
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
                        text = "Tu carrito está vacío.",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                items(orders) { order ->
                    OrderCard(order = order, onClick = { onOrderClick(order) })
                }
            }
        }
    }
}

@Composable
fun OrderCard(order: Order, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Orden #${order.id}", style = MaterialTheme.typography.body1)
            Text(text = "Total: $${order.totalPrice}", color = MaterialTheme.colorScheme.secondary)
        }
    }
}
