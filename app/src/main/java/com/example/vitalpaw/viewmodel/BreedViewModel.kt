package com.example.vitalpaw.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalpaw.data.model.BreedModel
import com.example.vitalpaw.data.service.RetrofitClient
import com.example.vitalpaw.data.service.VitalPawApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BreedViewModel : ViewModel() {
    private val apiService = RetrofitClient.retrofit.create(VitalPawApiService::class.java)

    private val _breeds = MutableStateFlow<List<BreedModel>>(emptyList())
    val breeds: StateFlow<List<BreedModel>> = _breeds

    private val _selectedBreed = MutableStateFlow<BreedModel?>(null)
    val selectedBreed: StateFlow<BreedModel?> = _selectedBreed

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun createBreed(breed: BreedModel) {
        viewModelScope.launch {
            try {
                val createdBreed = apiService.createBreed(breed)
                _breeds.value = _breeds.value + createdBreed
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al crear raza: ${e.message}"
            }
        }
    }

    fun getBreed(id: Long) {
        viewModelScope.launch {
            try {
                val breed = apiService.getBreed(id)
                _selectedBreed.value = breed
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al obtener raza: ${e.message}"
            }
        }
    }

    fun getAllBreeds() {
        viewModelScope.launch {
            try {
                val breeds = apiService.getAllBreeds()
                _breeds.value = breeds
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al obtener razas: ${e.message}"
            }
        }
    }
}