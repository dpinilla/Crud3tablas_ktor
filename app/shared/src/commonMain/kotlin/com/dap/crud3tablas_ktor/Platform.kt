package com.dap.crud3tablas_ktor

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform