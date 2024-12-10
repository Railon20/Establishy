@Composable
fun HomeScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToOrders: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onProductClick: (String) -> Unit,
    recommendedProducts: List<Product>
) {
    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") },
                    selected = true,
                    onClick = { /* No-op: already on home */ }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito") },
                    label = { Text("Carrito") },
                    selected = false,
                    onClick = onNavigateToCart
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Historial") },
                    label = { Text("Pedidos") },
                    selected = false,
                    onClick = onNavigateToOrders
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Cuenta") },
                    label = { Text("Cuenta") },
                    selected = false,
                    onClick = onNavigateToProfile
                )
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Buscar productos o tiendas...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onNavigateToSearch() }
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(recommendedProducts) { product ->
                    ProductCard(product, onProductClick)
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .size(200.dp, 150.dp)
            .clickable { onClick(product.id) },
        elevation = 4.dp
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = product.name,
                modifier = Modifier.size(100.dp)
            )
            Text(text = product.name, style = MaterialTheme.typography.h6)
            Text(text = "$${product.price}", style = MaterialTheme.typography.body2)
            Button(onClick = { /* Add to cart logic */ }) {
                Text("Agregar")
            }
        }
    }
}

data class Product(val id: String, val name: String, val price: Double, val image: Int)
