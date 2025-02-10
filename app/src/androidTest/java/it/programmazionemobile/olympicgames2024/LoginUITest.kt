package it.programmazionemobile.olympicgames2024

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import it.programmazionemobile.olympicgames2024.view.MainActivity // L'Activity che ospita il Fragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java) // Avvia l'Activity principale

    @Test
    fun testLoginButtonClick() {
        // Assicura che il LoginFragment sia visibile
        onView(withId(R.id.loginFragment)).check(matches(isDisplayed()))

        // Simula l'inserimento dell'email e della password
        onView(withId(R.id.username)).perform(typeText("testuser@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.password)).perform(typeText("password123"), closeSoftKeyboard())
        onView(withId(R.id.btnlogin)).perform(click())

        // Verifica che l'utente venga reindirizzato alla schermata principale
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()))
    }
}

