package com.dap.crud3tablas_ktor.view.producto

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import crud3tablas_ktor.app.shared.generated.resources.Res
import crud3tablas_ktor.app.shared.generated.resources.nombre
import crud3tablas_ktor.app.shared.generated.resources.tituloProducto
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoScreen(
    viewModelProducto: ProductoViewModel
){
    val lista by viewModelProducto.listaProducto.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModelProducto.visualiza()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(Res.string.tituloProducto),
                        Modifier.fillMaxWidth(),
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) {myPadding ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(myPadding)
        ) {
            itemsIndexed(lista){index, producto ->
                Text(
                    "${stringResource(Res.string.nombre)} ${producto.pro_nombre}",
                    Modifier

                )
                Spacer(Modifier.height(30.dp))
            }
        }

    }
}