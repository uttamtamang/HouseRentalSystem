package com.example.meroghar;

import androidx.annotation.BinderThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;
import com.example.meroghar.Broadcast.BroadCastReceiver;
import com.example.meroghar.Interfaces.UserApi;
import com.example.meroghar.Models.User;
import com.example.meroghar.ServerResponse.SignUpResponse;
import com.example.meroghar.URL.Url;
import com.example.meroghar.createchannel.CreateChannel;

//import butterknife.BindView;
import eightbitlab.com.blurview.BlurView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.meroghar.URL.Url.token;

public class LoginActivity extends AppCompatActivity {
    EditText userEmail, userPassword;
    Button btnLogin,btnSignup;
    TextView registerNow;
    NotificationManagerCompat notificationManagerCompat;

    private CheckBox cbRem;
    SharedPreferences rememberMe;

//    @BindView(R.id.root)
//    ViewGroup root;
//    @BindView(R.id.userEmail)
//    BlurView blur_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //This will hide your TITLE BAR
        getSupportActionBar().hide();


//Notifier
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();

        //THIS WILL MAKE YOUR STATUS BAR TRANSPARENT
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
//            getWindow().setFlags(
//                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//            );
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }

        rememberMe = getSharedPreferences("User", Context.MODE_PRIVATE);

        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);

        cbRem= findViewById(R.id.rememberMe);

        btnLogin= findViewById(R.id.btnLogin);
        registerNow = findViewById(R.id.linkRegistration);
        btnSignup= findViewById(R.id.btnSignup);

        if( rememberMe.getString("email", "").isEmpty()){
            cbRem.setChecked(false);
        }
        else{
            userEmail.setText(rememberMe.getString("email",""));
            userPassword.setText(rememberMe.getString("password",""));
            cbRem.setChecked(true);

        }

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


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
                if(cbRem.isChecked()){
                    SharedPreferences.Editor editor = rememberMe.edit();

                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.commit();
                }
                else {
                    rememberMe.edit().clear().commit();
                }
                if(!response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                //Toast.makeText(LoginActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                token += response.body().getToken();
                //Toast.makeText(LoginActivity.this, "Token " + response.body().getToken(), Toast.LENGTH_SHORT).show();
                    notifiy();
                    saveid();
                openDashBoard();
                }

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error " + t.getLocalizedMessage() , Toast.LENGTH_SHORT).show();

            }

        });


    }
    private void saveid(){
        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<User> userCall = userApi.retrievUserdetail(token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Url.id = response.body().get_id();
                Toast.makeText(LoginActivity.this, "Id " +  response.body().get_id(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error " + t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openDashBoard(){
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(intent);
    }

    //Nofify Function
    private void notifiy() {
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_home_black_24dp)
                .setContentTitle("Mero Ghar")
                .setContentText("Login success Boss " + userEmail.getText().toString())
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }
//doubt
    BroadCastReceiver broadCastReceiver= new BroadCastReceiver(this);

    protected void onStart(){
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadCastReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadCastReceiver);
    }
}
