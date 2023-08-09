package com.nabiha.db_with_mysql_laravel.navigation

sealed class Screens(val route:String){
    object Post_get_data_screeens : Screens("Post_get_data_screeens")
    object update_data_screens : Screens("update_data_screens/{id}")
}
