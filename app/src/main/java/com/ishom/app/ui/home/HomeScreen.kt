package com.ishom.app.ui.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ishom.app.template.widget.MainBar
import com.ishom.app.template.widget.MainBottomNavigation
import com.ishom.app.ui.movie.list.MovieListScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    controller: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold (
        topBar = {
             MainBar(controller)
        },
        bottomBar = {
            MainBottomNavigation(viewModel.selectedIndex) { index ->
                viewModel.setSelected(index)
            }
        },
        content = {
            when (viewModel.selectedIndex) {
                0 -> MovieListScreen(controller)
                else -> {
                    // dynamic feature should be call in here
                    Text(text = "Testing")
                }
            }
        }
    )
}