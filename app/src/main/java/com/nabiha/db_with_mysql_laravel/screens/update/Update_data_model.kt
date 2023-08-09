package com.nabiha.db_with_mysql_laravel.screens.update

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.nabiha.db_with_mysql_laravel.data.api.RetrofitClient
import com.nabiha.db_with_mysql_laravel.data.model.ListModel
import com.nabiha.db_with_mysql_laravel.navigation.Screens
import kotlinx.coroutines.launch

class Update_data_model(private val id: String) : ViewModel() {

    var data = mutableStateOf(ListModel())

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
                data.value = listModelList?.find { it.id == id.toInt() } ?: ListModel()
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

    suspend fun updateData(name: String, context: Context, navController: NavHostController) {
        try {

            val response = RetrofitClient.instance.updateData(id.toInt(),ListModel(id.toInt(),name))

            if (response.isSuccessful) {
                Toast.makeText(context, "Data berhasil diUpdate", Toast.LENGTH_LONG).show()
                navController.navigate(Screens.Post_get_data_screeens.route)
            } else {
                Log.d(
                    "ISIRESPONSE",
                    "updateData: ${response.code()} - ${response.errorBody()?.string()}"
                )
            }
        } catch (e: Exception) {
            Log.d("ERR", "updateData: $e")
        }
    }
}