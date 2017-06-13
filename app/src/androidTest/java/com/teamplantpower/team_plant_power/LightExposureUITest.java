package com.teamplantpower.team_plant_power;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
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
public class LightExposureUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void lightExposureUITest() {

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.lightButton), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText1 = onView(
                allOf(withId(R.id.setMinLight), isDisplayed()));
        appCompatEditText1.perform(replaceText("0.000001"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.setMin_MaxLight), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.message), isDisplayed()));
        textView.check(matches(withText("Light Exposure in Greenhouse OK")));

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.setMinLight), isDisplayed()));
        appCompatEditText2.perform(replaceText("100.00000"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.setMin_MaxLight), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.message), isDisplayed()));
        textView2.check(matches(withText("WARNING! Light Exposure in Greenhouse NOT OK!")));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.setMinLight), isDisplayed()));
        appCompatEditText3.perform(replaceText("101"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.setMin_MaxLight), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.message), isDisplayed()));
        textView3.check(matches(withText("Light Exposure in Greenhouse OK")));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.setMinLight), isDisplayed()));
        appCompatEditText4.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.setMinLight), isDisplayed()));
        appCompatEditText5.perform(replaceText("60"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.setMaxLight), isDisplayed()));
        appCompatEditText6.perform(replaceText("40.2"), closeSoftKeyboard());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.setMin_MaxLight), isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.message), isDisplayed()));
        textView4.check(matches(withText("Light Exposure in Greenhouse OK")));

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
