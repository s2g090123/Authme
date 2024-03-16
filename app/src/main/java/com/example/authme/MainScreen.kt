package com.example.authme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authme.navigation.Route
import com.example.authme.screen.info.UserInfoScreen
import com.example.authme.screen.list.UserListScreen

@Composable
fun MainScreen(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Route.List.route,
    ) {
        composable(Route.List.route) {
            UserListScreen(
                onClick = {
                    navHostController.navigate(Route.Info.build(it.login))
                }
            )
        }
        composable(Route.Info.route) {
            UserInfoScreen(
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}