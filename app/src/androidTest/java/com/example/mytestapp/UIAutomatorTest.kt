package com.example.mytestapp

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class UIAutomatorTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private val packageName = context.packageName

    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())


    @Before
    fun setup() {
        uiDevice.pressHome()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
    }

    companion object {
        private const val TIMEOUT = 5000L

    }

    @Test
    fun test_MainActivityIsStarted() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        Assert.assertNotNull(editText)
    }


    @Test
    fun test_DetailsActivityButtonExist() {

        val buttonToDetails = uiDevice.findObject(By.res(packageName, "toDetailsActivityButton"))
        Assert.assertNotNull(buttonToDetails)
        buttonToDetails.isClickable
    }

    @Test
    fun test_SearchActivityButtonExist() {

        val buttonSearch = uiDevice.findObject(By.res(packageName, "searchActivityButton"))
        Assert.assertNotNull(buttonSearch)
        buttonSearch.isClickable
    }

    @Test
    fun test_OpenDetailsActivity() {
        uiDevice.click(10, 10)
        val toDetails = uiDevice.findObject(By.res(packageName, "toDetailsActivityButton"))
        toDetails.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )
        Assert.assertEquals(changedText.text, "Number of results: 0")

    }


    @Test
    fun test_SearchIsPositive() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        editText.text = "algol"

        val search = uiDevice.findObject(By.res(packageName, "searchActivityButton"))
        search.click()

        val changedText =
            uiDevice.wait(Until.findObject(By.res(packageName, "totalCountTextView")), TIMEOUT)

        Assert.assertEquals(changedText.text.toString(), "Number of results: 3171")
    }


    @Test
    fun test_IncrementIsWork() {
        uiDevice.click(10, 10)
        val toDetails = uiDevice.findObject(By.res(packageName, "toDetailsActivityButton"))
        toDetails.click()

        uiDevice.wait(Until.hasObject(By.res(packageName, "totalCountTextView")), TIMEOUT)

        val button = uiDevice.findObject(By.res(packageName, "incrementButton"))
        button.click()

        val textView = uiDevice.findObject(By.res(packageName, "totalCountTextView"))
        Assert.assertEquals(textView.text, "Number of results: 1")
    }

    @Test
    fun test_DecrementIsWork() {
        uiDevice.click(10, 10)
        val toDetails = uiDevice.findObject(By.res(packageName, "toDetailsActivityButton"))
        toDetails.click()

        uiDevice.wait(Until.hasObject(By.res(packageName, "totalCountTextView")), TIMEOUT)

        val button = uiDevice.findObject(By.res(packageName, "decrementButton"))
        button.click()

        val textView = uiDevice.findObject(By.res(packageName, "totalCountTextView"))

        Assert.assertEquals(textView.text, "Number of results: -1")
    }

    @Test
    fun test_SaveResultText() {

        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        editText.text = "algol"

        val search = uiDevice.findObject(By.res(packageName, "searchActivityButton"))
        search.click()

        uiDevice.wait(Until.hasObject(By.res(packageName, "totalCountTextView")), TIMEOUT)

        val toDetails = uiDevice.findObject(By.res(packageName, "toDetailsActivityButton"))
        toDetails.click()

        uiDevice.wait(Until.hasObject(By.res(packageName, "totalCountTextView")), TIMEOUT)

        val textView = uiDevice.findObject(By.res(packageName, "totalCountTextView"))
        Assert.assertEquals(textView.text, "Number of results: 3171")

    }

    @Test
    fun test_BackPressIsWork() {
        uiDevice.click(10, 10)
        val toDetails = uiDevice.findObject(By.res(packageName, "toDetailsActivityButton"))
        toDetails.click()

        uiDevice.wait(Until.hasObject(By.res(packageName, "totalCountTextView")), TIMEOUT)

        uiDevice.pressBack()

        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        Assert.assertNotNull(editText)


    }

}