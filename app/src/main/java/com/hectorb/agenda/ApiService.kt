package com.hectorb.agenda

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("agendas")
    fun getAgendas(): Call<List<ClassModel>>

    @POST("agendas")
    fun addAgenda(@Body agenda: ClassModel): Call<ClassModel>
}
