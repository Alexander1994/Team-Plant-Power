package com.teamplantpower.team_plant_power;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.teamplantpower.activities.MainActivity;
import com.teamplantpower.team_plant_power.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)

/**
 * Requires Messages & Users references in DB to be empty
 */
public class MessageBoardUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void messageBoardUITest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        nap();

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.messageBoardButton), withText("Message Board"), isDisplayed()));
        nap();

        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        nap();

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.EnterName), isDisplayed()));
        nap();

        appCompatEditText.perform(replaceText("John"), closeSoftKeyboard());

        nap();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.Submit), isDisplayed()));
        nap();

        appCompatButton2.perform(click());

        nap();

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.EnterName), isDisplayed()));
        nap();

        appCompatEditText2.perform(replaceText("Hello world!"), closeSoftKeyboard());

        nap();

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.Submit), isDisplayed()));
        nap();

        appCompatButton3.perform(click());

        nap();

        ViewInteraction textView = onView(
                allOf(withId(R.id.Name), isDisplayed()));

        nap();
        nap();

        textView.check(matches(withText("John")));

        nap();

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.EnterName), isDisplayed()));
        nap();
        nap();

        textView2.check(matches(withText("Hello world!")));

    }
    private void nap() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void nap(int d) {
        try {
            Thread.sleep(d);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
