package com.example.meroghar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meroghar.Interfaces.UserApi;
import com.example.meroghar.Models.User;
import com.example.meroghar.URL.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etFullName, etPhone, etAddress, etEmail, etPassword, etConfirmPassword;
    private Button Register, backLogin;

    String imagePath;
    private String profilePicture="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //This will hide your TITLE BAR
        getSupportActionBar().hide();

        //THIS WILL MAKE YOUR STATUS BAR TRANSPARENT
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );

        etFullName= findViewById(R.id.userName);
        etPhone= findViewById(R.id.userPhone);
        etAddress= findViewById(R.id.userAddress);
        etEmail = findViewById(R.id.userEmail);
        etPassword = findViewById(R.id.userRPassword);
        etConfirmPassword = findViewById(R.id.userConfirmPassword);

        Register = findViewById(R.id.btnRegister);
        backLogin= findViewById(R.id.btnBackToLogin);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPassword.getText().toString().equals(etConfirmPassword.getText().toString())){
                    signup();
                }
                else{
                    Toast.makeText(RegistrationActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }

            }
        });

        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
    public void signup(){
        String fullname, address, phone, email, password, cpassword;
        fullname= etFullName.getText().toString();
        address= etAddress.getText().toString();
        phone= etPhone.getText().toString();
        email= etEmail.getText().toString();
        password= etPassword.getText().toString();
        //cpassword= etConfirmPassword.getText().toString();


            User user = new User(fullname, address, phone, email, password);
            UserApi userApi = Url.getInstance().create(UserApi.class);
            Call<Void> signup = userApi.signup(user);

            signup.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(RegistrationActivity.this, "Code: " +response.body(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(RegistrationActivity.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(RegistrationActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            });




    }

}
