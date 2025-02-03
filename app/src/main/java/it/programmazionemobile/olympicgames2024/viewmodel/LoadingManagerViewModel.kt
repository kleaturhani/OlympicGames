package it.programmazionemobile.olympicgames2024.viewmodel

import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class LoadingManagerViewModel(initialStatus: Boolean = false) : ViewModel() {
    val loadingCompleted: MutableLiveData<Boolean> = MutableLiveData(initialStatus)

    protected open fun initSync() {}
    protected open suspend fun initAsync() {}

    fun initialize() {
        initSync()
        viewModelScope.launch {
            initAsync()
            stopLoading()
        }
    }

    fun reinitialize() {
        startLoadingAndDo { initialize() }
    }

    fun startLoading() {
        setLoadingCompleted(false)
    }

    fun stopLoading() {
        setLoadingCompleted(true)
    }

    fun startLoadingAndDo(loadingFunction: () -> Unit) {
        startLoading()
        loadingFunction.invoke()
    }

    fun whileLoading(loadingFunction: () -> Unit) {
        startLoading()
        loadingFunction.invoke()
        stopLoading()
    }

    private fun setLoadingCompleted(isCompleted: Boolean = true) {
        /* Controlla se il thread attuale è quello principale; in caso affermativo, aggiorna
        * initializationCompleted usando setValue(), altrimenti usando postValue() */
        if (Looper.myLooper() == Looper.getMainLooper()) {
            loadingCompleted.setValue(isCompleted)
        } else {
            loadingCompleted.postValue(isCompleted)
        }
    }

}