package com.example.mytestapp

import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mytestapp.view.details.DetailsActivity
import com.example.mytestapp.view.search.MainActivity
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])

class MainActivityTest {

    lateinit var scenario: ActivityScenario<MainActivity>
    lateinit var detailScenario: ActivityScenario<DetailsActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        detailScenario = ActivityScenario.launch(DetailsActivity::class.java)
    }

    @After
    fun close() {
        scenario.close()
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityEditTextView_NotNull() {
        scenario.onActivity {
            val searchEditText =
                it.findViewById<EditText>(R.id.searchEditText)
            assertNotNull(searchEditText)
        }
    }

    @Test
    fun activityButton_NotNull() {
        scenario.onActivity {
            val toDetailsActivityButton =
                it.findViewById<Button>(R.id.toDetailsActivityButton)
            assertNotNull(toDetailsActivityButton)
        }
    }

    @Test
    fun activityButtons_AreVisible() {
        scenario.onActivity {
            val toDetailsActivityButton = it.findViewById<Button>(R.id.toDetailsActivityButton)
            assertEquals(View.VISIBLE, toDetailsActivityButton.visibility)
        }
    }

    @Test
    fun activityButtons_Click() {
        scenario.onActivity {
            val toDetailsActivityButton = it.findViewById<Button>(R.id.toDetailsActivityButton)
            toDetailsActivityButton.performClick()
            assertEquals(true, toDetailsActivityButton.isClickable)
        }
    }

    @Test
    fun activityEditText_Edit() {
        scenario.onActivity {
            val editText = it.findViewById<EditText>(R.id.searchEditText)
            editText.setText("test_testik", TextView.BufferType.EDITABLE)
            assertNotNull(editText.text)
            assertEquals("test_testik", editText.text.toString())
        }
    }
}
