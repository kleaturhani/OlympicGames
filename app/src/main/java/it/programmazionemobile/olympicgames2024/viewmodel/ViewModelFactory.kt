package it.programmazionemobile.olympicgames2024.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import it.programmazionemobile.olympicgames2024.repository.ResultsRepository

class ViewModelFactory(private val repository: ResultsRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DisciplineResultsViewModel::class.java)) {
            return DisciplineResultsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
