package com.example.meroghar;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


public class AddPropertyTest {

    @Rule
    public ActivityTestRule<AddPropertyActivity> addProperty = new ActivityTestRule<>(AddPropertyActivity.class);

    @Test
    public void addProperty(){
         onView(withId(R.id.propertyTitle))
             .perform(typeText("Beautiful House for sell"))
             .perform(closeSoftKeyboard());

          onView(withId(R.id.propertyPrice))
             .perform(typeText("1500000"))
             .perform(closeSoftKeyboard());

    }

}
