@Composable
fun OrderHistoryScreen(
    completedOrders: List<CompletedOrder>,
    onOrderClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial de Pedidos") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        if (completedOrders.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No tienes pedidos completados", style = MaterialTheme.typography.h6)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(completedOrders) { order ->
                    CompletedOrderCard(order, onOrderClick)
                }
            }
        }
    }
}

@Composable
fun CompletedOrderCard(order: CompletedOrder, onClick: (String) -> Unit) {
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
                text = "Fecha: ${order.date}",
                style = MaterialTheme.typography.body2
            )
            Text(
                text = "Total: $${order.totalPrice}",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

data class CompletedOrder(val orderId: String, val date: String, val totalPrice: Double)
