package com.example.meroghar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.meroghar.Adapters.MyPropertyAdapter;
import com.example.meroghar.Interfaces.PropertyApi;
import com.example.meroghar.Models.Property;
import com.example.meroghar.URL.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPropertyActivity extends AppCompatActivity {
    RecyclerView propertyRecyclerView;
    MyPropertyAdapter adapter;
    List<Property> propertyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_property);

        propertyRecyclerView= findViewById(R.id.myPropertyList);
        getSupportActionBar().setTitle("My properties");

        loadProperties();
    }

    private void loadProperties() {
        PropertyApi propertyApi = Url.getInstance().create(PropertyApi.class);
        Call<List<Property>> list = propertyApi.getMyProperty(Url.token);

        list.enqueue(new Callback<List<Property>>() {
            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MyPropertyActivity.this, "error" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                propertyList = response.body();

                adapter = new MyPropertyAdapter(propertyList, MyPropertyActivity.this);
                propertyRecyclerView.setAdapter(adapter);
                propertyRecyclerView.setLayoutManager(new LinearLayoutManager(MyPropertyActivity.this));
            }

            @Override
            public void onFailure(Call<List<Property>> call, Throwable t) {
                Toast.makeText(MyPropertyActivity.this, "error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
