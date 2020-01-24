package com.example.meroghar;

import androidx.annotation.BinderThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;

//import butterknife.BindView;
import eightbitlab.com.blurview.BlurView;

public class LoginActivity extends AppCompatActivity {
    EditText userName, password;
    Button btnLogin,btnSignup;
    TextView registerNow;

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

        //THIS WILL MAKE YOUR STATUS BAR TRANSPARENT
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }


        btnLogin= findViewById(R.id.btnLogin);
        registerNow = findViewById(R.id.linkRegistration);
        btnSignup= findViewById(R.id.btnSignup);

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

//        final float radius = 25f;
//        final Drawable windowBackground = getWindow().getDecorView().getBackground();
//        blur_email.setupWith(root)
//                .setFrameClearDrawable(windowBackground)
//                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
//                .setBlurRadius(radius)
//                .setHasFixedTransformationMatrix(true);

    }
}
