package com.dap.crud3tablas_ktor.view.cliente.inserta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dap.crud3tablas_ktor.conexionService.ConexionService
import com.dap.crud3tablas_ktor.model.ModelCliente
import kotlinx.coroutines.launch

class InsertaClienteViewModel: ViewModel() {
    val conexionService = ConexionService()
    fun guardar(data: ModelCliente, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                conexionService.insertaCliente(data)
                onSuccess()

            } catch (e: Exception) {

                    onError()

            }
        }
    }

}

