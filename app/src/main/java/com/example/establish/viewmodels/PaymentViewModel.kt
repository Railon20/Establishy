package com.example.establish.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.tuapp.data.RetrofitClient

class PaymentViewModel : ViewModel() {
    var preferenceId: String? = null
        private set

    fun createPreference(orderId: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.createPreference(orderId)
                preferenceId = response.preferenceId
            } catch (e: Exception) {
                // Manejar error
            }
        }
    }
}
