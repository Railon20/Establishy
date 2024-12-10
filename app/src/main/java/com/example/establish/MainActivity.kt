package com.example.establish

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.establish.ui.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EstablishTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == REQUEST_CODE) {
        when (resultCode) {
            Activity.RESULT_OK -> {
                val payment = data?.getSerializableExtra(MercadoPagoCheckout.EXTRA_PAYMENT) as? Payment
                Toast.makeText(this, "Pago exitoso: ${payment?.id}", Toast.LENGTH_SHORT).show()
                // Aquí puedes notificar al backend sobre el pago exitoso
            }
            Activity.RESULT_CANCELED -> {
                val error = data?.getSerializableExtra(MercadoPagoCheckout.EXTRA_ERROR)
                Toast.makeText(this, "Error al procesar el pago", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

