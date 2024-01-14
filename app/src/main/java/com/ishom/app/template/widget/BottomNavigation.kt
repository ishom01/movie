package com.ishom.app.template.widget

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun MainBottomNavigation(
    selectedIndex: Int,
    onSelectedItem: (index: Int) -> Unit
) {
    val items = listOf(
        BottomNavigationItem.Movie,
        BottomNavigationItem.WatchList,
    )
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = {
                   Icon(painterResource(id = item.icon), contentDescription = item.title)
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.LightGray,
                label = {
                    Text(text = item.title)
                },
                selected = selectedIndex == index,
                onClick = {
                    onSelectedItem.invoke(index)
                }
            )
        }
    }

}