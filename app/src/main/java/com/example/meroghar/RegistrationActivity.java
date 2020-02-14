package com.example.meroghar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meroghar.Interfaces.UserApi;
import com.example.meroghar.Models.User;
import com.example.meroghar.ServerResponse.ImageResponse;
import com.example.meroghar.ServerResponse.SignUpResponse;
import com.example.meroghar.URL.Url;
import com.example.meroghar.strictmode.StrictModeClass;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etFullName, etPhone, etAddress, etEmail, etPassword, etConfirmPassword;
    private Button Register, backLogin;
    private CircleImageView imgProfile;
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

        imgProfile = findViewById(R.id.imgProfile);
        etFullName= findViewById(R.id.userName);
        etPhone= findViewById(R.id.userPhone);
        etAddress= findViewById(R.id.userAddress);
        etEmail = findViewById(R.id.userEmail);
        etPassword = findViewById(R.id.userRPassword);
        etConfirmPassword = findViewById(R.id.userConfirmPassword);

        Register = findViewById(R.id.btnRegister);
        //backLogin= findViewById(R.id.btnBackToLogin);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPassword.getText().toString().equals(etConfirmPassword.getText().toString())){
                    if(validate() && EmptyValidation()){

                        saveImage();
                         //EmptyValidation();
                        signup();

                    }
                }
                else{
                    Toast.makeText(RegistrationActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }

            }
        });

    }

    //to test
    private boolean validate(){
        boolean status= true;

        if(etEmail.getText().toString().length() < 6 ){
            etEmail.setError("Please enter validate email");
            status = false;
        }
        return status;
    }

    private void BrowseImage(){

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_OK){
            if(data == null){
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();

            }
        }
        Uri uri = data.getData();
        imgProfile.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri){

        String[] projection ={MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null,null,null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveImage(){
        File file=new File(imagePath);
        RequestBody requestBody= RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body=MultipartBody.Part.createFormData("imageFile",file.getName(),requestBody);


        UserApi userApi=Url.getInstance().create(UserApi.class);
        Call<ImageResponse> imageResponseCall=userApi.uploadImage(body);

        StrictModeClass.StrictMode();

        try{

            Response<ImageResponse> imageResponseResponse = imageResponseCall.execute();
            profilePicture = imageResponseResponse.body().getFilename();
            Toast.makeText(this,"Profile Updated"+profilePicture,Toast.LENGTH_LONG).show();

        }catch (IOException e){
            Toast.makeText(this,"Error"+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


    }

    public void signup(){
        String fullname, address, phone, email, password, cpassword;
        fullname= etFullName.getText().toString();
        address= etAddress.getText().toString();
        phone= etPhone.getText().toString();
        email= etEmail.getText().toString();
        password= etPassword.getText().toString();
        //cpassword= etConfirmPassword.getText().toString();
            User user = new User(fullname, address, phone, email, password, profilePicture);
            UserApi userApi = Url.getInstance().create(UserApi.class);
            Call<SignUpResponse> signup = userApi.signup(user);

            signup.enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(RegistrationActivity.this, "Code: " +response.body(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(RegistrationActivity.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {
                    Toast.makeText(RegistrationActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            });

    }

    public boolean EmptyValidation(){
        if(etFullName.getText().toString().trim().isEmpty()){
            etFullName.setError("Enter your full name please..");
            getCurrentFocus();
            return false;
        }
         if(etPhone.getText().toString().trim().isEmpty()){
            etPhone.setError("Enter your phone number please..");
            getCurrentFocus();
            return false;
        }
          if(etEmail.getText().toString().trim().isEmpty()){
            etEmail.setError("Enter your email or username please..");
            getCurrentFocus();
            return false;
        }
           if(etAddress.getText().toString().trim().isEmpty()){
            etAddress.setError("Enter your Address please..");
            getCurrentFocus();
            return false;
        }
            if(etPassword.getText().toString().trim().isEmpty()){
            etPassword.setError("Enter your password please..");
            getCurrentFocus();
            return false;
        }
             if(etConfirmPassword.getText().toString().trim().isEmpty()){
            etConfirmPassword.setError("confirm your password please..");
            getCurrentFocus();
            return false;
        }
        return true;
    }

}
