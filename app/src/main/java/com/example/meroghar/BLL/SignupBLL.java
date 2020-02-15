package com.example.meroghar.BLL;

import com.example.meroghar.Interfaces.UserApi;
import com.example.meroghar.Models.User;
import com.example.meroghar.ServerResponse.SignUpResponse;
import com.example.meroghar.URL.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SignupBLL {
    boolean isSuccess = false;

    public boolean login(String fullName, String address, String phone,  String email,String password, String profilePicture) {

        User user = new User(fullName,address,phone,email,password,profilePicture);

        UserApi usersAPI = Url.getInstance().create(UserApi.class);
        Call<SignUpResponse> usersCall = usersAPI.signup(user);

        try {
            Response<SignUpResponse> signupResponse = usersCall.execute();
            if (signupResponse.isSuccessful()) {

                Url.token += signupResponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
