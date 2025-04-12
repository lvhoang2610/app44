package com.example.app44.view.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.app44.navigation.Screen
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    val items = listOf(
        Screen.PrintInvoice,
        Screen.PrintExportForm,
        Screen.PrintDelivery,
        Screen.PrintContract,
        Screen.PrintOthers,
        Screen.History
    )
    val currentDestination by navController.currentBackStackEntryAsState()

    Scaffold(bottomBar = {
        NavigationBar {
            items.forEach { screen ->
                NavigationBarItem(icon = {
                    Icon(
                        imageVector = screen.icon, contentDescription = screen.route
                    )
                },
                    label = { Text(screen.title) },
                    selected = currentDestination?.destination?.route == screen.route,
                    onClick = {
                        if (currentDestination?.destination?.route != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(Screen.PrintInvoice.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    })
            }
        }
    }) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.PrintInvoice.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.PrintInvoice.route) {
                PrintInvoiceScreen(navController)
            }
            composable(Screen.PrintExportForm.route) {
                PrintExportFormScreen(navController)
            }
            composable(Screen.PrintDelivery.route) {
                PrintDeliveryScreen(navController)
            }
            composable(Screen.PrintContract.route) {
                PrintContractScreen(navController)
            }
            composable(Screen.PrintOthers.route) {
                PrintOthersScreen(navController)
            }
            composable(Screen.History.route) {
                HistoryScreen(navController)
            }
            composable(
                route = Screen.PreviewPDF.route,
                arguments = listOf(navArgument("pdfUrl") {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val pdfUrl = backStackEntry.arguments?.getString("pdfUrl") ?: ""
                PreviewPDFScreen(navController, pdfUrl)
            }
        }
    }
}