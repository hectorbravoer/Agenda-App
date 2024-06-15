package com.hectorb.agenda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hectorb.agenda.ui.theme.AgendaTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.flow.collectAsState
import androidx.compose.ui.platform.LocalContext

class ViewAgendasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgendaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ViewAgendas()
                }
            }
        }
    }
}

@Composable
fun ViewAgendas() {
    val context = LocalContext.current
    val agendaDao = remember { AppDatabase.getDatabase(context).agendaDao() }
    val agendas by agendaDao.getAll().collectAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        agendas.forEach { agenda ->
            Text("Fecha: ${agenda.fecha}")
            Text("Asunto: ${agenda.asunto}")
            Text("Actividad: ${agenda.actividad}")
            Text("------")
        }
    }
}
