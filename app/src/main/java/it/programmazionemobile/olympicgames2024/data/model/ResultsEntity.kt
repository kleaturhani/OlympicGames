package it.programmazionemobile.olympicgames2024.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results_table")
data class ResultsEntity(
    @PrimaryKey val id: String,
    val discipline: String,
    val matchName: String,
    val matchSquadsName: String,
    val finalPoints: String,
    val eventStatus: String,
    val matchFlags: String,
    val formattedDate: String,
    val gender: String,
    var isFavourite: Boolean = false
)


object MockList {

    fun getModel(): List<ResultsEntity> {

        val match1 = ResultsEntity(
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
        )

        val match2 = ResultsEntity(
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
        )

        val match3 = ResultsEntity(
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
        )

        val match4 = ResultsEntity(
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
        )

        val match5 = ResultsEntity(
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
        )

        val match6 = ResultsEntity(
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
        )

        val match7 = ResultsEntity(
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
        )

        val match8 = ResultsEntity(
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
        )

        val match9 = ResultsEntity(
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
        )

        val match10 = ResultsEntity(
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
        )

        val match11 = ResultsEntity(
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
        )

        val match12 = ResultsEntity(
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
        )

        val match13 = ResultsEntity(
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
        )

        val match14 = ResultsEntity(
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
        )

        val match15 = ResultsEntity(
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
        )

        val match16 = ResultsEntity(
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
        )

        val match17 = ResultsEntity(
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
        )

        val match18 = ResultsEntity(
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
        )

        val match19 = ResultsEntity(
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
        )

        val match20 = ResultsEntity(
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
        )

        val match21 = ResultsEntity(
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
        )

        val match22 = ResultsEntity(
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
        )

        val match23 = ResultsEntity(
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
        )

        val match24 = ResultsEntity(
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
        )

        val match25 = ResultsEntity(
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
        )

        val match26 = ResultsEntity(
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
        )

        val match27 = ResultsEntity(
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
        )

        val match28 = ResultsEntity(
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
        )

        val match29 = ResultsEntity(
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
        )

        val match30 = ResultsEntity(
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
        )

        val match31 = ResultsEntity(
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
        )

        val match32 = ResultsEntity(
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
        )

        val match33 = ResultsEntity(
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


        val itemList: ArrayList<ResultsEntity> = ArrayList()
        itemList.add(match1)
        itemList.add(match2)
        itemList.add(match3)
        itemList.add(match4)
        itemList.add(match5)
        itemList.add(match6)
        itemList.add(match7)
        itemList.add(match8)
        itemList.add(match9)
        itemList.add(match10)
        itemList.add(match11)
        itemList.add(match12)
        itemList.add(match13)
        itemList.add(match14)
        itemList.add(match15)
        itemList.add(match16)
        itemList.add(match17)
        itemList.add(match18)
        itemList.add(match19)
        itemList.add(match20)
        itemList.add(match21)
        itemList.add(match22)
        itemList.add(match23)
        itemList.add(match24)
        itemList.add(match25)
        itemList.add(match26)
        itemList.add(match27)
        itemList.add(match28)
        itemList.add(match29)
        itemList.add(match30)
        itemList.add(match31)
        itemList.add(match32)
        itemList.add(match33)

        return itemList
    }
}