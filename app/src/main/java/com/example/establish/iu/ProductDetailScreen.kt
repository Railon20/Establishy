package com.example.establish.iu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.establish.models.Product

@Composable
fun ProductDetailsScreen(product: Product, onBack: () -> Unit, onAddToOrder: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = product.name) },
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = product.name, style = MaterialTheme.typography.headlineSmall)
            Text(text = "Precio: $${product.price}", style = MaterialTheme.typography.bodyLarge)

            Button(onClick = {
                // Lógica para añadir el producto a un pedido
                onAddToOrder(product.id)
            }) {
                Text(text = "Añadir al pedido")
            }
        }
    }
}
