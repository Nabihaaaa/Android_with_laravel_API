package com.nabiha.db_with_mysql_laravel.screens.post_get

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabiha.db_with_mysql_laravel.data.api.RetrofitClient
import com.nabiha.db_with_mysql_laravel.data.model.ListModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PostGetDataModel : ViewModel() {


    val data = mutableStateListOf<ListModel>()
    var refreshing by mutableStateOf(false)

    init {
        viewModelScope.launch {
            getData()
        }
    }
    suspend fun refreshData() {
        try {
            refreshing = true
            delay(1_000L)
            data.clear()
            getData()
        } catch (e: Exception) {
            Log.d("ERR", "refreshData: $e")
        } finally {
            refreshing = false
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
            val response = RetrofitClient.instance.addData(ListModel(name = name))

            if (response.isSuccessful) {
                Toast.makeText(context, "Data berhasil diupload", Toast.LENGTH_LONG).show()
            } else {
                Log.d("GAGAL", "addData: $response")
            }
        } catch (e: Exception) {
            Log.d("ERR", "addData: $e")
        }
    }

    suspend fun deleteData(id: Int, context: Context) {
        try {
            val response = RetrofitClient.instance.deleteData(id)

            if (response.isSuccessful) {
                Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_LONG).show()
            } else {
                Log.d("GAGAL", "addData: $response")
            }
        } catch (e: Exception) {
            Log.d("ERR", "addData: $e")
        }
    }

}