package com.example.meroghar.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.meroghar.AddPropertyActivity;
import com.example.meroghar.R;

import java.util.List;


public class PropertiesFragment extends Fragment {

    RecyclerView propertyRecyclerView;

   // public static List<>


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_properties, container, false);

        propertyRecyclerView = v.findViewById(R.id.propertyRecyclerView);





        return v;

    }
}
