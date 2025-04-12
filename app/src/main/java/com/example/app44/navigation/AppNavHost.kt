package com.example.app44.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app44.sharepref.TokenManager
import com.example.app44.view.screen.ForgotPasswordScreen
import com.example.app44.view.screen.LoginScreen
import com.example.app44.view.screen.MainScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
) {
    val tokenManager = TokenManager(LocalContext.current)
    val startDestination = if (tokenManager.getToken().isNullOrEmpty()) {
        Screen.Login.route
    } else {
        Screen.MainScreen.route
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(navController)
        }
        // In hóa đơn
        composable(Screen.MainScreen.route) {
            MainScreen(navController)
        }
    }
}
