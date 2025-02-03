package it.programmazionemobile.olympicgames2024.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class Matches(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var discipline: String,
    var matchName: String,
    var finalPoints: String,
    var statusEvent: String,
    var matchFlag: String,
    var date: String,
) {
}
