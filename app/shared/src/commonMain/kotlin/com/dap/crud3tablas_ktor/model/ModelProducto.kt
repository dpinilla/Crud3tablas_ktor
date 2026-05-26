package com.dap.crud3tablas_ktor.model

import kotlinx.serialization.Serializable

@Serializable
data class ModelProducto(
    val pro_id: Int? = null,
    val pro_nombre: String? = null
)
