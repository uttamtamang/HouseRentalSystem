package com.example.meroghar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meroghar.Fragments.ProfileFragment;
import com.example.meroghar.Interfaces.UserApi;
import com.example.meroghar.Models.User;
import com.example.meroghar.URL.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileEditActivity extends AppCompatActivity {

    Button btnProfileUpdate;
    EditText etFullName, etPhone, etAddress, etEmail;
    private static final String TAG = "UpdateProfile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        //SETTING TITLE ON ACTION BAR
        getSupportActionBar().setTitle("User profile update form");

        etFullName = findViewById(R.id.userNameUpdate);
        etAddress = findViewById(R.id.userAddressUpdate);
        etPhone = findViewById(R.id.userPhoneUpdate);
        etEmail = findViewById(R.id.userEmailUpdate);

        btnProfileUpdate = findViewById(R.id.btnUpdateProfile);

        //SET USER DATA
        etFullName.setText(ProfileFragment.globalUser.getFullName());
        etAddress.setText(ProfileFragment.globalUser.getAddress());
        etPhone.setText(ProfileFragment.globalUser.getPhone());
        etEmail.setText(ProfileFragment.globalUser.getEmail());

        etEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileEditActivity.this, "Sorry, you cannot update your email.", Toast.LENGTH_SHORT).show();
            }
        });

        btnProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();
            }
        });


    }

    private void updateUserProfile(){
        String userFullName = etFullName.getText().toString();
        String userAddress = etAddress.getText().toString();
        String userPhone= etPhone.getText().toString();

        User user = new User(userFullName, userAddress, userPhone);
        try{
            UserApi userApi = Url.getInstance().create(UserApi.class);
            Call<User> userCall = userApi.updateUserProfile(Url.token,user);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(ProfileEditActivity.this,
                            "Profile update Success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileEditActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                    Toast.makeText(ProfileEditActivity.this, "Failed to update!", Toast.LENGTH_SHORT).show();
                }
            });
        }catch(Exception e){
            Log.e(TAG, "onCreateView: " + e.getLocalizedMessage(), e);
        }
    }
}
