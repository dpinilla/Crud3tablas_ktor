package com.dap.crud3tablas_ktor.view.producto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dap.crud3tablas_ktor.conexionService.ConexionService
import com.dap.crud3tablas_ktor.model.ModelProducto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ProductoViewModel: ViewModel() {
    private val _lista = MutableStateFlow<List<ModelProducto>>(emptyList())
    val listaProducto: StateFlow<List<ModelProducto>> = _lista.asStateFlow()
    val conexionService = ConexionService()

    fun visualiza() {
        viewModelScope.launch {
            try {
                val datos = conexionService.consultaProducto()
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


