package com.example.meroghar;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


public class UpdatePropertytTest {
     @Rule
    public ActivityTestRule<UpdatePropertyActivity> addProperty = new ActivityTestRule<>(UpdatePropertyActivity.class);

    @Test
    public void addProperty(){
         onView(withId(R.id.update_propertyTitle))
             .perform(typeText("Beautiful House for sell"))
             .perform(closeSoftKeyboard());

          onView(withId(R.id.update_propertyPrice))
             .perform(typeText("1500000"))
             .perform(closeSoftKeyboard());

        onView(withId(R.id.update_propertyAddress))
             .perform(typeText("Nayapaati"))
             .perform(closeSoftKeyboard());

    }
}
