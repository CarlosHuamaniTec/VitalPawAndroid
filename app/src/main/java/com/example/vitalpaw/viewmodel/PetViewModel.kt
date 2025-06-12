package com.example.vitalpaw.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalpaw.data.model.PetCreateModel
import com.example.vitalpaw.data.model.PetModel
import com.example.vitalpaw.data.service.RetrofitClient
import com.example.vitalpaw.data.service.VitalPawApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PetViewModel : ViewModel() {
    private val apiService = RetrofitClient.retrofit.create(VitalPawApiService::class.java)

    private val _pets = MutableStateFlow<List<PetModel>>(emptyList())
    val pets: StateFlow<List<PetModel>> = _pets

    private val _selectedPet = MutableStateFlow<PetModel?>(null)
    val selectedPet: StateFlow<PetModel?> = _selectedPet

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun createPet(pet: PetCreateModel) {
        viewModelScope.launch {
            try {
                val createdPet = apiService.createPet(pet)
                _pets.value += createdPet
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al crear mascota: ${e.message}"
            }
        }
    }

    fun getPet(id: Long) {
        viewModelScope.launch {
            try {
                val pet = apiService.getPet(id)
                _selectedPet.value = pet
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al obtener mascota: ${e.message}"
            }
        }
    }

    fun updatePet(id: Long, pet: PetCreateModel) {
        viewModelScope.launch {
            try {
                val updatedPet = apiService.updatePet(id, pet)
                _pets.value = _pets.value.map { if (it.id == id) updatedPet else it }
                _selectedPet.value = updatedPet
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al actualizar mascota: ${e.message}"
            }
        }
    }
}