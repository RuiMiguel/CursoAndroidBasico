package com.everis.cursoandroidbasicosample.network

import com.everis.cursoandroidbasicosample.entities.CarList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkDatasource {
    companion object {
        const val BASE_URL = "https://cma-sas-everis.herokuapp.com"
    }

    private val loggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    private val okHttpClient =
        OkHttpClient.Builder().apply { addInterceptor(loggingInterceptor) }.build()

    private val service = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiClient::class.java)

    suspend fun getLamborghinisSync(): CarList? {
        return service.getLamborghinisSuspend().body()
    }

    fun getLamborghinisAsync(onSuccess: (CarList) -> Unit, onFailure: () -> Unit) {
        val call = service.getLamborghinis()

        call.enqueue(object : retrofit2.Callback<CarList> {
            override fun onFailure(call: Call<CarList>, t: Throwable) {
                onFailure()
            }

            override fun onResponse(call: Call<CarList>, response: Response<CarList>) {
                response.body().takeIf { response.isSuccessful }.apply {
                    response.body()?.let { onSuccess(it) }
                }
            }
        })
    }
}