package it.programmazionemobile.olympicgames2024.databaseInterface

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import it.programmazionemobile.olympicgames2024.data.model.ResultsEntity


@Dao
interface ResultsDao {

    @Query("SELECT * FROM results_table WHERE isFavourite = 1")
    fun getFavouriteResults(): List<ResultsEntity>// Preferiti come LiveData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: ResultsEntity) // Inserimento di un elemento

    @Query("UPDATE results_table SET isFavourite = :isFavourite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: String, isFavourite: Boolean) // Aggiorna stato preferito

    @Delete
    suspend fun deleteResult(result: ResultsEntity) // Elimina un elemento

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg result: ResultsEntity)

    @Query("DELETE FROM results_table WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM results_table WHERE matchName = :matchName")
    fun deleteResult(matchName: String)

    @Query("SELECT * FROM results_table WHERE isFavourite = 1")
    fun selectAllFavoriteList(): List<ResultsEntity>

    @Query("INSERT INTO results_table (id, isFavourite) VALUES (NULL, 0)")
    fun insertEmpty()

    @Query("SELECT * FROM results_table WHERE gender = 'Maschile'")
    fun getResultsMaschile(): List<ResultsEntity>

    @Query("SELECT * FROM results_table WHERE gender = 'Femminile'")
    fun getResultsFemminile(): List<ResultsEntity>

    @Query("SELECT * FROM results_table")
    suspend fun getAllResults(): List<ResultsEntity>

    @Update
    suspend fun update(result: ResultsEntity)
}