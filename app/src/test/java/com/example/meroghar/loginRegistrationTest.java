package com.example.meroghar;

import com.example.meroghar.BLL.Login;
import com.example.meroghar.BLL.SignupBLL;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class loginRegistrationTest {
        @Test

    public void testlogin() {
            Login loginBLL = new Login();
            boolean result = loginBLL.checkUser("thor123", "thor123");
            assertEquals(true,result);
        }
        @Test
    public  void testsignup(){
        SignupBLL signupBLL = new SignupBLL();
        boolean result = signupBLL.login("Uttam Tamang","Nuwakot","9818831286",
                "uttamtamang45@gmail.com","uttam123","uttam.jpeg");
        assertEquals(true,result);
    }

}

