package com.example.psbquiz.Activities;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;
import androidx.test.rule.ActivityTestRule;
import com.example.psbquiz.R;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class QuizCompleteActivityTest {

    @Rule
    public ActivityTestRule<QuizCompleteActivity> mActivityTestRule = new ActivityTestRule<QuizCompleteActivity>(QuizCompleteActivity.class);

    private QuizCompleteActivity mActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(HomeActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }



    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

    @Test
    public void Test_Title_TextView()
    {
        View view = mActivity.findViewById(R.id.textView15);
        assertNotNull(view);
    }

    @Test
    public void Test_Current_score()
    {
        View view = mActivity.findViewById(R.id.scores);
        assertNotNull(view);
    }

    @Test
    public void Test_listview()
    {
        View view = mActivity.findViewById(R.id.listView);
        assertNotNull(view);
    }

    @Test
    public void Test_1st_score()
    {
        View view = mActivity.findViewById(R.id.f_scores);
        assertNotNull(view);
    }

    @Test
    public void Test_2nd_score()
    {
        View view = mActivity.findViewById(R.id.s_scores);
        assertNotNull(view);
    }

    @Test
    public void Test_3rd_score()
    {
        View view = mActivity.findViewById(R.id.t_scores);
        assertNotNull(view);
    }

    @Test
    public void Test_Complete_done_Btn()
    {
        assertNotNull(mActivity.findViewById(R.id.completed_doneButton));

        onView(withId(R.id.completed_doneButton)).perform(click());

        Activity HomeActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);

        assertNotNull(HomeActivity);

        HomeActivity.finish();
    }
}