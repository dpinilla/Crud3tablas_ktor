package com.dap.crud3tablas_ktor.model

import kotlinx.serialization.Serializable

@Serializable
data class ModelCliente(
    val cli_cedula: String?=null,
    val cli_nombre: String?=null,
    val cli_edad: Int?=null
)
