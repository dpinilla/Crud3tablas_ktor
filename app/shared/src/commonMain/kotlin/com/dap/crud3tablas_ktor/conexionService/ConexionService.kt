package com.dap.crud3tablas_ktor.conexionService

import com.dap.crud3tablas_ktor.model.ModelCliente
import com.dap.crud3tablas_ktor.model.ModelProducto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json



class ConexionService {

    val client = HttpClient{
        install(ContentNegotiation) {
            // Evita errores si el PHP manda campos extra
            json(Json { ignoreUnknownKeys = true })
        }
    }
    companion object {
        //val url: String = "http://54.156.114.70:15001/dpinilla/"
        val url: String = "http://192.168.1.13/php3tablas/"

    }

    suspend fun consultaCliente(): List<ModelCliente> {
        return client.get("${url}consultaCliente.php").body()
    }

    suspend fun consultaClienteId(cedula: String): ModelCliente {
        return client.get("${url}consultaClienteId.php") {
            parameter("cedula", cedula) // Añade automáticamente ?cedula=valor a la URL
        }.body()
    }

    suspend fun consultaProducto(): List<ModelProducto> {
        return client.get("${url}consultaProducto.php").body()
    }

    suspend fun insertaCliente(data: ModelCliente): ModelCliente {
        return client.post("${url}insertaCliente.php") {
            contentType(ContentType.Application.Json)
            setBody(data) // Envía el objeto serializado como JSON en el cuerpo
        }.body()
    }


    suspend fun insertaProducto(data: ModelProducto): ModelProducto {
        return client.post("${url}insertaProducto.php") {
            contentType(ContentType.Application.Json)
            setBody(data)
        }.body()
    }

    suspend fun modificarCliente(data: ModelCliente): ModelCliente {
        return client.put("${url}modificarCliente.php") {
            contentType(ContentType.Application.Json)
            setBody(data)
        }.body()
    }

    suspend fun eliminarCliente(id: String): Any {
        return client.delete("${url}eliminarCliente.php") {
            parameter("id", id)
        }.body() // Retorna la respuesta del servidor (ej. un texto de "Eliminado correctamente")
    }
}