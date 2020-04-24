package com.example.rpl.trompey;

import android.app.Activity;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.Is.is;

public class AdminActivityTester {
    Activity activity;
    @Rule
    public ActivityTestRule<AddObatHewan> activityScenarioRule = new ActivityTestRule<>(AddObatHewan.class);

    @Before
    public void initTest() {
        activity = activityScenarioRule.getActivity();
    }
    @Test
    public void klikData(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.list_admin))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return null;
                    }

                    @Override
                    public String getDescription() {
                        return null;
                    }

                    @Override
                    public void perform(UiController uiController, View view) {

                        View card = view.findViewById(R.id.list_item_admin);
                        card.performClick();
                    }
                }));
    }

    @Test
    public void addDataNoComplate(){

        onView(withId(R.id.et_namaObat)).perform(typeText("Obat Kucing"));
        onView(withId(R.id.et_namaObat)).perform(typeText("Rp. 25.000"));
        onView(withId(R.id.btn_upload)).perform(scrollTo()).perform(click());
        onView(withText("Data belum Lengkap"))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

}
