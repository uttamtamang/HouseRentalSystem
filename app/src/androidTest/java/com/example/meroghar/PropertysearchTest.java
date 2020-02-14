package com.example.meroghar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.meroghar.Fragments.PropertiesFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class PropertysearchTest {

//   @RunWith(AndroidJUnit4::class)
//class MyTestSuite {
//    @Test  testEventFragment() {
//        val factory = MyFragmentFactory()
//        val scenario = launchFragmentInContainer<MyFragment>(
//                fragmentArgs, factory)
//        onView(withId(R.id.text)).check(matches(withText("Hello World!")))
//    }
//}
}
