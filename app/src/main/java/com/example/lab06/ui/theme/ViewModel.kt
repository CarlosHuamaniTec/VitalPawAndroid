package com.example.lab06.ui.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PetViewModel : ViewModel() {

    private val _petList = MutableStateFlow(
        listOf("Max", "Bombom", "Fitulais", "Rex")
    )
    val petList: StateFlow<List<String>> = _petList

    fun addPet(name: String) {
        _petList.update { it + name }
    }
}