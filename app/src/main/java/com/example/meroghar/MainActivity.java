package com.example.meroghar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.meroghar.Models.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Wishlist> wishlistList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wishlistList = new ArrayList<>();
        wishlistList.add(new Wishlist("home for sell", "12000", "queens", R.drawable.wishlist));
        wishlistList.add(new Wishlist("get your dream house", "15000", "dillibazar", R.drawable.wishlist2));
    }
}
