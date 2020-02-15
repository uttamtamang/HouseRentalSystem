package com.example.meroghar;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class PropertyTest {

    @Test
    public void TestCost(){
        DisplayPropertyActivity displayPropertyActivity = new DisplayPropertyActivity();
        int result = displayPropertyActivity.TotalRentCost(1000, 13);
        assertEquals(1130, result);
    }
}
