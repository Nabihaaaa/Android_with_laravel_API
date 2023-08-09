package com.nabiha.db_with_mysql_laravel.screens.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UpdateDataFactory (private val id: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = Update_data_model(id) as T
}