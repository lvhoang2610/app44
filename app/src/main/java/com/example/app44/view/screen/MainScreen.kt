package com.example.app44.view.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
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
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app44.sharepref.TokenManager
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navHostController: NavHostController) {
    val items = listOf(
        Screen.PrintInvoice,
        Screen.PrintDelivery,
        Screen.PrintBill,
        Screen.PrintContract,
        Screen.PrintOthers,
        Screen.History
    )
    val navController: NavHostController = rememberNavController()
    val currentDestination by navController.currentBackStackEntryAsState()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val tokenManager = TokenManager(LocalContext.current)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Menu",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                NavigationDrawerItem(
                    label = { Text("Đăng xuất") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                            navHostController.navigate(Screen.Login.route) {
                                tokenManager.clearToken()
                                popUpTo(0)
                            }
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                val currentScreen =
                    items.find { it.route == currentDestination?.destination?.route }

                if (currentScreen != null) {
                    TopAppBar(
                        title = {
                            Text(currentScreen?.title ?: "")
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(Icons.Filled.Menu, contentDescription = "Menu")
                            }
                        }
                    )
                }
            },
            bottomBar = {
                NavigationBar {
                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = screen.icon, contentDescription = screen.route
                                )
                            },
                            label = {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = screen.title,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            },
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
                            }
                        )
                    }
                }
            }
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = Screen.PrintInvoice.route,
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 12.dp)
            ) {
                composable(Screen.PrintInvoice.route) {
                    PrintInvoiceScreen(navController)
                }
                composable(Screen.PrintBill.route) {
                    PrintBillScreen(navController)
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
}
