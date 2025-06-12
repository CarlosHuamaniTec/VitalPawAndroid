package com.example.vitalpaw.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalpaw.data.model.DeviceModel
import com.example.vitalpaw.data.service.RetrofitClient
import com.example.vitalpaw.data.service.VitalPawApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DeviceViewModel : ViewModel() {
    private val apiService = RetrofitClient.retrofit.create(VitalPawApiService::class.java)

    private val _devices = MutableStateFlow<List<DeviceModel>>(emptyList())
    val devices: StateFlow<List<DeviceModel>> = _devices

    private val _selectedDevice = MutableStateFlow<DeviceModel?>(null)
    val selectedDevice: StateFlow<DeviceModel?> = _selectedDevice

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun createDevice(device: DeviceModel) {
        viewModelScope.launch {
            try {
                val createdDevice = apiService.createDevice(device)
                _devices.value = _devices.value + createdDevice
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al crear dispositivo: ${e.message}"
            }
        }
    }

    fun getDevice(id: Long) {
        viewModelScope.launch {
            try {
                val device = apiService.getDevice(id)
                _selectedDevice.value = device
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al obtener dispositivo: ${e.message}"
            }
        }
    }

    fun getDeviceByDeviceId(deviceId: String) {
        viewModelScope.launch {
            try {
                val device = apiService.getDeviceByDeviceId(deviceId)
                _selectedDevice.value = device
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al obtener dispositivo: ${e.message}"
            }
        }
    }

    fun updateDevice(id: Long, device: DeviceModel) {
        viewModelScope.launch {
            try {
                val updatedDevice = apiService.updateDevice(id, device)
                _devices.value = _devices.value.map { if (it.id == id) updatedDevice else it }
                _selectedDevice.value = updatedDevice
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al actualizar dispositivo: ${e.message}"
            }
        }
    }

    fun deleteDevice(id: Long) {
        viewModelScope.launch {
            try {
                apiService.deleteDevice(id)
                _devices.value = _devices.value.filter { it.id != id }
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al eliminar dispositivo: ${e.message}"
            }
        }
    }
}