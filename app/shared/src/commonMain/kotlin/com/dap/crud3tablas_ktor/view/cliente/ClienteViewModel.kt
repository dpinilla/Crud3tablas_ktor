package com.dap.crud3tablas_ktor.view.cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dap.crud3tablas_ktor.conexionService.ConexionService
import com.dap.crud3tablas_ktor.model.ModelCliente
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ClienteViewModel : ViewModel() {
    private val _lista = MutableStateFlow<List<ModelCliente>>(emptyList())
    val listaCliente: StateFlow<List<ModelCliente>> = _lista.asStateFlow()
    val conexionService = ConexionService()

    fun visualiza() {
        viewModelScope.launch {
            try {
                val datos = conexionService.consultaCliente()
                _lista.value = datos
            }


            // Con multiplataforma
              catch (e: Exception) {
                // Reemplazamos Log.e por println para que sea visible en cualquier consola (Xcode o Web)
                println("dap - Error de conexión en la plataforma actual: ${e.message}")
            }
        }
    }
}

