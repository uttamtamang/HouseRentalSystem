package com.example.meroghar.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.meroghar.Adapters.PropertyAdapter;
import com.example.meroghar.AddPropertyActivity;
import com.example.meroghar.Interfaces.PropertyApi;
import com.example.meroghar.Models.Property;
import com.example.meroghar.R;
import com.example.meroghar.URL.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PropertiesFragment extends Fragment {

    RecyclerView propertyRecyclerView;
    PropertyAdapter adapter;
    List<Property> propertyList;
   // public static List<>


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_properties, container, false);

        propertyRecyclerView = v.findViewById(R.id.propertyRecyclerView1);

        loadProperties();

        return v;

    }

    private void loadProperties() {
        PropertyApi propertyApi = Url.getInstance().create(PropertyApi.class);
        Call<List<Property>> list = propertyApi.getProperty();

        list.enqueue(new Callback<List<Property>>() {
            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "error" + response.body(), Toast.LENGTH_LONG).show();
                    return;
                }
                propertyList = response.body();

                adapter = new PropertyAdapter(propertyList, getContext());
                propertyRecyclerView.setAdapter(adapter);
                propertyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<Property>> call, Throwable t) {
                Toast.makeText(getContext(), "error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
