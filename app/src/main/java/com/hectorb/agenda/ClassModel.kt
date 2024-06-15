package com.hectorb.agenda

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agenda")
data class ClassModel(
    @PrimaryKey(autoGenerate = true) val id_agenda: Int,
    val fecha: String,
    val asunto: String,
    val actividad: String
)