package com.dap.crud3tablas_ktor.view.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import crud3tablas_ktor.app.shared.generated.resources.Res
import crud3tablas_ktor.app.shared.generated.resources.eventoCliente
import crud3tablas_ktor.app.shared.generated.resources.eventoProducto
import org.jetbrains.compose.resources.stringResource


@Composable
fun MenuScreen(
    onGoCliente: () -> Unit,
    onGoProducto: () -> Unit
){
    Scaffold() {myPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(myPadding),
                Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { onGoCliente() },
                Modifier
                    .fillMaxWidth()
            ) {
                Text(stringResource(Res.string.eventoCliente))
            }
            Spacer(Modifier.height(30.dp))
            Button(
                onClick = {onGoProducto()},
                Modifier
                    .fillMaxWidth()
            ) {
                Text(stringResource(Res.string.eventoProducto))
            }
        }
    }
}