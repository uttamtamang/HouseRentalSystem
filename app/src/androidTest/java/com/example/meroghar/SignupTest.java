package com.example.meroghar;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class SignupTest {
    @Rule
    public ActivityTestRule<RegistrationActivity> RegistrationTestRule=
            new ActivityTestRule<>(RegistrationActivity.class);

 @Test
    public void TestSignup() {
     onView(withId(R.id.userEmail))
             .perform(typeText("uttam tamang"))
             .perform(closeSoftKeyboard());

     onView(withId(R.id.userName))
             .perform(typeText("uttamtamang@gmail.com"))
             .perform(closeSoftKeyboard());

     onView(withId(R.id.userRPassword))
             .perform(typeText("tamang"))
             .perform(closeSoftKeyboard());

     onView(withId(R.id.btnRegister))
             .perform(click());
 }
}
