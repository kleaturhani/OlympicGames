package it.programmazionemobile.olympicgames2024.utility

import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.google.firebase.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar

// Extension functions per Int
fun Int.extendToTens() : String {
    return (if (this < 10) "0" else "") + this
}

// Extension functions per String
fun String.toIntOrZero(): Int {
    return this.toIntOrNull() ?: 0
}

// Extension functions per Any
fun Any.toInt(): Int {
    return this.toString().toIntOrZero()
}

// Extension functions per LocalDateTime
fun Calendar.minuteExtended(): String {
    return this.get(Calendar.MINUTE).let {
        (if (it < 10) "0" else "") + it
    }
}

fun Timestamp.toCalendar(): Calendar {
    val milliseconds = this.seconds * 1000 + this.nanoseconds / 1000000
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = milliseconds
    return calendar
}

// Estensione per Calendar per ottenere l'ora in formato esteso (ad esempio "03" per i numeri < 10)
fun Calendar.hourExtended(): String {
    return this.get(Calendar.HOUR_OF_DAY).let {
        (if (it < 10) "0" else "") + it
    }
}

// Estensione per Calendar per ottenere il giorno del mese in formato esteso
fun Calendar.dayOfMonthExtended(): String {
    return this.get(Calendar.DAY_OF_MONTH).let {
        (if (it < 10) "0" else "") + it
    }
}

// Estensione per Calendar per ottenere il mese in formato esteso
fun Calendar.monthValueExtended(): String {
    return (this.get(Calendar.MONTH) + 1).let {
        (if (it < 10) "0" else "") + it
    }
}

// Conversione della data di Calendar in formato String
// Assuming these extension functions are already defined for Calendar
fun Calendar.dateToString(extended: Boolean = false): String {
    val stringBuilder = StringBuilder()

    val day = this.get(Calendar.DAY_OF_MONTH)
    val month = this.get(Calendar.MONTH) + 1
    val year = this.get(Calendar.YEAR)

    if (extended)
        stringBuilder.append(day.extendToTens(), "/", month.extendToTens())
    else
        stringBuilder.append(day, "/", month)

    return stringBuilder.append("/", year).toString()
}

fun Calendar.timeToString(extended: Boolean = false): String {
    val stringBuilder = StringBuilder()
    if (extended)
        stringBuilder.append(this.hourExtended())
    else
        stringBuilder.append(this.get(Calendar.HOUR_OF_DAY))
    return stringBuilder.append(":", this.minuteExtended()).toString()
}


fun Timestamp.toLocalDateTime(): String {
    val milliseconds = this.seconds * 1000 + this.nanoseconds / 1000000

    // Per API livello 26 e superiore, usare java.time
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val instant = Instant.ofEpochMilli(milliseconds)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        return localDateTime.toString()  // Puoi restituire direttamente la stringa se necessario
    } else {
        // Per versioni precedenti a API level 26, usare Calendar e Date
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds

        // Estrai i valori di data e ora
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1  // I mesi sono indicizzati da 0
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        // Crea e ritorna una stringa formattata
        return "$year-$month-$day $hour:$minute"
    }
}


// Extension functions per View
fun View.tagToString(): String {
    return this.tag.toString()
}

// Extension functions per EditText
fun EditText.textToString(): String {
    return this.text.toString()
}

fun EditText.textToInt(): Int {
    return this.textToString().toIntOrZero()
}

fun EditText.onTextChanged(onTextChanged: () -> Boolean) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged.invoke()
        }

        override fun afterTextChanged(editable: Editable?) {
        }
    })
}

fun EditText.onFocusLost(onFocusLost: () -> Boolean) {
    this.setOnFocusChangeListener { _, hasFocus ->
        if (!hasFocus) {
            onFocusLost.invoke()
        }
    }
}

// Extension functions per MutableList
fun <T> MutableList<T>.prepend(element: T) {
    add(0, element)
}

