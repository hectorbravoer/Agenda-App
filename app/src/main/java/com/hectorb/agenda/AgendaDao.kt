package com.hectorb.agenda

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AgendaDao {
    @Insert
    suspend fun insert(agenda: ClassModel)

    @Query("SELECT * FROM agenda")
    suspend fun getAll(): List<ClassModel>
}
