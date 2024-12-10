package com.example.establish.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ObservationScreen(
    navController: NavController,
    isProduct: Boolean,
    title: String,
    imageUrl: String,
    price: String = "",
    description: String = "",
    storeName: String = "",
    storeAddress: String = "",
    isOpen: Boolean = true,
    onAddToCart: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Back Button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Volver", style = MaterialTheme.typography.bodyLarge)
        }

        // Content
        Column(modifier = Modifier.padding(16.dp)) {
            // Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                // Imagen simulada. Usa una biblioteca como Coil para cargar imágenes reales
                Text(
                    text = "Imagen: $imageUrl",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Title and Details
            Text(text = title, style = MaterialTheme.typography.headlineMedium)

            if (isProduct) {
                Text(text = "Precio: $price", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = description, style = MaterialTheme.typography.bodySmall)

                // Add to cart button
                Button(
                    onClick = onAddToCart,
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Agregar al Pedido")
                }
            } else {
                Text(text = "Establecimiento: $storeName", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Dirección: $storeAddress", style = MaterialTheme.typography.bodySmall)
                Text(
                    text = if (isOpen) "Abierto" else "Cerrado",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isOpen) Color.Green else Color.Red
                )

                Spacer(modifier = Modifier.height(16.dp))

                // View Products Button
                Button(
                    onClick = { /* Navigate to product list for this store */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Ver Productos")
                }
            }
        }
    }
}
