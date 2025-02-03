package it.programmazionemobile.olympicgames2024.database

import androidx.room.Database
import androidx.room.RoomDatabase
import it.programmazionemobile.olympicgames2024.data.model.ResultsEntity
import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import it.programmazionemobile.olympicgames2024.databaseInterface.ResultsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ResultsEntity::class], version = 5, exportSchema = false)
abstract class ResultsDatabase : RoomDatabase() {
    abstract fun resultsDao(): ResultsDao

    companion object {
        @Volatile
        private var INSTANCE: ResultsDatabase? = null

        val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Crea una nuova tabella con lo schema aggiornato (incluso gender)
                database.execSQL(
                    """
            CREATE TABLE results_table_new (
                id TEXT NOT NULL PRIMARY KEY,
                discipline TEXT NOT NULL,
                matchName TEXT NOT NULL,
                finalPoints TEXT NOT NULL,
                eventStatus TEXT NOT NULL,
                matchFlags TEXT NOT NULL,
                formattedDate TEXT NOT NULL,
                matchSquadsName TEXT NOT NULL DEFAULT '',
                gender TEXT NOT NULL, 
                isFavourite INTEGER NOT NULL DEFAULT 0 
            )
            """
                )

                // Copia i dati dalla vecchia tabella alla nuova (senza duplicazioni)
                database.execSQL(
                    """
            INSERT INTO results_table_new (id, discipline, matchName, finalPoints, eventStatus, matchFlags, formattedDate, matchSquadsName, gender, isFavourite)
            SELECT id, discipline, matchName, finalPoints, eventStatus, matchFlags, formattedDate, matchSquadsName, gender, isFavourite
            FROM results_table
            """
                )

                // Elimina la vecchia tabella
                database.execSQL("DROP TABLE results_table")

                // Rinomina la nuova tabella
                database.execSQL("ALTER TABLE results_table_new RENAME TO results_table")

                database.execSQL("ALTER TABLE results_table ADD COLUMN isFavourite INTEGER DEFAULT 0 NOT NULL")

                database.execSQL("ALTER TABLE results_table ADD COLUMN gender TEXT NOT NULL")
            }
        }


        fun getDatabase(context: Context): ResultsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResultsDatabase::class.java,
                    "results_database"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                val resultsDao = getDatabase(context).resultsDao()
                                val olympicResults = listOf(
                                    ResultsEntity(
                                        id = "1",
                                        discipline = "3x3 Basketball",
                                        matchName = "Partita Semifinale Maschile",
                                        matchSquadsName = "USA - FRA",
                                        finalPoints = "21 - 19",
                                        eventStatus = "Finished",
                                        matchFlags = "USA, FRA",
                                        formattedDate = "2024-07-27 18:00",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "2",
                                        discipline = "3x3 Basketball",
                                        matchName = "Partita Finale Femminile",
                                        matchSquadsName = "JPN - CAN",
                                        finalPoints = "18 - 15",
                                        eventStatus = "Finished",
                                        matchFlags = "JPN, CAN",
                                        formattedDate = "2024-07-28 20:00",
                                        gender = "Femminile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "3",
                                        discipline = "3x3 Basketball",
                                        matchName = "Partita per il Bronzo Maschile",
                                        matchSquadsName = "AUS vs ESP",
                                        finalPoints = "17 - 15",
                                        eventStatus = "Finished",
                                        matchFlags = "🇦🇺, 🇪🇸",
                                        formattedDate = "2024-07-26",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "4",
                                        discipline = "Tiro con l'arco",
                                        matchName = "Individuale Maschile",
                                        matchSquadsName = "USA vs KOR",
                                        finalPoints = "6 - 4",
                                        eventStatus = "Finished",
                                        matchFlags = "🇺🇸, 🇰🇷",
                                        formattedDate = "2024-07-25",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "5",
                                        discipline = "Tiro con l'arco",
                                        matchName = "Individuale Femminile",
                                        matchSquadsName = "MEX vs CHN",
                                        finalPoints = "7 - 3",
                                        eventStatus = "Finished",
                                        matchFlags = "🇲🇽, 🇨🇳",
                                        formattedDate = "2024-07-26",
                                        gender = "Femminile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "6",
                                        discipline = "Tiro con l'arco",
                                        matchName = "Squadra Mista",
                                        matchSquadsName = "JPN vs ITA",
                                        finalPoints = "5 - 5 (Shoot-off)",
                                        eventStatus = "Finished",
                                        matchFlags = "🇯🇵, 🇮🇹",
                                        formattedDate = "2024-07-27",
                                        gender = "Mista",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "7",
                                        discipline = "Tiro con l'arco",
                                        matchName = "Squadra Maschile e Femminile",
                                        matchSquadsName = "FRA vs GBR",
                                        finalPoints = "6 - 2",
                                        eventStatus = "Finished",
                                        matchFlags = "🇫🇷, 🇬🇧",
                                        formattedDate = "2024-07-28",
                                        gender = "Femminile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "8",
                                        discipline = "Ginnastica artistica",
                                        matchName = "Corpo Libero Maschile",
                                        matchSquadsName = "USA, JPN, GBR",
                                        finalPoints = "14.700",
                                        eventStatus = "Finished",
                                        matchFlags = "🇺🇸, 🇯🇵, 🇬🇧",
                                        formattedDate = "2024-07-28",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "9",
                                        discipline = "Ginnastica artistica",
                                        matchName = "Volteggio Femminile",
                                        matchSquadsName = "BRA, RUS, CHN",
                                        finalPoints = "15.100",
                                        eventStatus = "Finished",
                                        matchFlags = "🇧🇷, 🇷🇺, 🇨🇳",
                                        formattedDate = "2024-07-29",
                                        gender = "Femminile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "10",
                                        discipline = "Ginnastica artistica",
                                        matchName = "Parallele Maschili",
                                        matchSquadsName = "GER, FRA, ITA",
                                        finalPoints = "15.600",
                                        eventStatus = "Finished",
                                        matchFlags = "🇩🇪, 🇫🇷, 🇮🇹",
                                        formattedDate = "2024-07-30",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "11",
                                        discipline = "Ginnastica artistica",
                                        matchName = "Trave Femminile",
                                        matchSquadsName = "USA, RUS, CHN",
                                        finalPoints = "14.800",
                                        eventStatus = "Finished",
                                        matchFlags = "🇺🇸, 🇷🇺, 🇨🇳",
                                        formattedDate = "2024-07-31",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "12",
                                        discipline = "Pallavolo",
                                        matchName = "Partita Finale Maschile",
                                        matchSquadsName = "BRA vs ITA",
                                        finalPoints = "3 - 2",
                                        eventStatus = "Finished",
                                        matchFlags = "🇧🇷, 🇮🇹",
                                        formattedDate = "2024-08-04",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "13",
                                        discipline = "Pallavolo",
                                        matchName = "Partita Semifinale Femminile",
                                        matchSquadsName = "USA vs CHN",
                                        finalPoints = "3 - 1",
                                        eventStatus = "Finished",
                                        matchFlags = "🇺🇸, 🇨🇳",
                                        formattedDate = "2024-08-03",
                                        gender = "Femminile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "14",
                                        discipline = "Pallavolo",
                                        matchName = "Partita per il Bronzo Femminile",
                                        matchSquadsName = "NED vs RUS",
                                        finalPoints = "3 - 0",
                                        eventStatus = "Finished",
                                        matchFlags = "🇳🇱, 🇷🇺",
                                        formattedDate = "2024-08-02",
                                        gender = "Femminile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "15",
                                        discipline = "Nuoto",
                                        matchName = "100m Stile Libero Maschile",
                                        matchSquadsName = "USA, AUS, FRA",
                                        finalPoints = "47.58",
                                        eventStatus = "Finished",
                                        matchFlags = "🇺🇸, 🇦🇺, 🇫🇷",
                                        formattedDate = "2024-07-25",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "16",
                                        discipline = "Nuoto",
                                        matchName = "200m Rana Femminile",
                                        matchSquadsName = "JPN, CHN, RUS",
                                        finalPoints = "2:19.64",
                                        eventStatus = "Finished",
                                        matchFlags = "🇯🇵, 🇨🇳, 🇷🇺",
                                        formattedDate = "2024-07-26",
                                        gender = "Femminile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "17",
                                        discipline = "Nuoto",
                                        matchName = "400m Misti Individuali Femminile",
                                        matchSquadsName = "HUN, USA, AUS",
                                        finalPoints = "4:31.12",
                                        eventStatus = "Finished",
                                        matchFlags = "🇭🇺, 🇺🇸, 🇦🇺",
                                        formattedDate = "2024-07-27",
                                        gender = "Femminile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "18",
                                        discipline = "Nuoto",
                                        matchName = "Staffetta 4x100m Mista Maschile",
                                        matchSquadsName = "USA, GBR, AUS",
                                        finalPoints = "3:27.45",
                                        eventStatus = "Finished",
                                        matchFlags = "🇺🇸, 🇬🇧, 🇦🇺",
                                        formattedDate = "2024-07-28",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "19",
                                        discipline = "Golf",
                                        matchName = "Individuale Maschile",
                                        matchSquadsName = "USA, GBR, JPN",
                                        finalPoints = "-12",
                                        eventStatus = "Finished",
                                        matchFlags = "🇺🇸, 🇬🇧, 🇯🇵",
                                        formattedDate = "2024-08-01",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "20",
                                        discipline = "Golf",
                                        matchName = "Individuale Femminile",
                                        matchSquadsName = "KOR, MEX, GER",
                                        finalPoints = "-10",
                                        eventStatus = "Finished",
                                        matchFlags = "🇰🇷, 🇲🇽, 🇩🇪",
                                        formattedDate = "2024-08-02",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "21",
                                        discipline = "Tennis",
                                        matchName = "Singolare Maschile",
                                        matchSquadsName = "ESP vs ITA",
                                        finalPoints = "6-4, 7-6",
                                        eventStatus = "Finished",
                                        matchFlags = "🇪🇸, 🇮🇹",
                                        formattedDate = "2024-08-04",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "22",
                                        discipline = "Tennis",
                                        matchName = "Doppio Femminile",
                                        matchSquadsName = "USA vs CZE",
                                        finalPoints = "6-3, 6-4",
                                        eventStatus = "Finished",
                                        matchFlags = "🇺🇸, 🇨🇿",
                                        formattedDate = "2024-08-03",
                                        gender = "Femminile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "23",
                                        discipline = "Tennis",
                                        matchName = "Doppio Misto",
                                        matchSquadsName = "FRA vs AUS",
                                        finalPoints = "6-7, 7-5, 10-8",
                                        eventStatus = "Finished",
                                        matchFlags = "🇫🇷, 🇦🇺",
                                        formattedDate = "2024-08-02",
                                        gender = "Mista",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "24",
                                        discipline = "Lotta",
                                        matchName = "Lotta Libera Maschile 57kg",
                                        matchSquadsName = "USA vs IRN",
                                        finalPoints = "5-3",
                                        eventStatus = "Finished",
                                        matchFlags = "🇺🇸, 🇮🇷",
                                        formattedDate = "2024-08-05",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "25",
                                        discipline = "Lotta",
                                        matchName = "Lotta Greco-Romana Femminile 53kg",
                                        matchSquadsName = "JPN vs CHN",
                                        finalPoints = "6-2",
                                        eventStatus = "Finished",
                                        matchFlags = "🇯🇵, 🇨🇳",
                                        formattedDate = "2024-08-06",
                                        gender = "Femminile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "26",
                                        discipline = "Lotta",
                                        matchName = "Lotta Libera Maschile 74kg",
                                        matchSquadsName = "RUS vs USA",
                                        finalPoints = "8-5",
                                        eventStatus = "Finished",
                                        matchFlags = "🇷🇺, 🇺🇸",
                                        formattedDate = "2024-08-07",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "27",
                                        discipline = "Judo",
                                        matchName = "Maschile",
                                        matchSquadsName = "FRA vs JPN",
                                        finalPoints = "10-0",
                                        eventStatus = "Finished",
                                        matchFlags = "🇫🇷, 🇯🇵",
                                        formattedDate = "2024-08-07",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "28",
                                        discipline = "Judo",
                                        matchName = "Femminile",
                                        matchSquadsName = "CHN vs KOR",
                                        finalPoints = "7-3",
                                        eventStatus = "Finished",
                                        matchFlags = "🇨🇳, 🇰🇷",
                                        formattedDate = "2024-08-08",
                                        gender = "Femminile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "29",
                                        discipline = "Taekwondo",
                                        matchName = "Maschile",
                                        matchSquadsName = "ESP vs ITA",
                                        finalPoints = "15-12",
                                        eventStatus = "Finished",
                                        matchFlags = "🇪🇸, 🇮🇹",
                                        formattedDate = "2024-08-09",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "30",
                                        discipline = "Taekwondo",
                                        matchName = "Femminile",
                                        matchSquadsName = "GBR vs CHN",
                                        finalPoints = "14-10",
                                        eventStatus = "Finished",
                                        matchFlags = "🇬🇧, 🇨🇳",
                                        formattedDate = "2024-08-10",
                                        gender = "Femminile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "31",
                                        discipline = "Rugby a 7",
                                        matchName = "Partita Finale Maschile",
                                        matchSquadsName = "NZL vs AUS",
                                        finalPoints = "21-19",
                                        eventStatus = "Finished",
                                        matchFlags = "🇳🇿, 🇦🇺",
                                        formattedDate = "2024-08-11",
                                        gender = "Maschile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "32",
                                        discipline = "Rugby a 7",
                                        matchName = "Partita Finale Femminile",
                                        matchSquadsName = "FRA vs USA",
                                        finalPoints = "28-14",
                                        eventStatus = "Finished",
                                        matchFlags = "🇫🇷, 🇺🇸",
                                        formattedDate = "2024-08-10",
                                        gender = "Femminile",
                                        isFavourite = false
                                    ),
                                    ResultsEntity(
                                        id = "33",
                                        discipline = "Rugby a 7",
                                        matchName = "Partita per il Bronzo Maschile",
                                        matchSquadsName = "FIJ vs GBR",
                                        finalPoints = "24-17",
                                        eventStatus = "Finished",
                                        matchFlags = "🇫🇯, 🇬🇧",
                                        formattedDate = "2024-08-09",
                                        gender = "Maschile",
                                        isFavourite = false
                                    )
                                )
                                resultsDao.insert(*olympicResults.toTypedArray())

                            }
                        }
                    })
                    .addMigrations(MIGRATION_4_5)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}