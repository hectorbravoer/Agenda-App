package com.hectorb.agenda

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.hectorb.agenda.ui.theme.AgendaTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.content.Context

class AddAgendaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgendaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddAgendaForm()
                }
            }
        }
    }
}

@Composable
fun AddAgendaForm() {
    val context = LocalContext.current
    var fecha by remember { mutableStateOf(TextFieldValue()) }
    var asunto by remember { mutableStateOf(TextFieldValue()) }
    var actividad by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Fecha:")
        TextField(value = fecha, onValueChange = { fecha = it })
        Text(text = "Asunto:")
        TextField(value = asunto, onValueChange = { asunto = it })
        Text(text = "Actividad:")
        TextField(value = actividad, onValueChange = { actividad = it })

        Button(
            onClick = {
                val newAgenda = ClassModel(0, fecha.text, asunto.text, actividad.text)
                addAgenda(newAgenda, context)
            }
        ) {
            Text("Agregar")
        }
    }
}

fun addAgenda(agenda: ClassModel, context: Context) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ApiService::class.java)
    CoroutineScope(Dispatchers.IO).launch {
        val response = service.addAgenda(agenda).execute()
        if (response.isSuccessful) {
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(context, "Agenda agregada exitosamente", Toast.LENGTH_SHORT).show()
            }
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(context, "Error al agregar agenda", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
