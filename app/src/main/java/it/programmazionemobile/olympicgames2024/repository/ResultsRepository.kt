package it.programmazionemobile.olympicgames2024.repository

import it.programmazionemobile.olympicgames2024.data.model.ResultsEntity
import it.programmazionemobile.olympicgames2024.databaseInterface.ResultsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResultsRepository(private val resultsDao: ResultsDao) {

    // Funzione per ottenere i risultati Maschili
    suspend fun getResultsMaschile(): List<ResultsEntity> {
        return withContext(Dispatchers.IO) { // Esegui la query in background
            resultsDao.getResultsMaschile()
        }
    }

    // Funzione per ottenere i risultati Femminili
    suspend fun getResultsFemminile(): List<ResultsEntity> {
        return withContext(Dispatchers.IO) { // Esegui la query in background
            resultsDao.getResultsFemminile()
        }
    }


    suspend fun getAllResults(): List<ResultsEntity> {
        return resultsDao.getAllResults()
    }

    /* Factory method */
    companion object {
        private var INSTANCE: ResultsRepository? = null

        fun getDataSource(resultsDao: ResultsDao): ResultsRepository {
            return synchronized(ResultsRepository::class) {
                val newInstance = INSTANCE ?: ResultsRepository(resultsDao)
                INSTANCE = newInstance
                newInstance
            }
        }
    }

}
