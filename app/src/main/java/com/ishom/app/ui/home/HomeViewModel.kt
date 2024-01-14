package com.ishom.app.ui.home

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private val _selectedIndex = mutableIntStateOf(0)
    val selectedIndex get() =  _selectedIndex.intValue

    fun setSelected(index: Int) {
        viewModelScope.launch {
            _selectedIndex.intValue = index
        }
    }
}