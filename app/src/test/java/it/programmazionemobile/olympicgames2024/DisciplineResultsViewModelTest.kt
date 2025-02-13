package it.programmazionemobile.olympicgames2024

import it.programmazionemobile.olympicgames2024.data.model.ResultsEntity
import it.programmazionemobile.olympicgames2024.databaseInterface.ResultsDao
import it.programmazionemobile.olympicgames2024.repository.ResultsRepository
import it.programmazionemobile.olympicgames2024.viewmodel.DisciplineResultsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DisciplineResultsViewModelTest {

    private lateinit var viewModel: DisciplineResultsViewModel
    private lateinit var repository: ResultsRepository
    private lateinit var resultsDao: ResultsDao

    @Before
    fun setup() {
        resultsDao = object : ResultsDao {
            private val results = mutableListOf(
                ResultsEntity(
                    "1",
                    "3x3 Basketball",
                    "Partita Semifinale Maschile",
                    "USA - FRA",
                    "21 - 19",
                    "Finished",
                    "USA, FRA",
                    "2024-07-27 18:00",
                    "Maschile",
                    false
                ),
                ResultsEntity(
                    "2",
                    "Swimming",
                    "Finale 200m",
                    "AUS - USA",
                    "1:44.5",
                    "Finished",
                    "AUS",
                    "2024-07-27 19:00",
                    "Femminile",
                    true
                )
            )

            override fun getFavouriteResults(): List<ResultsEntity> {
                return results.filter { it.isFavourite }
            }

            override suspend fun insertResult(result: ResultsEntity) {
                results.add(result)
            }

            override suspend fun updateFavoriteStatus(id: String, isFavourite: Boolean) {
                results.find { it.id == id }?.isFavourite = isFavourite
            }

            override suspend fun deleteResult(result: ResultsEntity) {
                results.remove(result)
            }

            override suspend fun insert(vararg result: ResultsEntity) {
                results.addAll(result)
            }

            override suspend fun deleteById(id: String) {
                results.removeAll { it.id == id }
            }

            override fun deleteResult(matchName: String) {
                results.removeAll { it.matchName == matchName }
            }

            override fun selectAllFavoriteList(): List<ResultsEntity> {
                return results.filter { it.isFavourite }
            }

            override fun insertEmpty() {
                results.add(ResultsEntity("0", "", "", "", "", "", "", "", "", false))
            }

            override fun getResultsMaschile(): List<ResultsEntity> {
                return results.filter { it.gender == "Maschile" }
            }

            override fun getResultsFemminile(): List<ResultsEntity> {
                return results.filter { it.gender == "Femminile" }
            }

            override suspend fun getAllResults(): List<ResultsEntity> {
                return results
            }

            override suspend fun update(result: ResultsEntity) {
                val index = results.indexOfFirst { it.id == result.id }
                if (index != -1) results[index] = result
            }
        }

        repository = ResultsRepository(resultsDao)
        viewModel = DisciplineResultsViewModel(repository)
    }

    @Test
    fun `test ViewModel inizializza correttamente i dati`() {
        assertNotNull(viewModel.searchResults) // Controlla che il LiveData non sia null
    }
}
