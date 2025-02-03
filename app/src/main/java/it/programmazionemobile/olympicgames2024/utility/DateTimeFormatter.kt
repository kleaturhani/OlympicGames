package it.programmazionemobile.olympicgames2024.utility

import android.content.res.Resources
import com.google.firebase.Timestamp
import it.programmazionemobile.olympicgames2024.R
import java.time.LocalDateTime
import it.programmazionemobile.olympicgames2024.utility.dateToString
import it.programmazionemobile.olympicgames2024.utility.timeToString
import java.util.Calendar

class DateTimeFormatter {
    companion object{

        // Using Calendar to avoid issues with LocalDateTime on lower API levels
        fun calendarToTemplate(resources: Resources, calendar: Calendar): String {
            return resources.getString(
                R.string.timestampTemplate,
                calendar.dateToString(true), // Use extension function on Calendar
                calendar.timeToString(true) // Use extension function on Calendar
            )
        }

        fun firebaseTimestampToTemplate(resources: Resources, timestamp: Timestamp): String {
            val calendar = timestamp.toCalendar()
            return calendarToTemplate(resources, calendar)
        }

    }
}