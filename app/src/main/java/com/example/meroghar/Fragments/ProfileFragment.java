package com.example.meroghar.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meroghar.AddPropertyActivity;
import com.example.meroghar.DisplayPropertyActivity;
import com.example.meroghar.Interfaces.UserApi;
import com.example.meroghar.LoginActivity;
import com.example.meroghar.Models.User;
import com.example.meroghar.MyPropertyActivity;
import com.example.meroghar.ProfileEditActivity;
import com.example.meroghar.R;
import com.example.meroghar.URL.Url;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.meroghar.URL.Url.imagePath;
import static com.example.meroghar.URL.Url.token;


public class ProfileFragment extends Fragment {

    public static User globalUser;
    Button btnEditProfile, btnAddProperty, btnViewProperty, btnLogout;

    TextView tvemail, tvaddress;
    public CircleImageView tvuserProfilePic;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        btnAddProperty = v.findViewById(R.id.btnAddPropertyfromProfile);
        btnViewProperty= v.findViewById(R.id.btnViewProperty);
        btnEditProfile= v.findViewById(R.id.btnEditProfile);
        btnLogout= v.findViewById(R.id.btnLogout);

        tvemail = v.findViewById(R.id.userName);
        tvaddress= v.findViewById(R.id.userAddress);
        tvuserProfilePic= v.findViewById(R.id.myuserProfilePic);

        loadUser();

        tvuserProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"image:"+tvuserProfilePic,Toast.LENGTH_LONG).show();

            }
        });
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileEditActivity.class);
                startActivity(intent);
            }
        });
        btnAddProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddPropertyActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(token != "Bearer "){
                   Url.token ="Bearer ";
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    //intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

            }
        });
        btnViewProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyPropertyActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
    private void loadUser() {
        UserApi userAPI=Url.getInstance().create(UserApi.class);
        Call<User> userModelCall = userAPI.retrievUserdetail(token);

        userModelCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(),"Code"+response.code(),Toast.LENGTH_LONG).show();
                    return;
                }
                globalUser = response.body();

                String imagePath= Url.imagePath +response.body().getProfilePicture();
                Picasso.get().load(imagePath).into(tvuserProfilePic);
                String full_name=response.body().getFullName();
                String email =response.body().getEmail();
                String address =response.body().getAddress();
                String phone_number =response.body().getPhone();

                Toast.makeText(getContext(),"image:"+imagePath,Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(),"fullname:"+full_name,Toast.LENGTH_LONG).show();
                //name.setText(full_name);
                tvemail.setText(email);
                tvaddress.setText(address);
                //phone.setText(phone_number);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(),"Error"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });


    }

}
