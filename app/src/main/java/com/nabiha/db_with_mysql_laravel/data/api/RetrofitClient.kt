package com.nabiha.db_with_mysql_laravel.data.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var csrfToken: String = ""

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("X-CSRF-TOKEN", csrfToken)
                .method(original.method(), original.body())

            // Log request headers
            Log.d("REQUEST_HEADERS", requestBuilder.build().headers().toString())

            chain.proceed(requestBuilder.build())
        }
        .build()

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://androidlaraveltest.000webhostapp.com/")
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