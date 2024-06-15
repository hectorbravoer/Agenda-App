package com.hectorb.agenda

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AgendaController {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ApiService::class.java)

    fun addAgenda(agenda: ClassModel, context: Context) {
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
}