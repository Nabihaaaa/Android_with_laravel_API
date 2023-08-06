package com.nabiha.db_with_mysql_laravel.data.api

import com.nabiha.db_with_mysql_laravel.data.model.ListModel
import com.nabiha.db_with_mysql_laravel.data.model.TokenModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("getData")
    suspend fun getPosts(): Response<ArrayList<ListModel>>

    @GET("get-csrf-token")
    suspend fun getCSRFToken(): Response<TokenModel>

    @POST("addData")
    suspend fun addData(
        @Body data: HashMap<String, String>
    ): Response<Void>

}
