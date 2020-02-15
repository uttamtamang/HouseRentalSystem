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
        wishlistList.add(new Wishlist("Beautiful house for sell", "100000", "Nayapaati", R.drawable.wishlist));
        wishlistList.add(new Wishlist("Beautiful flat for Rent", "125000", "Dillibazar", R.drawable.wishlist2));
    }
}
