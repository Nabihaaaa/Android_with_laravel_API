package com.nabiha.db_with_mysql_laravel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.nabiha.db_with_mysql_laravel.navigation.MainNavGraph
import com.nabiha.db_with_mysql_laravel.ui.theme.Db_with_mysql_laravelTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Db_with_mysql_laravelTheme {

                val navController = rememberNavController()

                MainNavGraph(modifier = Modifier.fillMaxSize(), navController = navController)
            }
        }
    }
}

