package com.sumit.dehaat;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.view.View;

import com.sumit.dehaat.view.activity.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class MainInstrumentedTest {

    private MainActivity mainActivity;

    public RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        mainActivity = activityTestRule.getActivity();

    }

    @Test
    public void testCaseSearchAuthorWithOrWithoutInput() throws Throwable {

        Thread.sleep(2000);

        final Matcher<View> recyclerViewAuthor = ViewMatchers.withId(R.id.recycler_view_author);

        Espresso.onView(withRecyclerView(R.id.recycler_view_author).atPositionOnView(0, R.id.tv_read_more)).perform(ViewActions.click());
        Thread.sleep(1000);
        Espresso.onView(withRecyclerView(R.id.recycler_view_author).atPositionOnView(0, R.id.tv_read_more)).perform(ViewActions.click());


        Thread.sleep(2000);

        Espresso.onView(recyclerViewAuthor).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        Thread.sleep(1000);
        Espresso.pressBack();

        Thread.sleep(2000);

        Espresso.onView(withRecyclerView(R.id.recycler_view_author).atPositionOnView(1, R.id.tv_read_more)).perform(ViewActions.click());
        Thread.sleep(1000);
        Espresso.onView(withRecyclerView(R.id.recycler_view_author).atPositionOnView(1, R.id.tv_read_more)).perform(ViewActions.click());


        Thread.sleep(2000);
        Espresso.onView(recyclerViewAuthor).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));

        Thread.sleep(1000);
        Espresso.pressBack();

        Thread.sleep(1000);
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Thread.sleep(2000);
        Espresso.onView(recyclerViewAuthor).perform(RecyclerViewActions.actionOnItemAtPosition(2, ViewActions.click()));

        Thread.sleep(1000);

        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Thread.sleep(3000);
    }

    @After
    public void tearDown() {
        mainActivity = null;
    }


    public class RecyclerViewMatcher {
        final int mRecyclerViewId;

        public RecyclerViewMatcher(int recyclerViewId) {
            this.mRecyclerViewId = recyclerViewId;
        }

        public Matcher<View> atPosition(final int position) {
            return atPositionOnView(position, -1);
        }

        public Matcher<View> atPositionOnView(final int position, final int targetViewId) {

            return new TypeSafeMatcher<View>() {
                Resources resources = null;
                View childView;

                public void describeTo(Description description) {
                    int id = targetViewId == -1 ? mRecyclerViewId : targetViewId;
                    String idDescription = Integer.toString(id);
                    if (this.resources != null) {
                        try {
                            idDescription = this.resources.getResourceName(id);
                        } catch (Resources.NotFoundException var4) {
                            idDescription = String.format("%s (resource name not found)", id);
                        }
                    }

                    description.appendText("with id: " + idDescription);
                }

                public boolean matchesSafely(View view) {

                    this.resources = view.getResources();

                    if (childView == null) {
                        RecyclerView recyclerView =
                                (RecyclerView) view.getRootView().findViewById(mRecyclerViewId);
                        if (recyclerView != null) {

                            childView = recyclerView.findViewHolderForAdapterPosition(position).itemView;
                        } else {
                            return false;
                        }
                    }

                    if (targetViewId == -1) {
                        return view == childView;
                    } else {
                        View targetView = childView.findViewById(targetViewId);
                        return view == targetView;
                    }

                }
            };
        }
    }
}
