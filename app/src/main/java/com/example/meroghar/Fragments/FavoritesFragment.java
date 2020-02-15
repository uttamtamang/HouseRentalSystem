package com.example.meroghar.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meroghar.Adapters.WishlistAdapter;
import com.example.meroghar.DashboardActivity;
import com.example.meroghar.MainActivity;
import com.example.meroghar.R;

public class FavoritesFragment extends Fragment {
    RecyclerView favPropertyRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);


        favPropertyRecyclerView= v.findViewById(R.id.favPropertyRecyclerView);
        WishlistAdapter wishlistAdapter = new WishlistAdapter(getContext(), DashboardActivity.wishlistList);
        favPropertyRecyclerView.setAdapter(wishlistAdapter);
        favPropertyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

}
