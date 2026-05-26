package com.dap.crud3tablas_ktor.view.cliente.modifica

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dap.crud3tablas_ktor.conexionService.ConexionService
import com.dap.crud3tablas_ktor.model.ModelCliente
import kotlinx.coroutines.launch

class ModificaClienteViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val cedula: String = checkNotNull(savedStateHandle["cedula"])
    var data by mutableStateOf<ModelCliente?>(null)
    val conexionService = ConexionService()

    init {
        consultaId(cedula)
    }


    fun consultaId(cedula: String) {
        viewModelScope.launch {
            try {
                val datos = conexionService.consultaClienteId(cedula)
                data = datos
            }
            //Sin multiplataforma
            /*catch (e: io.ktor.client.plugins.ResponseException) {
                // Manejar el error si se cae el internet o falla el XAMPP
                println("Error en la conexión: ${e.response.status.value}")
            } catch (e: Exception) {
                // Este catch captura problemas de conexión (Ej: Servidor XAMPP apagado, sin Wi-Fi)
                Log.e("dap", "No se pudo conectar al backend", e)
            }*/
            // Con multiplataforma
              catch (e: Exception) {
                // Reemplazamos Log.e por println para que sea visible en cualquier consola (Xcode o Web)
                println("dap - Error de conexión en la plataforma actual: ${e.message}")
            }
        }
    }

    fun modifica(data: ModelCliente, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                conexionService.modificarCliente(data)
                onSuccess()

            }

            // Con multiplataforma
              catch (e: Exception) {
                // Reemplazamos Log.e por println para que sea visible en cualquier consola (Xcode o Web)
                println("dap -No modificado")
                onError()
            }
        }
    }
}
