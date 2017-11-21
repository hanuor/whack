package `in`.hanuor.whack

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.*
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/*
 * Copyright (C) 2016 Hanuor Inc. by Shantanu Johri(https://hanuor.github.io/shanjohri/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@RunWith(AndroidJUnit4::class)
        class Whackatest{
    @Rule
    var mActivityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)
    var mDevice: UiDevice ?= null


    @Before
    fun setUp() {
        // Initialize UiDevice instance

        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        // Start from the home screen
        mDevice?.pressHome()

        mDevice?.wait(Until.hasObject(By.pkg(mDevice?.getLauncherPackageName()).depth(0)), 1000)

       }
    @Test
    fun checkSettings(){

        // Simulate a short press on the HOME button.
        mDevice?.pressHome()

        // We’re now in the home screen. Next, we want to simulate
        // a user bringing up the All Apps screen.
        // If you use the uiautomatorviewer tool to capture a snapshot
        // of the Home screen, notice that the All Apps button’s
        // content-description property has the value “Apps”. We can
        // use this property to create a UiSelector to find the button.


        var allAppsButton: UiObject ?=null
        var uiselect = UiSelector()

        allAppsButton = mDevice?.findObject(uiselect.description("Apps"))

        // Simulate a click to bring up the All Apps screen.
        allAppsButton?.clickAndWaitForNewWindow()

        // In the All Apps screen, the Settings app is located in
        // the Apps tab. To simulate the user bringing up the Apps tab,
        // we create a UiSelector to find a tab with the text
        // label “Apps”.
        var appsTab: UiObject ?= null
        appsTab = mDevice?.findObject(uiselect.text("Apps"))

        // Simulate a click to enter the Apps tab.
        appsTab?.click();

        // Next, in the apps tabs, we can simulate a user swiping until
        // they come to the Settings app icon. Since the container view
        // is scrollable, we can use a UiScrollable object.

        var appViews = UiScrollable(uiselect.scrollable(true))

        // Set the swiping mode to horizontal (the default is vertical)
        appViews.setAsHorizontalList();

        // create a UiSelector to find the Settings app and simulate
        // a user click to launch the app.
        var settingsApp:UiObject  = appViews
                .getChildByText(uiselect
                .className(android.widget.TextView::class.java.getName()),
                "Settings")
        settingsApp.clickAndWaitForNewWindow();

        // Validate that the package name is the expected one
        var settingsValidation = UiObject(
                uiselect
                        .packageName("com.android.settings"))
        assertThat(settingsValidation.exists(), equalTo(true))
    }

    fun getLauncherPackageName():String {
        // Create launcher Intent
        var intent: Intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)

        // Use PackageManager to get the launcher package name
        var pm: PackageManager = InstrumentationRegistry.getContext().getPackageManager()
        var  resolveInfo: ResolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return resolveInfo.activityInfo.packageName
    }
}
