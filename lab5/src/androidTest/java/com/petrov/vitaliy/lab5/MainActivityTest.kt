package com.petrov.vitaliy.lab5


import android.content.pm.ActivityInfo
import android.widget.Button
import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    var activityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)

    private val buttonId = R.id.button
    private val textId = R.id.editText

    @Before
    fun setUp() {
        activityTestRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    @Test
    fun afterCreating() {
        onView(withId(buttonId)).check(matches(isEnabled())).check(matches(isClickable()))
        onView(withId(textId)).check(matches(isEnabled())).check(matches(isFocusable()))
    }

    @Test
    fun checkButtonTextAfterRotation() {
        val buttonTextBefore = activityTestRule.activity.findViewById<Button>(buttonId).text.toString()
        val editTextBefore = activityTestRule.activity.findViewById<EditText>(textId).text.toString()
        onView(withId(buttonId)).perform(click())
        val buttonTextChanged = activityTestRule.activity.findViewById<Button>(buttonId).text.toString()
        activityTestRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        Thread.sleep(200)
        val buttonTextAfter = activityTestRule.activity.findViewById<Button>(buttonId).text.toString()
        val editTextAfter = activityTestRule.activity.findViewById<EditText>(textId).text.toString()
        Preconditions.checkState(buttonTextBefore == buttonTextAfter,"не совпадает")
        Preconditions.checkState(buttonTextChanged == "Button Changed","не совпадает")
        Preconditions.checkState(editTextBefore == editTextAfter,"не совпадает")
    }
}
