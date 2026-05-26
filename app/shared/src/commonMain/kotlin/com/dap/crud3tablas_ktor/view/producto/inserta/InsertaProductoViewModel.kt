package com.dap.crud3tablas_ktor.view.producto.inserta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dap.crud3tablas_ktor.conexionService.ConexionService
import com.dap.crud3tablas_ktor.model.ModelProducto
import kotlinx.coroutines.launch


class InsertaProductoViewModel: ViewModel() {
    val conexionService = ConexionService()
    fun guardar(data: ModelProducto, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                conexionService.insertaProducto(data)
                onSuccess()

            } catch (e: Exception) {

                onError()

            }
        }
    }


}
