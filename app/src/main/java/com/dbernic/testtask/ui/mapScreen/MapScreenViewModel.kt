package com.dbernic.testtask.ui.mapScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbernic.testtask.data.repositories.HelloRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val repository: HelloRepository,
): ViewModel(){

    private val _alert = MutableStateFlow("")
    val alert = _alert.asStateFlow()

    fun clickEvent() {
        viewModelScope.launch {
            val alert = try {
                val result = repository.getHello()
                if (result.isSuccessful) {
                    result.body()?.hello ?: "Unknown error"
                } else {
                    result.errorBody().toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                e.message ?: "Unknown error"
            }

            showAlert(alert)

        }
    }

    fun showAlert(string: String) {
        _alert.value = string
    }

    fun hideAlert() {
        _alert.value = ""
    }
}