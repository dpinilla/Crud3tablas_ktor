package com.dap.crud3tablas_ktor.view.cliente.detalle


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dap.crud3tablas_ktor.conexionService.ConexionService
import com.dap.crud3tablas_ktor.model.ModelCliente
import kotlinx.coroutines.launch


class DetalleClienteViewModel(
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

            // Con multiplataforma
              catch (e: Exception) {
                // Reemplazamos Log.e por println para que sea visible en cualquier consola (Xcode o Web)
                println("dap - Error de conexión en la plataforma actual: ${e.message}")
            }
        }
    }

    fun eliminaCliente(
        cedula: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                conexionService.eliminarCliente(cedula)
                onSuccess()

            }
            // Con multiplataforma
              catch (e: Exception) {
                // Reemplazamos Log.e por println para que sea visible en cualquier consola (Xcode o Web)
                println("dap - Error de conexión en la plataforma actual: ${e.message}")
                onError()
            }
        }
    }
}


