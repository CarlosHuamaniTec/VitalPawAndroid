package com.example.vitalpaw.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalpaw.data.model.PasswordChangeModel
import com.example.vitalpaw.data.model.UserCreateModel
import com.example.vitalpaw.data.model.UserModel
import com.example.vitalpaw.data.service.RetrofitClient
import com.example.vitalpaw.data.service.VitalPawApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val apiService = RetrofitClient.retrofit.create(VitalPawApiService::class.java)

    private val _users = MutableStateFlow<List<UserModel>>(emptyList())
    val users: StateFlow<List<UserModel>> = _users

    private val _selectedUser = MutableStateFlow<UserModel?>(null)
    val selectedUser: StateFlow<UserModel?> = _selectedUser

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun createUser(user: UserCreateModel) {
        viewModelScope.launch {
            try {
                val createdUser = apiService.createUser(user)
                _users.value = _users.value + createdUser
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al crear usuario: ${e.message}"
            }
        }
    }

    fun getUser(id: Long) {
        viewModelScope.launch {
            try {
                val user = apiService.getUser(id)
                _selectedUser.value = user
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al obtener usuario: ${e.message}"
            }
        }
    }

    fun updateUser(id: Long, user: UserCreateModel) {
        viewModelScope.launch {
            try {
                val updatedUser = apiService.updateUser(id, user)
                _users.value = _users.value.map { if (it.id == id) updatedUser else it }
                _selectedUser.value = updatedUser
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al actualizar usuario: ${e.message}"
            }
        }
    }

    fun deleteUser(id: Long) {
        viewModelScope.launch {
            try {
                apiService.deleteUser(id)
                _users.value = _users.value.filter { it.id != id }
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al eliminar usuario: ${e.message}"
            }
        }
    }

    fun confirmAccount(token: String) {
        viewModelScope.launch {
            try {
                val confirmedUser = apiService.confirmAccount(token)
                _users.value = _users.value.map { if (it.id == confirmedUser.id) confirmedUser else it }
                _selectedUser.value = confirmedUser
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al confirmar cuenta: ${e.message}"
            }
        }
    }

    fun changePassword(id: Long, newPassword: String) {
        viewModelScope.launch {
            try {
                val updatedUser = apiService.changePassword(id, PasswordChangeModel(newPassword))
                _users.value = _users.value.map { if (it.id == id) updatedUser else it }
                _selectedUser.value = updatedUser
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al cambiar contraseña: ${e.message}"
            }
        }
    }
}