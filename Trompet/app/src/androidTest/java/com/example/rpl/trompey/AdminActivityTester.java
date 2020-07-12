package com.example.rpl.trompey;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;



import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.action.ViewActions.click;
import static org.hamcrest.core.Is.is;

public class AdminActivityTester {
    Activity activity;


    @Rule
    public ActivityTestRule<MakananHewan> activityScenarioRule = new ActivityTestRule<>(MakananHewan.class);

    @Before
    public void initTest() {

        activity = activityScenarioRule.getActivity();
    }
    @Test
    public void AddObatEmptyImage(){
        onView(withId(R.id.et_namaObat)).perform(typeText("Obat Kucing"));
        onView(withId(R.id.et_hargaObat)).perform(typeText("Rp. 25.000"));
        onView(withId(R.id.et_deskripsiObat)).perform(scrollTo()).perform(typeText("Obat"));
        onView(withId(R.id.btn_upload)).perform(scrollTo()).perform(click());
        onView(withText("Belum ada image"))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }
    @Test
    public void AddObatEmptyDescription(){
        onView(withId(R.id.et_namaObat)).perform(typeText("Obat Kucing"));
        onView(withId(R.id.et_namaObat)).perform(typeText("Rp. 25.000"));
        onView(withId(R.id.btn_upload)).perform(scrollTo()).perform(click());
        onView(withText("Data belum Lengkap"))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void RecycleViewClick(){

        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new ViewAction() {
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
                View v = view.findViewById(R.id.beli);
                v.performClick();
            }
        }));
    }
}
