package com.petrov.vitaliy.lab32

import android.content.pm.ActivityInfo
import android.view.Gravity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.DrawerMatchers.isOpen
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


class EspressoTest {

    @get:Rule
    var rule: ActivityTestRule<Activity1> =
    ActivityTestRule(Activity1::class.java)


    @Test
    fun testNavigation() {
        checkActivities(1)
        fromActivityToAboutAndBack(1)
        onView(withId(R.id.from_1_to_2)).perform(click())
        checkActivities(2)
        fromActivityToAboutAndBack(2)

        onView(withId(R.id.from_2_to_1)).perform(click())
        checkActivities(1)
        fromActivityToAboutAndBack(1)

        onView(withId(R.id.from_1_to_2)).perform(click())
        onView(withId(R.id.from_2_to_3)).perform(click())
        checkActivities(3)
        fromActivityToAboutAndBack(3)

        onView(withId(R.id.from_3_to_2)).perform(click())
        checkActivities(2)

        onView(withId(R.id.from_2_to_3)).perform(click())
        onView(withId(R.id.from_3_to_1)).perform(click())
        checkActivities(1)
        fromActivityToAboutAndBack(1)
    }

    private fun fromActivityToAboutAndBack(index : Int){
        checkActivities(index)
        val drawer = when(index){
            1 -> R.id.drawer1
            2 -> R.id.drawer2
            3 -> R.id.drawer3
            else -> -1
        }
        onView(withId(drawer)).check(matches(isClosed(Gravity.LEFT))).perform(DrawerActions.open())
        onView(withId(R.id.about)).perform(click())
        checkActivities(4)
        Espresso.pressBack()
        onView(withId(drawer)).check(matches(isOpen(Gravity.LEFT))).perform(DrawerActions.close())
        checkActivities(index)
    }


    @Test
    fun checkBackStack(){
        checkActivities(1)
        onView(withId(R.id.from_1_to_2)).perform(click())
        checkActivities(2)
        Espresso.pressBack()
        checkActivities(1)

        onView(withId(R.id.from_1_to_2)).perform(click())
        checkActivities(2)
        onView(withId(R.id.from_2_to_3)).perform(click())
        checkActivities(3)
        Espresso.pressBack()
        checkActivities(2)

        onView(withId(R.id.from_2_to_3)).perform(click())
        checkActivities(3)

        onView(withId(R.id.from_3_to_1)).perform(click())
        checkActivities(1)

        onView(withId(R.id.from_1_to_2)).perform(click())
        onView(withId(R.id.from_2_to_3)).perform(click())
        Espresso.pressBack()
        checkActivities(2)
        Espresso.pressBack()
        checkActivities(1)
        Espresso.pressBackUnconditionally()
        Assert.assertTrue(rule.activity.isDestroyed)
    }

    @Test
    fun testRotation(){
        testRotation(1)
        onView(withId(R.id.from_1_to_2)).perform(click())
        testRotation(2)
        onView(withId(R.id.from_2_to_3)).perform(click())
        testRotation(3)
    }

    private fun testRotation(index: Int){
        rule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        checkActivities(index)
        rule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        checkActivities(index)
        rule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }


    private fun checkActivities(indexOfActivity: Int) {
        when(indexOfActivity) {
            1 -> {
            isActivityLaunched(true, 1)
            isActivityLaunched(false, 2)
            isActivityLaunched(false, 3)
            onView(withId(R.id.text_about)).check(ViewAssertions.doesNotExist())
        }

            2 -> {
            isActivityLaunched(false, 1)
            isActivityLaunched(true, 2)
            isActivityLaunched(false, 3)
            onView(withId(R.id.text_about)).check(ViewAssertions.doesNotExist())
        }

            3 -> {
            isActivityLaunched(false, 1)
            isActivityLaunched(false, 2)
            isActivityLaunched(true, 3)
            onView(withId(R.id.text_about)).check(ViewAssertions.doesNotExist())
        }

            4 -> {
            isActivityLaunched(false, 1)
            isActivityLaunched(false, 2)
            isActivityLaunched(false, 3)
            onView(withId(R.id.text_about)).check(matches(ViewMatchers.isDisplayed()))
        }
        }
    }

    private fun isActivityLaunched(isCurrent: Boolean, index: Int){
        val button = when(index){
            1 -> R.id.from_1_to_2
            2 -> R.id.from_2_to_1
            3 -> R.id.from_3_to_1
            else -> -1
        }
        if(isCurrent)
            onView(withId(button)).check(matches(ViewMatchers.isDisplayed()))
        else
            onView(withId(button)).check(ViewAssertions.doesNotExist())
    }

}
