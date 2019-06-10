package me.moallemi.newscards.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    fun launchIO(block: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            block.invoke()
        }
    }
}