package com.example.establish.iu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.establish.models.Product
import com.example.establish.models.Store

@Composable
fun StoreDetailsScreen(store: Store, products: List<Product>, onProductClick: (Product) -> Unit, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = store.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colorScheme.primary
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = "Dirección: ${store.address}",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = if (store.isOpen) "Abierto" else "Cerrado",
                modifier = Modifier.padding(horizontal = 16.dp),
                color = if (store.isOpen) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                items(products) { product ->
                    ProductCard(product = product, onClick = { onProductClick(product) })
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = product.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = "$${product.price}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
