package com.praveen.omdb.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.praveen.omdb.utils.commons.toSingleEvent

abstract class BaseViewModel() : ViewModel() {

    private val mUiState = MutableLiveData<Any>()
    open val uiState: LiveData<Any> = mUiState.toSingleEvent()

    fun <T : Any> updateViewState(result: T) {
        mUiState.value = result
    }

}