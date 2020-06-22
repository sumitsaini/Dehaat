package com.sumit.dehaat;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.View;

import com.sumit.dehaat.view.activity.LoginActivity;
import com.sumit.dehaat.view.activity.MainActivity;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginInstrumentedTest {

    private LoginActivity loginActivity;
    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    Instrumentation.ActivityMonitor activityMonitor = InstrumentationRegistry.getInstrumentation().addMonitor(MainInstrumentedTest.class.getName(), null, false);

    @Before
    public void setUp() {
        loginActivity = activityTestRule.getActivity();

    }

    @Test
    public void testCase_loginWithOutEnterAnything() throws Throwable {

        final Matcher<View> etSearch = ViewMatchers.withId(R.id.et_email);
        final Matcher<View> etPassword = ViewMatchers.withId(R.id.et_password);
        final Matcher<View> btnLogin = ViewMatchers.withId(R.id.btn_login);

        // check with out entering anything in email edit text
        Espresso.onView(btnLogin).perform(ViewActions.click());

        Thread.sleep(2000);

        // check after enter some data in edit text and perform click
        Espresso.onView(etSearch).perform(ViewActions.replaceText("sumit.saini"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(btnLogin).perform(ViewActions.click());


        Thread.sleep(2000);

        // check after enter some data in edit text and perform click
        Espresso.onView(etPassword).perform(ViewActions.typeText("cityslicka"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(btnLogin).perform(ViewActions.click());


        Thread.sleep(2000);

        // check after enter some data in edit text and perform click
        Espresso.onView(etSearch).perform(ViewActions.replaceText("sumit.saini@gmail"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(btnLogin).perform(ViewActions.click());


        Thread.sleep(2000);


        // check after enter some data in edit text and perform click
        Espresso.onView(etSearch).perform(ViewActions.replaceText("sumit.saini@gmail.com"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(btnLogin).perform(ViewActions.click());


        Thread.sleep(4000);

        // check after enter some data in edit text and perform click
        Espresso.onView(etSearch).perform(ViewActions.replaceText("eve.holt@reqres.in"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(btnLogin).perform(ViewActions.click());


        //Thread.sleep(4000);


        Activity activity = InstrumentationRegistry.getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 3000);
       // Assert.assertNotNull(activity);

       // loginActivity.startActivity(activity.getIntent());
       // loginActivity.finish();

    }

    @After
    public void tearDown() {
        loginActivity = null;
    }

}
