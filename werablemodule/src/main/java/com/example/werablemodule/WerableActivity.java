package com.example.werablemodule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.werablemodule.Interfaces.UserApi;
import com.example.werablemodule.Models.User;
import com.example.werablemodule.ServerResponse.SignUpResponse;
import com.example.werablemodule.URL.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WerableActivity extends WearableActivity {

    private TextView mTextView;
    EditText userEmail, userPassword;
    Button btnLogin,btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_werable);

        mTextView = (TextView) findViewById(R.id.text);

        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);

        btnLogin= findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // Enables Always-on
        setAmbientEnabled();
    }
    public void login(){
        final String email, password;

        email= userEmail.getText().toString();
        password= userPassword.getText().toString();

        User user = new User(email, password);
        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<SignUpResponse> login = userApi.login(user);

        login.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Either Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Url.token += response.body().getToken();

                    openDashBoard();
                }

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error " + t.getLocalizedMessage() , Toast.LENGTH_SHORT).show();

            }

        });


    }
    public void openDashBoard(){
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}
