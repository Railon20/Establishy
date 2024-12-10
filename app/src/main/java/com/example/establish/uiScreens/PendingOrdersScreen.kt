@Composable
fun PendingOrdersScreen(
    pendingOrders: List<PendingOrder>,
    onOrderClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pedidos Pendientes") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        if (pendingOrders.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No tienes pedidos pendientes", style = MaterialTheme.typography.h6)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(pendingOrders) { order ->
                    PendingOrderCard(order, onOrderClick)
                }
            }
        }
    }
}

@Composable
fun PendingOrderCard(order: PendingOrder, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick(order.orderId) },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Pedido: ${order.orderId}",
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "Estado: ${order.status}",
                style = MaterialTheme.typography.body2
            )
            Text(
                text = "Total: $${order.totalPrice}",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

data class PendingOrder(val orderId: String, val status: String, val totalPrice: Double)
