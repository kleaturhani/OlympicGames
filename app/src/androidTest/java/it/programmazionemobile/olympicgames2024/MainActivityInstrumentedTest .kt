package it.programmazionemobile.olympicgames2024

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import it.programmazionemobile.olympicgames2024.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest  {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testMainActivityIsDisplayed() {
        onView(withId(R.id.menu_bar)).check(matches(isDisplayed()))
    }

    @Test
    fun testNavigationBarInteraction() {
        val menuHome = R.id.nav_home
        val menuProfile = R.id.nav_profile

        onView(withId(R.id.menu_bar)).check(matches(isDisplayed()))

        onView(withId(menuHome)).perform(click())
        onView(withId(R.id.nav_home)).check(matches(isDisplayed())) // Controlla se il frammento è visibile

        onView(withId(menuProfile)).perform(click())
        onView(withId(R.id.nav_profile)).check(matches(isDisplayed())) // Controlla se il frammento è visibile
    }
}