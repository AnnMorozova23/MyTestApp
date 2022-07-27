package com.example.mytestapp

import android.os.Build
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.mytestapp.view.search.MainActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test


class MainActivityEspressoTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityEditTextView_NotNull() {
        scenario.onActivity {
            val searchEditTextView =
                it.findViewById<EditText>(R.id.searchEditText)
            TestCase.assertNotNull(searchEditTextView)
        }
    }

    @Test
    fun activityEditTextView_IsDisplayed() {
        onView(withId(R.id.searchEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun activityButton_IsDisplayed() {
        onView(withId(R.id.toDetailsActivityButton)).check(matches(isDisplayed()))
    }

    @Test
    fun detailsActivityButton_IsClicked() {
        onView(withId(R.id.toDetailsActivityButton)).check(matches(isClickable()))
    }

    @Test
    fun activitySearch_IsWorking() {
        onView(withId(R.id.searchEditText)).perform(click())
        onView(withId(R.id.searchEditText)).perform(
            replaceText("algol"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.searchEditText)).perform(pressImeActionButton())
        onView(isRoot()).perform(delay())

            onView(withId(R.id.totalCountTextView)).check(matches(withText("Number of results: 42")))

    }

    private fun delay(): ViewAction? {
        return object : ViewAction {
            override fun getConstraints() = isRoot()
            override fun getDescription(): String = "wait for $2 seconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(2000)
            }
        }
    }


    @After
    fun close() {
        scenario.close()
    }


}