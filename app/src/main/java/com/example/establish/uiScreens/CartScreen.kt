@Composable
fun CartScreen(
    cartItems: List<CartItem>,
    onOrderClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrito") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Tu carrito está vacío", style = MaterialTheme.typography.h6)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(cartItems) { cartItem ->
                    CartItemCard(cartItem, onOrderClick)
                }
            }
        }
    }
}

@Composable
fun CartItemCard(cartItem: CartItem, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick(cartItem.orderId) },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Orden: ${cartItem.orderId}",
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "Total: $${cartItem.totalPrice}",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

data class CartItem(val orderId: String, val totalPrice: Double)
