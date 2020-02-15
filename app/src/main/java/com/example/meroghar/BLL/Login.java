package com.example.meroghar.BLL;

import com.example.meroghar.Interfaces.UserApi;
import com.example.meroghar.Models.User;
import com.example.meroghar.ServerResponse.SignUpResponse;
import com.example.meroghar.URL.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class Login {
     boolean isSuccess = false;

    public boolean checkUser(String email, String password) {

         User user = new User(email, password);
        UserApi usersAPI = Url.getInstance().create(UserApi.class);
        Call<SignUpResponse> usersCall = usersAPI.login(user);


        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful()) {

                Url.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
