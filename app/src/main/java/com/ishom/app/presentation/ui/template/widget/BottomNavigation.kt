package com.ishom.app.presentation.ui.template.widget

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ishom.app.presentation.navigation.BottomNavDestination
import com.ishom.app.presentation.navigation.allBottomNavDestinations

@Composable
fun BottomNavigation(
    navController: NavHostController = rememberNavController()
) {
    val currentDestination = navController.currentBackStackEntryAsState().value
    val currentDestinationRoute = currentDestination?.destination?.route
    val isShouldShowBottomNav =
        BottomNavDestination.allBottomNavDestinations.any { it.route == currentDestinationRoute }

    if (isShouldShowBottomNav) {
        BottomAppBar(
            backgroundColor = Color.White,
        ) {
            BottomNavDestination.allBottomNavDestinations.forEach { destination ->
                val isSelected = currentDestinationRoute?.equals(destination.route) == true
                val color = if (isSelected) MaterialTheme.colors.primary else
                    MaterialTheme.colors.onBackground
                BottomNavigationItem(
                    selected = isSelected,
                    onClick = {
                          navController.navigate(destination.route) {
                              popUpTo(navController.graph.findStartDestination().id) {
                                  saveState = true
                              }
                              launchSingleTop = true
                              restoreState = true
                          }
                    }, icon = {
                        Icon(
                            painter = painterResource(id = destination.selectedIcon),
                            contentDescription = stringResource(id = destination.label),
                            tint = color
                        )
                    },
                    enabled = true,
                    label = {
                        Text(
                            text = stringResource(id = destination.label),
                            color = color
                        )
                    },
                    alwaysShowLabel = false
                )
            }
        }
    }
}