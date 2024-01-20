package com.ishom.app.presentation.ui.template.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ishom.app.presentation.ui.template.style.Fonts
import com.ishom.app.presentation.util.mirror
import com.ishom.core.R

enum class MainBarType {
    MAIN,
    DETAIL,
    BackOnly,
    SEARCH
}

@Composable
fun MainBar(
    title: String = stringResource(id = R.string.home),
    type: MainBarType = MainBarType.MAIN,
    isWatchList: Boolean? = false,
    buttonModifier: Modifier = Modifier,
    searchKey: String = "",
    onBackPressed: (() -> Unit)? = null,
    onSearchPressed: (() -> Unit)? = null,
    onLanguagePressed: (() -> Unit)? = null,
    onSelectedFavorite: (() -> Unit)? = null,
    onSearchChanged: ((key: String) -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MainBarLeft(
            type = type,
            buttonModifier = buttonModifier,
            onBackPressed = onBackPressed
        )
        MainContent(
            type = type,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
            title = title,
            searchKey = searchKey,
            onSearchChanged = onSearchChanged,
        )
        MainBarRight(
            type = type,
            isWatchList = isWatchList,
            onLanguagePressed = onLanguagePressed,
            onSelectedFavorite = onSelectedFavorite,
            onSearchPressed = onSearchPressed,
        )
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
        MainBarType.MAIN, MainBarType.DETAIL, MainBarType.BackOnly -> {
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
                                text = stringResource(id = R.string.search_movie),
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
                                    contentDescription = stringResource(id = R.string.close),
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
fun MainBarLeft(
    type: MainBarType,
    buttonModifier: Modifier = Modifier,
    onBackPressed: (() -> Unit)? = null,
) {
    when (type) {
        MainBarType.DETAIL, MainBarType.SEARCH, MainBarType.BackOnly -> {
            IconButton(
                modifier = buttonModifier.mirror(),
                onClick = { onBackPressed?.invoke() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                    tint = Color.Black
                )
            }
        }
        MainBarType.MAIN -> {
            Icon(
                modifier = buttonModifier,
                imageVector = Icons.Default.Movie,
                contentDescription = stringResource(id = R.string.home),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun MainBarRight(
    type: MainBarType,
    isWatchList: Boolean?,
    onSelectedFavorite: (() -> Unit)? = null,
    onLanguagePressed: (() -> Unit)? = null,
    onSearchPressed: (() -> Unit)? = null,
) {

    when (type) {
        MainBarType.MAIN -> {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { onLanguagePressed?.invoke() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = stringResource(id = R.string.language),
                        tint = Color.Black
                    )
                }
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { onSearchPressed?.invoke() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search),
                        tint = Color.Black
                    )
                }
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
                        contentDescription = stringResource(id = R.string.watchlist),
                        tint = if (isSelected) Color.Red else Color.Black
                    )
                }
            }
        }
        else -> {}
    }
}