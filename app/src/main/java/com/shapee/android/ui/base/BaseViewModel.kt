package com.shapee.android.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*


open class BaseViewModel<T>() : ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String?>()

    protected var mNavigator:T? = null
    fun setNavigator(navigator: T){
        mNavigator = navigator
    }

    var jobs = listOf<Job>()

    fun launchAsync(block: suspend CoroutineScope.() -> Unit): Job {
        return GlobalScope.launch(Dispatchers.Main) { block() }
    }

    suspend fun <T> async(block: suspend CoroutineScope.() -> T): Deferred<T> {
        return GlobalScope.async(Dispatchers.Default) { block() }
    }

    suspend fun <T> asyncAwait(block: suspend CoroutineScope.() -> T): T {
        return async(block).await()
    }



}