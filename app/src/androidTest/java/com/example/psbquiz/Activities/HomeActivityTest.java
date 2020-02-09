package com.example.psbquiz.Activities;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;

import com.example.psbquiz.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<HomeActivity>(HomeActivity.class);

    private HomeActivity mActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(HomeActivity.class.getName(),null,false);

    Instrumentation.ActivityMonitor monitor1 = getInstrumentation().addMonitor(LoginActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

    @Test
    public void Test_Image_View()
    {
        View view = mActivity.findViewById(R.id.imageView3);
        assertNotNull(view);
    }

    @Test
    public void Test_TextView()
    {
        View view = mActivity.findViewById(R.id.textView);
        View view1 = mActivity.findViewById(R.id.textView2);
        assertNotNull(view);
        assertNotNull(view1);
    }

    @Test
    public void Test_nameText()
    {
        View view = mActivity.findViewById(R.id.nameText);
        assertNotNull(view);
    }

    @Test
    public void Test_courseName()
    {
        View view = mActivity.findViewById(R.id.courseText);
        assertNotNull(view);
    }



    @Test
    public void Test_start_Btn()
    {
        assertNotNull(mActivity.findViewById(R.id.quizButton));
        onView(withId(R.id.quizButton)).perform(click());
        Activity HomeActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(HomeActivity);
        HomeActivity.finish();
    }

    @Test
    public void Test_logout()
    {
        assertNotNull(mActivity.findViewById(R.id.logoutButton));
        onView(withId(R.id.logoutButton)).perform(click());
        Activity LoginActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(LoginActivity);
        LoginActivity.finish();

    }


}