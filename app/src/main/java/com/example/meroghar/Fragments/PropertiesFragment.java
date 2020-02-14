package com.example.meroghar.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meroghar.Adapters.PropertyAdapter;
import com.example.meroghar.AddPropertyActivity;
import com.example.meroghar.Interfaces.PropertyApi;
import com.example.meroghar.Models.Property;
import com.example.meroghar.R;
import com.example.meroghar.URL.Url;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PropertiesFragment extends Fragment {

    RecyclerView propertyRecyclerView;
    PropertyAdapter adapter;
    List<Property> propertyList;
   // public static List<>

    EditText btnSearchByAddress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_properties, container, false);

        propertyRecyclerView = v.findViewById(R.id.propertyRecyclerView1);
        btnSearchByAddress = v.findViewById(R.id.etPropertySearch);

        loadProperties();
        
        btnSearchByAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        
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
    
    //Search Address 
    private void filter(String text){
        ArrayList<Property> filteredList = new ArrayList<>();
        for(Property item: propertyList){
            if( item.getAddress().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapter.FilterAddress(filteredList);
    }
}
