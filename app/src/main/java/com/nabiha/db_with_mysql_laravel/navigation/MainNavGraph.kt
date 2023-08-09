package com.nabiha.db_with_mysql_laravel.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nabiha.db_with_mysql_laravel.screens.post_get.Post_get_data_screens
import com.nabiha.db_with_mysql_laravel.screens.update.Update_data_screens

@Composable
fun MainNavGraph(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.Post_get_data_screeens.route
    ) {

        composable(Screens.Post_get_data_screeens.route){
            Post_get_data_screens(navController)
        }

        composable(Screens.update_data_screens.route){
            Update_data_screens(navController, it.arguments?.getString("id")!!)
        }

    }

}