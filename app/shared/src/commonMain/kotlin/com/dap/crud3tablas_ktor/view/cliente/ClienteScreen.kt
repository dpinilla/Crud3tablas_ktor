package com.dap.crud3tablas_ktor.view.cliente

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import crud3tablas_ktor.app.shared.generated.resources.Res
import crud3tablas_ktor.app.shared.generated.resources.cedula
import crud3tablas_ktor.app.shared.generated.resources.edad
import crud3tablas_ktor.app.shared.generated.resources.ic_add
import crud3tablas_ktor.app.shared.generated.resources.nombre
import crud3tablas_ktor.app.shared.generated.resources.tituloCliente
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClienteScreen(
    viewModelCliente: ClienteViewModel,
    onGoInsertar: () -> Unit,
    onItemClick: (String) -> Unit
) {
    val lista by viewModelCliente.listaCliente.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModelCliente.visualiza()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(Res.string.tituloCliente),
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onGoInsertar() },
            ) {
                Icon(
                    painterResource(Res.drawable.ic_add),
                    contentDescription = "Agregar"
                )
            }
        }


    ) { MyPadding ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(MyPadding)
        ) {
            itemsIndexed(lista) { index, cliente ->
                Column(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(cliente.cli_cedula.toString())
                        }
                        .padding(16.dp)
                ) {
                    Text(
                        "${stringResource(Res.string.cedula)} ${cliente.cli_cedula}",
                        Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        "${stringResource(Res.string.nombre)} ${cliente.cli_nombre}",
                        Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        "${stringResource(Res.string.edad)} ${cliente.cli_edad}",
                        Modifier
                            .fillMaxWidth()
                    )
                    Spacer(Modifier.height(30.dp))
                }
            }
        }
    }
}