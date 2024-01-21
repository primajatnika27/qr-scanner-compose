package com.prima.paymentqris.presentation.routes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.prima.paymentqris.presentation.dashboard_screen.Dashboard
import com.prima.paymentqris.presentation.history_transaction_screen.HistoryTransaction
import com.prima.paymentqris.presentation.scan_transaction_screen.ScanTransaction

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavigation() {
    val navController = rememberNavController()

    val navBackStateEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStateEntry?.destination?.route

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                BottomBar(navController = navController)
            }
        }
    ) {
        NavigationGraph(
            navController = navController
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStateEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStateEntry?.destination?.route

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.surface
    ) {
        BottomNavigationItem(
            selected = currentRoute == Screen.Dashboard.route,
            onClick = {
                      navController.navigate(Screen.Dashboard.route) {
                          popUpTo(navController.graph.findStartDestination().id) {
                              saveState = true
                          }

                          launchSingleTop = true

                          restoreState = true
                      }
            },
            label = {
                Text(text = "Balance")
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null
                )
            },
            selectedContentColor = Color(0xFF243D25),
            unselectedContentColor = Color.White,
            alwaysShowLabel = false,
        )

        BottomNavigationItem(
            selected = currentRoute == Screen.AddTransaction.route,
            onClick = {
                navController.navigate(Screen.AddTransaction.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }

                    launchSingleTop = true

                    restoreState = true
                }
            },
            label = {
                    Text(text = "Scan")
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            },
            selectedContentColor = Color(0xFF243D25),
            unselectedContentColor = Color.White,
            alwaysShowLabel = false,
        )

        BottomNavigationItem(
            selected = currentRoute == Screen.HistoryTransaction.route,
            onClick = {
                navController.navigate(Screen.HistoryTransaction.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }

                    launchSingleTop = true

                    restoreState = true
                }
            },
            label = {
                Text(text = "History")
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = null
                )
            },
            selectedContentColor = Color(0xFF243D25),
            unselectedContentColor = Color.White,
            alwaysShowLabel = false,
        )
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Dashboard.route) {
        composable(
            route = Screen.Dashboard.route,
        ) {
            Dashboard(navHostController = navController)
        }

        composable(
            route = Screen.AddTransaction.route,
        ) {
            ScanTransaction(navHostController = navController)
        }

        composable(
            route = Screen.HistoryTransaction.route,
        ) {
            HistoryTransaction(navHostController = navController)
        }
    }
}