package com.nabiha.db_with_mysql_laravel.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabiha.db_with_mysql_laravel.data.api.RetrofitClient
import com.nabiha.db_with_mysql_laravel.data.model.ListModel
import com.nabiha.db_with_mysql_laravel.data.model.TokenModel
import kotlinx.coroutines.launch
import kotlin.math.log

class Post_get_data_model : ViewModel() {

    val data = mutableStateListOf<ListModel>()

    init {
        viewModelScope.launch {
            getData()
        }
    }

    suspend fun getData() {
        try {
            val response =
                RetrofitClient.instance.getPosts()

            if (response.isSuccessful) {
                val listModelList = response.body()
                listModelList?.let { data.addAll(it) }
                Log.d("ISIRESPONSE", "onResponse: ${response.code()}")
            } else {
                Log.d(
                    "ISIRESPONSE",
                    "onResponse: ${response.code()} - ${response.errorBody()?.string()}"
                )
            }
        } catch (e: Exception) {
            Log.d("ERR", "getData: $e")
        }
    }

    suspend fun addData(name: String, context: Context) {
        try {
            val data = HashMap<String, String>()
            data["name"] = name

            val response = RetrofitClient.instance.addData(data)

            if (response.isSuccessful) {
                Toast.makeText(context, "Data berhasil diupload", Toast.LENGTH_LONG).show()
            } else {
                Log.d("GAGAL", "addData: $response")
            }
        } catch (e: Exception) {
            Log.d("ERR", "addData: $e")
        }
    }

}