package com.dap.crud3tablas_ktor.view.cliente.detalle

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import crud3tablas_ktor.app.shared.generated.resources.Res
import crud3tablas_ktor.app.shared.generated.resources.calcelaEliminarCliente
import crud3tablas_ktor.app.shared.generated.resources.cedula
import crud3tablas_ktor.app.shared.generated.resources.cerrar
import crud3tablas_ktor.app.shared.generated.resources.confirmaEliminarCliente
import crud3tablas_ktor.app.shared.generated.resources.descripcionAlertCliente
import crud3tablas_ktor.app.shared.generated.resources.edad
import crud3tablas_ktor.app.shared.generated.resources.eliminar
import crud3tablas_ktor.app.shared.generated.resources.guardar
import crud3tablas_ktor.app.shared.generated.resources.ic_close
import crud3tablas_ktor.app.shared.generated.resources.ic_delete
import crud3tablas_ktor.app.shared.generated.resources.ic_pen
import crud3tablas_ktor.app.shared.generated.resources.nombre
import crud3tablas_ktor.app.shared.generated.resources.tituloAlertCliente
import crud3tablas_ktor.app.shared.generated.resources.tituloDetalle
import crud3tablas_ktor.app.shared.generated.resources.toastEliminar
import crud3tablas_ktor.app.shared.generated.resources.toastErrorEliminar
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleClienteScreen(
    onGoCerrar: () -> Unit,
    onGoModificar: (String) -> Unit
) {
    val openAlertDialog = remember { mutableStateOf(false) }

    var cedula by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    val detalleClienteViewModel: DetalleClienteViewModel = viewModel()
    val toastEliminar = stringResource(Res.string.toastEliminar)
    val toastErrorEliminar = stringResource(Res.string.toastErrorEliminar)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    LaunchedEffect(detalleClienteViewModel.data) {
        cedula = detalleClienteViewModel.data?.cli_cedula.toString()
        nombre = detalleClienteViewModel.data?.cli_nombre.toString()
        edad = detalleClienteViewModel.data?.cli_edad.toString()
    }
    //
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(Res.string.tituloDetalle),
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                },
                Modifier.background(Color.Cyan),
                navigationIcon = {
                    IconButton(onClick = { onGoCerrar() }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_close),
                            contentDescription = stringResource(Res.string.cerrar)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onGoModificar(cedula) }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_pen),
                            contentDescription = stringResource(Res.string.guardar)
                        )
                    }
                    IconButton(onClick = {
                        openAlertDialog.value = true

                    }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_delete),
                            contentDescription = stringResource(Res.string.eliminar)
                        )
                    }
                }
            )
        }
    ) { MyPadding ->
        if (openAlertDialog.value) {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    openAlertDialog.value = false
                    detalleClienteViewModel.eliminaCliente(
                        cedula,
                        onSuccess = {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = toastEliminar,
                                    duration = SnackbarDuration.Short
                                )
                                onGoCerrar()
                            }

                        },
                        onError = {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = toastErrorEliminar,
                                    duration = SnackbarDuration.Short
                                )
                            }

                        }
                    )
                    //println("Confirmation registered") // Add logic here to handle confirmation.
                },
                dialogTitle = stringResource(Res.string.tituloAlertCliente),
                dialogText = stringResource(Res.string.descripcionAlertCliente),
                icon = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_delete), // Tu archivo de la papelera en la carpeta drawable
                        contentDescription = "Eliminar"
                    )
                }

            )
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(MyPadding)
        ) {
            Text(
                "${stringResource(Res.string.cedula)} $cedula",
                Modifier
                    .fillMaxWidth()
            )
            Text(
                "${stringResource(Res.string.nombre)} $nombre",
                Modifier
                    .fillMaxWidth()
            )
            Text(
                "${stringResource(Res.string.edad)} $edad",
                Modifier
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(30.dp))
        }
    }
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: @Composable () -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(
                painter = painterResource(Res.drawable.ic_delete), // Tu archivo de la papelera en la carpeta drawable
                contentDescription = "Eliminar"
            )
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(Res.string.confirmaEliminarCliente))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(Res.string.calcelaEliminarCliente))
            }
        }
    )
}
