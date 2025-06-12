package com.example.vitalpaw.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalpaw.data.model.AlertModel
import com.example.vitalpaw.data.service.RetrofitClient
import com.example.vitalpaw.data.service.VitalPawApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlertViewModel : ViewModel() {
    private val apiService = RetrofitClient.retrofit.create(VitalPawApiService::class.java)

    private val _alerts = MutableStateFlow<List<AlertModel>>(emptyList())
    val alerts: StateFlow<List<AlertModel>> = _alerts

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getAlertsByPetId(petId: Long, limit: Int = 10) {
        viewModelScope.launch {
            try {
                val alerts = apiService.getAlertsByPetId(petId, limit)
                _alerts.value = alerts
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al obtener alertas: ${e.message}"
            }
        }
    }

    fun deleteAlert(alertId: Long) {
        viewModelScope.launch {
            try {
                apiService.deleteAlert(alertId)
                _alerts.value = _alerts.value.filter { it.id != alertId }
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al eliminar alerta: ${e.message}"
            }
        }
    }
}