package com.nabiha.db_with_mysql_laravel.data.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.internal.Util
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var csrfToken: String = ""

    private val httpClient = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .connectTimeout(30, TimeUnit.SECONDS)
        .protocols(Util.immutableList(Protocol.HTTP_1_1))
        .build()

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://projectnabiha.my.id/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }

    suspend fun refreshCSRFToken() {
        // Fetch the CSRF token from the server and store it in the variable
        val response = instance.getCSRFToken()
        if (response.isSuccessful) {
            csrfToken = response.body()?.csrf_token ?: ""
            Log.d("CSRF_TOKEN", csrfToken) // Add this line to print the CSRF token to the console
        } else {
            // Handle error if necessary
            csrfToken = ""
        }
    }

}