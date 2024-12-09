package com.example.establish.iu

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.establish.models.Order
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.establish.utils.MercadoPagoHandler

@Composable
fun PaymentScreen(
    viewModel: PaymentViewModel = viewModel(),
    order: Order,
    onPaymentSuccess: () -> Unit,
    onPaymentFailure: (String) -> Unit,
    onCancel: () -> Unit
) {
    val appCommission = remember { order.totalPrice * 0.10 }
    val finalPrice = remember { order.totalPrice - appCommission }
    val context = LocalContext.current as Activity
    val preferenceId = viewModel.preferenceId // Obtén el preferenceId desde el ViewModel

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pago de Pedido") },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Cancelar"
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
            Text(
                text = "Resumen del Pedido",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Total: $${order.totalPrice}",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Comisión de la App: $${String.format("%.2f", appCommission)}",
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Total a Pagar: $${String.format("%.2f", finalPrice)}",
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    // Lógica de pago
                    onPaymentSuccess()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirmar Pago")
            }

            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar")
            }
        }
    }

    Button(onClick = {
        MercadoPagoHandler.startCheckout(context, preferenceId)
    }) {
        Text("Pagar con Mercado Pago")
    }


    // Manejar el resultado del pago
    context.activityResultHandler = { requestCode, resultCode, data ->
        MercadoPagoHandler.handleActivityResult(
            requestCode,
            resultCode,
            data,
            onSuccess = {
                onPaymentSuccess()
            },
            onFailure = {
                onPaymentFailure(it.message ?: "Error desconocido")
            }
        )
    }
}
