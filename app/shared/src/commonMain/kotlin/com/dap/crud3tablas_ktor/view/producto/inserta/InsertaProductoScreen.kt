package com.dap.crud3tablas_ktor.view.producto.inserta

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dap.crud3tablas_ktor.model.ModelProducto
import crud3tablas_ktor.app.shared.generated.resources.Res
import crud3tablas_ktor.app.shared.generated.resources.guardar
import crud3tablas_ktor.app.shared.generated.resources.nombre
import crud3tablas_ktor.app.shared.generated.resources.tituloInsertar
import crud3tablas_ktor.app.shared.generated.resources.toastEliminar
import crud3tablas_ktor.app.shared.generated.resources.toastErrorEliminar
import crud3tablas_ktor.app.shared.generated.resources.toastErrorGuardar
import crud3tablas_ktor.app.shared.generated.resources.toastGuardar
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertaProductoScreen(
    onGoCerrar:() -> Unit
){
    var nombre by remember { mutableStateOf("") }
    val insertaProductoViewModel: InsertaProductoViewModel = viewModel()
    val toastGuardar = stringResource(Res.string.toastGuardar)
    val toastErrorGuardar = stringResource(Res.string.toastErrorGuardar)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    stringResource(Res.string.tituloInsertar),
                    Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                ) }
            )
        }
    ) {myPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(myPadding)
            ) {
                TextField(
                    value = nombre,
                    onValueChange = {nombre = it},
                    Modifier.fillMaxWidth(),
                    label = {Text(stringResource(Res.string.nombre))}
                )
                Spacer(Modifier.height(30.dp))
                Button(
                    onClick = {
                        keyboardController?.hide()  // Cierra el teclado
                        insertaProductoViewModel.guardar(
                            ModelProducto(null,nombre),
                        onSuccess={
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = toastGuardar,
                                    duration = SnackbarDuration.Short
                                )
                                onGoCerrar()
                            }
                            //Toast.makeText(context, "Registro guardado", Toast.LENGTH_SHORT).show()

                        },
                        onError = {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = toastErrorGuardar,
                                    duration = SnackbarDuration.Short
                                )
                            }
                            //Toast.makeText(context, "Error al guardar", Toast.LENGTH_SHORT).show()
                        }
                        )
                    },
                    Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(Res.string.guardar))
                }
            }

    }
}