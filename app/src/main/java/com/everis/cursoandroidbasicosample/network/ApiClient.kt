package com.everis.cursoandroidbasicosample.network

import com.everis.cursoandroidbasicosample.entities.CarList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    companion object {
        const val LAMBORGHINI_REQUEST = "/get/LamborghiniModels"
    }

    @GET(LAMBORGHINI_REQUEST)
    fun getLamborghinis(): Call<CarList>

    @GET(LAMBORGHINI_REQUEST)
    suspend fun getLamborghinisSuspend(): Response<CarList>
}