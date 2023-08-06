package com.nabiha.db_with_mysql_laravel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nabiha.db_with_mysql_laravel.screens.Post_get_data_screens
import com.nabiha.db_with_mysql_laravel.ui.theme.Db_with_mysql_laravelTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Db_with_mysql_laravelTheme {
                // A surface container using the 'background' color from the theme
               Post_get_data_screens()
            }
        }
    }
}

