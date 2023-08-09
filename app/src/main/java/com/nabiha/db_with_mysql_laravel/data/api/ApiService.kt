package com.nabiha.db_with_mysql_laravel.data.api

import com.nabiha.db_with_mysql_laravel.data.model.ListModel
import com.nabiha.db_with_mysql_laravel.data.model.TokenModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/api/getData")
    suspend fun getPosts(): Response<ArrayList<ListModel>>

    @GET("/api/get-csrf-token")
    suspend fun getCSRFToken(): Response<TokenModel>

    @POST("/api/addData")
    suspend fun addData(
        @Body data: ListModel
    ): Response<Void>

    @PATCH("/api/update/{id}")
    suspend fun updateData(
        @Path("id") id: Int,
        @Body data: ListModel
    ): Response<ListModel>

    @DELETE("/api/delete/{id}")
    suspend fun deleteData(
        @Path("id") id: Int,
    ): Response<ListModel>

}
