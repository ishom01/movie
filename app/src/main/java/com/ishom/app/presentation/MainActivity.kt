package com.ishom.app.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ishom.app.presentation.navigation.BottomNavDestination
import com.ishom.app.presentation.navigation.MainNavigation
import com.ishom.app.presentation.navigation.allBottomNavDestinations
import com.ishom.app.presentation.navigation.navigateSearch
import com.ishom.app.presentation.ui.template.widget.BottomNavigation
import com.ishom.app.presentation.ui.template.widget.MainBar
import com.ishom.app.presentation.util.getFavoriteModule
import com.ishom.core.R
import com.ishom.movie.data.source.local.sharedPref.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(
            newBase?.run {
                val preferences = SharedPreferences(this)
                val locale = Locale(preferences.languageCode)
                Locale.setDefault(locale)
                createConfigurationContext(resources.configuration.apply {
                    setLocale(locale)
                    setLayoutDirection(locale)
                })
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun App() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val isHasFavoriteModule = getFavoriteModule() != null
    Scaffold(
        topBar = {
            val currentDestination = navController.currentBackStackEntryAsState().value
            val currentDestinationRoute = currentDestination?.destination?.route
            val isShouldShowTopBar = BottomNavDestination.allBottomNavDestinations.any {
                it.route == currentDestinationRoute
            }
            if (isShouldShowTopBar) {
                MainBar(
                    title = stringResource(id = R.string.home),
                    onLanguagePressed = {
                        try {
                            context.startActivity(
                                Intent(
                                    context,
                                    Class.forName("com.ishom.language.presentation.LanguageActivity")
                                )
                            )
                        }
                        catch (e: Exception) {
                            Toast.makeText(
                                context, "Language module not found", Toast.LENGTH_SHORT).show()
                        }
                    },
                    onSearchPressed = {
                        navController.navigateSearch()
                    }
                )
            }
        },
        bottomBar =  {
            BottomNavigation(navController, isHasFavoriteModule)
        }
    ) {
        MainNavigation(controller = navController)
    }
}