package com.example.meroghar;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class EmailValidationTest {
    @Test
    public void EmailTest(){
        RegistrationActivity registrationActivity = new RegistrationActivity();
        boolean result = registrationActivity.emailValidation("uttmtamang.com");
        assertEquals(false, result);
    }

    @Test
    public void emailLengthTest(){
    RegistrationActivity registrationActivity = new RegistrationActivity();
        boolean result = registrationActivity.validateEmail("uttmtamang.com");
        assertEquals(true, result);
    }

}
