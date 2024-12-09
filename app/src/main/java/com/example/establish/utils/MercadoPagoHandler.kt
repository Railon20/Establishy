package com.example.establish.utils

import android.app.Activity
import android.content.Intent
import com.mercadopago.android.px.core.MercadoPagoCheckout
import com.mercadopago.android.px.model.Payment
import com.mercadopago.android.px.preferences.CheckoutPreference

class MercadoPagoHandler {

    companion object {
        private const val REQUEST_CODE = 123
        private const val PUBLIC_KEY = "TU_PUBLIC_KEY"

        fun startCheckout(activity: Activity, preferenceId: String) {
            MercadoPagoCheckout.Builder(PUBLIC_KEY, preferenceId)
                .build()
                .startPayment(activity, REQUEST_CODE)
        }

        fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?, onSuccess: (Payment) -> Unit, onFailure: (Throwable) -> Unit) {
            if (requestCode == REQUEST_CODE) {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val payment = data?.getParcelableExtra<Payment>(MercadoPagoCheckout.EXTRA_PAYMENT_RESULT)
                        payment?.let(onSuccess)
                    }
                    Activity.RESULT_CANCELED -> {
                        // Usuario canceló el pago
                        onFailure(Exception("Pago cancelado por el usuario"))
                    }
                    else -> {
                        onFailure(Exception("Error desconocido durante el pago"))
                    }
                }
            }
        }
    }
}
