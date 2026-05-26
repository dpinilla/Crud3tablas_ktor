package com.dap.crud3tablas_ktor.view.cliente.modifica

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dap.crud3tablas_ktor.model.ModelCliente
import crud3tablas_ktor.app.shared.generated.resources.Res
import crud3tablas_ktor.app.shared.generated.resources.cerrar
import crud3tablas_ktor.app.shared.generated.resources.edad
import crud3tablas_ktor.app.shared.generated.resources.guardar
import crud3tablas_ktor.app.shared.generated.resources.ic_check
import crud3tablas_ktor.app.shared.generated.resources.ic_close
import crud3tablas_ktor.app.shared.generated.resources.modificar
import crud3tablas_ktor.app.shared.generated.resources.nombre
import crud3tablas_ktor.app.shared.generated.resources.toastErrorGuardar
import crud3tablas_ktor.app.shared.generated.resources.toastErrorModificar
import crud3tablas_ktor.app.shared.generated.resources.toastModificar
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModificaClienteScreen(
    onGoCerrar: () -> Unit
){
    var cedula by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    val modificaClienteViewModel: ModificaClienteViewModel = viewModel()
    val toastModificar = stringResource(Res.string.toastModificar)
    val toastErrorModificar = stringResource(Res.string.toastErrorModificar)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    /*LaunchedEffect(modificaViewModel.data) {
        modificaViewModel.data?.let { persona ->
            id = persona.id?:0
            nombre = persona.nombre.toString()
            edad = persona.edad.toString()
        }
    }*/
    //LaunchedEffect(modificaViewModel.data)
    // Cuando la pantalla se monta Y cada vez que el valor de data cambie.
    LaunchedEffect(modificaClienteViewModel.data) {
        cedula = modificaClienteViewModel.data?.cli_cedula.toString()
        nombre = modificaClienteViewModel.data?.cli_nombre.toString()
        edad = modificaClienteViewModel.data?.cli_edad.toString()
    }
    //imePadding : Evita que el teclado bloquee el mensaje del snackbarHost
    Scaffold(

        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(Res.string.modificar),
                        Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                Modifier.background(Color.Cyan),
                navigationIcon = {
                    IconButton(onClick = {onGoCerrar()}) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_close),
                            contentDescription = stringResource(Res.string.cerrar)
                        )
                    }
                },
                actions = {
                    IconButton(onClick =
                        {
                            keyboardController?.hide()  // Cierra el teclado
                            modificaClienteViewModel.modifica(
                                ModelCliente(cedula, nombre, edad.toIntOrNull()),
                                onSuccess = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = toastModificar,
                                            duration = SnackbarDuration.Short
                                        )
                                        onGoCerrar()
                                    }
                                    //Toast.makeText(context, toastModificar, Toast.LENGTH_SHORT).show()


                                },
                                onError = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = toastErrorModificar,
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                    //Toast.makeText(context, toastErrorModificar, Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_check),
                            contentDescription = stringResource(Res.string.guardar)
                        )
                    }
                }
            )
            /*com.dap.jpcrudsupabaseretrofit.view.modifica.Encabezado(
                onGoCerrar,
                onGoModificar = {
                    if (conSupabase) {
                        modificaViewModel.modificaSupabase(
                            context,
                            ModelPersona(id, nombre, edad.toIntOrNull()),
                            onSuccess = { onGoCerrar() }
                        )
                    } else {
                        modificaViewModel.modificaRetrofit(
                            context,
                            ModelPersona(id, nombre, edad.toIntOrNull()),
                            onSuccess = { onGoCerrar() }
                        )
                    }
                }
            )*/
        }
    ) { myPadding ->
        if (modificaClienteViewModel.data == null) {
            // Mostrar un indicador de carga mientras la corrutina trabaja
            CircularProgressIndicator()
        } else {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(myPadding)
            ) {
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    Modifier.fillMaxWidth(),
                    label = { Text(stringResource(Res.string.nombre)) }
                )
                Spacer(Modifier.height(15.dp))
                TextField(
                    value = edad,
                    onValueChange = { edad = it },
                    Modifier.fillMaxWidth(),
                    label = { Text(stringResource(Res.string.edad)) }
                )
            }
        }
    }
}

