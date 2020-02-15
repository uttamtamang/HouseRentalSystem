package com.example.meroghar;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ValidationTest {

    @Test
    public void EmptyValidationTest(){
        RegistrationActivity registrationActivity = new RegistrationActivity();
        boolean result = registrationActivity.EmptyValidation("", "", "", "" , "", "");
       assertEquals(false, result);

    }
}
