package it.programmazionemobile.olympicgames2024

import it.programmazionemobile.olympicgames2024.repository.ResultsRepository
import it.programmazionemobile.olympicgames2024.viewmodel.DisciplineResultsViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DisciplineResultsViewModelTest {

    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule() // Per testare LiveData

    private lateinit var viewModel: DisciplineResultsViewModel
    private lateinit var repository: ResultsRepository

    @Before
    fun setup() {
        viewModel = DisciplineResultsViewModel(repository)
    }

    @Test
    fun `test searchByGenderWithMockList with Maschile`() = runBlocking {
        viewModel.searchByGenderWithMockList("Maschile")
        val results = viewModel.searchResults.value ?: emptyList()
        assert(results.all { it.gender == "Maschile" }) // Controlla che tutti i risultati siano Maschili
    }

    @Test
    fun `test searchByGenderWithMockList with Femminile`() = runBlocking {
        viewModel.searchByGenderWithMockList("Femminile")
        val results = viewModel.searchResults.value ?: emptyList()
        assert(results.all { it.gender == "Femminile" }) // Controlla che tutti i risultati siano Femminili
    }
}

