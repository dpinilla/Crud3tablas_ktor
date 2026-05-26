package com.dap.crud3tablas_ktor

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dap.crud3tablas_ktor.navigation.Navigation
import com.dap.crud3tablas_ktor.view.cliente.ClienteScreen
import com.dap.crud3tablas_ktor.view.cliente.ClienteViewModel
import com.dap.crud3tablas_ktor.view.cliente.inserta.InsertaClienteScreen
import com.dap.crud3tablas_ktor.view.menu.MenuScreen
import com.dap.crud3tablas_ktor.view.producto.ProductoScreen
import com.dap.crud3tablas_ktor.view.producto.ProductoViewModel
import org.jetbrains.compose.resources.painterResource

import crud3tablas_ktor.app.shared.generated.resources.Res
import crud3tablas_ktor.app.shared.generated.resources.compose_multiplatform


@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModelCliente: ClienteViewModel = viewModel { ClienteViewModel() }
        val viewModelProducto: ProductoViewModel = viewModel { ProductoViewModel() }

        // Llamas a tu función de navegación pasando los parámetros exactamente igual
        Navigation(
            viewModelCliente = viewModelCliente,
            viewModelProducto = viewModelProducto
        )
    }
    /*MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("¡Haz clic aquí!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }*/
}