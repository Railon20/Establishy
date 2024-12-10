package com.example.establish.ui.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mercadopago.android.px.core.MercadoPagoCheckout
import com.mercadopago.android.px.model.Payment
import com.example.establish.utils.MercadoPagoConfig

@Composable
fun PaymentScreen(
    navController: NavHostController,
    orderId: String,
    totalAmount: Double, // Monto total del pedido
    onPaymentSuccess: (String) -> Unit, // Callback para manejar el éxito del pago
    onPaymentFailure: () -> Unit, // Callback para manejar errores
    onBackClick: () -> Unit
) {
    val activity = LocalContext.current as? Activity

    LaunchedEffect(key1 = totalAmount) {
        if (activity != null) {
            val preferenceId = createPaymentPreference(orderId, totalAmount)
            MercadoPagoCheckout.Builder(MercadoPagoConfig.PUBLIC_KEY, preferenceId).build()
                .startPayment(activity, REQUEST_CODE)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Procesando el pago...")

        Button(onClick = { onBackClick() }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Cancelar")
        }
    }
}

private const val REQUEST_CODE = 100

private fun createPaymentPreference(orderId: String, totalAmount: Double): String {
    // TODO: Implementar una solicitud al backend para crear una preferencia de pago en Mercado Pago.
    // Aquí debes enviar el monto total y recibir el ID de preferencia.
    return "PREFERENCE_ID"
}
