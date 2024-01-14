package com.ishom.app.template.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ishom.app.constant.Route
import com.ishom.app.template.style.Fonts

enum class MainBarType {
    MAIN,
    DETAIL,
    SEARCH
}

@Composable
fun MainBar(
    controller: NavHostController,
    title: String = "Home",
    type: MainBarType = MainBarType.MAIN,
    isWatchList: Boolean? = false,
    onSelectedFavorite: (() -> Unit)? = null,
    searchKey: String = "",
    onSearchChanged: ((key: String) -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MainBarImage(type = type, controller = controller)
        MainContent(
            type = type,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
            title = title,
            searchKey = searchKey,
            onSearchChanged = onSearchChanged,
        )
        MainBarRight(type = type, isWatchList = isWatchList, controller = controller,
            onSelectedFavorite = onSelectedFavorite)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    type: MainBarType,
    modifier: Modifier,
    title: String,
    searchKey: String,
    onSearchChanged: ((key: String) -> Unit)? = null
) {
    
    when (type) {
        MainBarType.MAIN, MainBarType.DETAIL -> {
            Text(text = title,
                modifier = modifier,
                fontSize = 16.sp,
                color = Color.Black,
                style = Fonts.Typography.h1
            )
        }
        else -> {
            BasicTextField(
                value = searchKey,
                textStyle = Fonts.Typography.body1,
                onValueChange = {
                    onSearchChanged?.invoke(it)
                },
                modifier = modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(19.dp)
                    )
                    .height(38.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
            ) { innerTextField ->
                TextFieldDefaults.TextFieldDecorationBox(
                    value = "",
                    innerTextField = innerTextField,
                    placeholder = {
                        if (searchKey.isEmpty()) {
                            Text(
                                text = "Search..",
                                style = Fonts.Typography.body1
                            )
                        }
                    },
                    singleLine = true,
                    enabled = false,
                    interactionSource = remember { MutableInteractionSource() },
                    visualTransformation = VisualTransformation.None,
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    trailingIcon = {
                        if (searchKey.isNotEmpty()) {
                            IconButton(
                                modifier = Modifier.size(18.dp),
                                onClick = {
                                    onSearchChanged?.invoke("")
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun MainBarImage(
    type: MainBarType,
    controller: NavHostController,
) {
    when (type) {
        MainBarType.DETAIL -> {
            IconButton(
                modifier = Modifier
                    .size(36.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .padding(8.dp),
                onClick = { controller.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }
        MainBarType.SEARCH -> {
            IconButton(
                modifier = Modifier.size(18.dp),
                onClick = { controller.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }
        MainBarType.MAIN -> {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Movie,
                contentDescription = "Home",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun MainBarRight(
    type: MainBarType,
    isWatchList: Boolean?,
    controller: NavHostController,
    onSelectedFavorite: (() -> Unit)? = null,
) {

    when (type) {
        MainBarType.MAIN -> {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = { controller.navigate(Route.PAGE_MOVIE_SEARCH) }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Black
                )
            }
        }
        MainBarType.DETAIL -> {
            isWatchList?.let { isSelected ->
                IconButton(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(18.dp)
                        )
                        .padding(8.dp),
                    onClick = {
                        onSelectedFavorite?.invoke()
                    }
                ) {
                    Icon(
                        imageVector = if (isSelected)
                            Icons.Filled.Favorite
                        else
                            Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isSelected) Color.Red else Color.Black
                    )
                }
            }
        }
        else -> {}
    }
}