package it.programmazionemobile.olympicgames2024.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import it.programmazionemobile.olympicgames2024.data.model.MockList
import it.programmazionemobile.olympicgames2024.data.model.ResultsEntity
import it.programmazionemobile.olympicgames2024.databaseInterface.ResultsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ItemResultsViewModel(private val resultsDao: ResultsDao) :
    ViewModel() {


    val likeButtonPressed: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _favouriteResults = MutableLiveData<List<ResultsEntity>>()
    val favouriteResults: LiveData<List<ResultsEntity>> get() = _favouriteResults
    private val resultsList = MockList.getModel()


    // Inizializzazione
    init {
        getFavouriteResults()
    }

    private fun getFavouriteResults() {
        // Utilizzo di un coroutine per eseguire la query in background
        viewModelScope.launch(Dispatchers.IO) {
            val results =
                resultsDao.getFavouriteResults()
            withContext(Dispatchers.Main) {
                _favouriteResults.postValue(results)
                //}
            }
        }
    }


    fun toggleLikeButtonPressed(result: ResultsEntity) {
        //utilizzo mocklist
        result.isFavourite = !result.isFavourite

        viewModelScope.launch(Dispatchers.IO) {
            resultsDao.update(result) // Salva nel database

            // Log the updated value of isFavourite for debug
            Log.d(
                "ItemResultsViewModel",
                "Updated result ${result.id} isFavourite: ${result.isFavourite}"
            )


            withContext(Dispatchers.Main) {
                getFavouriteResults()
            }
        }
    }
}


class ItemResultsViewModelFactory(private val resultsDao: ResultsDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemResultsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemResultsViewModel(resultsDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}