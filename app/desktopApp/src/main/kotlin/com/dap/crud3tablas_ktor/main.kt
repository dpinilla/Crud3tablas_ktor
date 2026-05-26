package com.dap.crud3tablas_ktor

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Crud3tablas_ktor",
    ) {
        App()
    }
}