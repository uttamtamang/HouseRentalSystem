package com.example.meroghar;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


public class LoginTest {
    @Rule
    public ActivityTestRule<LoginActivity> login= new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void TestLogin(){
         onView(withId(R.id.userEmail))
             .perform(typeText("uttam"))
             .perform(closeSoftKeyboard());

     onView(withId(R.id.userPassword))
             .perform(typeText("uttam"))
             .perform(closeSoftKeyboard());

     onView(withId(R.id.btnLogin))
             .perform(click());
    }
}

