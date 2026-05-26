package com.dap.crud3tablas_ktor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dap.crud3tablas_ktor.view.cliente.ClienteScreen
import com.dap.crud3tablas_ktor.view.cliente.ClienteViewModel
import com.dap.crud3tablas_ktor.view.cliente.detalle.DetalleClienteScreen
import com.dap.crud3tablas_ktor.view.cliente.inserta.InsertaClienteScreen
import com.dap.crud3tablas_ktor.view.cliente.modifica.ModificaClienteScreen
import com.dap.crud3tablas_ktor.view.menu.MenuScreen
import com.dap.crud3tablas_ktor.view.producto.ProductoScreen
import com.dap.crud3tablas_ktor.view.producto.ProductoViewModel
import com.dap.crud3tablas_ktor.view.producto.inserta.InsertaProductoScreen

@Composable
fun Navigation(
    viewModelCliente: ClienteViewModel,
    viewModelProducto: ProductoViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "menu"
    ) {
        composable("menu") {
            MenuScreen(
                onGoCliente = { navController.navigate("cliente") },
                onGoProducto = { navController.navigate("producto") }
            )
        }
        composable("cliente") {
            ClienteScreen(
                viewModelCliente,
                onGoInsertar = { navController.navigate("insertaCliente") },
                onItemClick = { cedula ->
                    navController.navigate("detalleCliente/$cedula")
                }
            )
        }
        composable("insertaCliente") {
            InsertaClienteScreen(
                onGoCerrar = { navController.popBackStack() })
        }
        ///OJO
        composable(
            "detalleCliente/{cedula}",
            listOf(navArgument("cedula") { type = NavType.StringType })
        ) {
            DetalleClienteScreen(
                onGoCerrar = { navController.popBackStack() },
                onGoModificar = { cedula ->
                    navController.navigate("modificaCliente/$cedula")
                }
            )
        }

        composable(
            "modificaCliente/{cedula}",
            listOf(navArgument("cedula") { type = NavType.StringType })
        ) {
            ModificaClienteScreen(
                //onGoCerrar = {navController.popBackStack()}
                onGoCerrar = {
                    navController.navigate("cliente") {
                        popUpTo("cliente") { inclusive = true }
                    }
                }
            )
        }

        composable("producto") {
            ProductoScreen(viewModelProducto)
        }

        composable("insertaProducto") {
            InsertaProductoScreen(
                onGoCerrar = { navController.popBackStack() }
            )
        }

        // Add more destinations similarly.
    }
}