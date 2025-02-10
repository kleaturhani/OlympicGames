package it.programmazionemobile.olympicgames2024

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import it.programmazionemobile.olympicgames2024.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DisciplineSelectionUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testSelectDiscipline() {
        onView(withId(R.id.discipline_name)) // Controlla la lista delle discipline
            .perform(click()) // Clicca sulla prima disciplina

        onView(withId(R.id.event_text)) // Controlla la lista degli eventi
            .check(matches(isDisplayed())) // Verifica che gli eventi vengano mostrati
    }
}

