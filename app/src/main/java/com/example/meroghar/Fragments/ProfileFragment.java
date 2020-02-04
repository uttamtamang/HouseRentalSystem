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

import com.example.meroghar.AddPropertyActivity;
import com.example.meroghar.LoginActivity;
import com.example.meroghar.R;

import static com.example.meroghar.URL.Url.token;


public class ProfileFragment extends Fragment {

    Button btnEditProfile, btnAddProperty, btnViewProperty, btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        btnAddProperty = v.findViewById(R.id.btnAddPropertyfromProfile);
        btnViewProperty= v.findViewById(R.id.btnViewProperty);
        btnEditProfile= v.findViewById(R.id.btnEditProfile);
        btnLogout= v.findViewById(R.id.btnLogout);

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
                if(token != "Bearer"){
                    token ="Bearer";
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
        return v;
    }
}
