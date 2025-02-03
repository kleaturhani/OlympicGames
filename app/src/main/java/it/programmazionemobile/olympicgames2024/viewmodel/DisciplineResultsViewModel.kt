package it.programmazionemobile.olympicgames2024.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.programmazionemobile.olympicgames2024.data.model.MockList
import it.programmazionemobile.olympicgames2024.data.model.ResultsEntity
import it.programmazionemobile.olympicgames2024.repository.ResultsRepository
import kotlinx.coroutines.launch

class DisciplineResultsViewModel(private val repository: ResultsRepository) : ViewModel() {

    // LiveData per osservare i risultati della ricerca
    private val _searchResults = MutableLiveData<List<ResultsEntity>>()
    val searchResults: LiveData<List<ResultsEntity>> get() = _searchResults


    // Funzione per cercare i risultati in base al genere usando MockList
    fun searchByGenderWithMockList(gender: String) {
        viewModelScope.launch {
            val results = when (gender) {
                "Maschile" -> MockList.getModel().filter { it.gender == "Maschile" }
                "Femminile" -> MockList.getModel().filter { it.gender == "Femminile" }
                else -> MockList.getModel() // Se "Tutti", prendi tutti i risultati
            }
            _searchResults.postValue(results)
        }
    }
}
