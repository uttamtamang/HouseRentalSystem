package com.example.meroghar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meroghar.Interfaces.PropertyApi;
import com.example.meroghar.Interfaces.UserApi;
import com.example.meroghar.Models.Property;
import com.example.meroghar.ServerResponse.IdResponse;
import com.example.meroghar.URL.Url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePropertyActivity extends AppCompatActivity {
       private EditText tvTitle, tvAddress, tvPrice, tvDesc, tvFacility1, tvFacility2,
            tvFacility3, tvFacility4, tvBedroom, tvKitchen, tvLivingroom, tvBatroom, tvOwnerName, tvOwnerAddress,
            tvOwnerEmail, etpropertyId;
    private Button btnUpdate, btnDelete;
    private ImageView propertyImage;
    Spinner tvCategory, tvPurpose;

    private static final String TAG = "update property";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_property);

        getSupportActionBar().setTitle("Update property");

        etpropertyId= findViewById(R.id.update_propertyId);
         tvTitle = findViewById(R.id.update_propertyTitle);
        tvAddress =findViewById(R.id.update_propertyAddress);
        tvCategory = findViewById(R.id.update_propertyCategorySpinner);
        tvPurpose = findViewById(R.id.update_propertyPurposeSpinner);
        tvPrice = findViewById(R.id.update_propertyPrice);
        tvDesc = findViewById(R.id.update_propertyDescription);

        tvFacility1= findViewById(R.id.update_facility1);
        tvFacility2= findViewById(R.id.update_facility2);
        tvFacility3= findViewById(R.id.update_facility3);
        tvFacility4= findViewById(R.id.update_facility4);

        tvBedroom = findViewById(R.id.update_bedRoom);
        tvKitchen = findViewById(R.id.update_Kitchen);
        tvLivingroom = findViewById(R.id.update_livingRoom);
        tvBatroom = findViewById(R.id.update_bathroom);

        propertyImage= findViewById(R.id.update_propertyImage);
        etpropertyId.setVisibility(View.GONE);

        //setting buttons
        btnDelete= findViewById(R.id.btnDeleteMyProperty);
        btnUpdate= findViewById(R.id.btnUpdateMyProperty);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProperty();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProperty();
            }
        });
        //Getting values from MyPropertyAdapter
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            etpropertyId.setText(bundle.getString("proId"));

            tvTitle.setText(bundle.getString("proTitle"));
            String image = bundle.getString("proImage");
            Picasso.get().load(Url.imagePath + image).into(propertyImage);
            tvAddress.setText(bundle.getString("proAddress"));
//            tvCategory.setText(bundle.getString("proCategory"));
//            tvPurpose.setText(bundle.getString("proPurpose"));
            tvPrice.setText(bundle.getString("proPrice"));
            tvDesc.setText(bundle.getString("proDesc"));

            tvFacility1.setText(bundle.getString("proF1"));
            tvFacility2.setText(bundle.getString("proF2"));
            tvFacility3.setText(bundle.getString("proF3"));
            tvFacility4.setText(bundle.getString("proF4"));

            tvBedroom.setText(bundle.getString("proBed"));
            tvKitchen.setText(bundle.getString("proKitchen"));
            tvLivingroom.setText(bundle.getString("proLiving"));
            tvBatroom.setText(bundle.getString("proBath"));

        }
    }

    private void updateProperty(){
//        String title = tvTitle.getText().toString();
//        String desc = tvDesc.getText().toString();
//        String price = tvPrice.getText().toString();
//        String address = tvAddress.getText().toString();
//        Spinner category = tvCategory.getSelectedItem().toString()
        Property property = new Property(tvTitle.getText().toString(), tvAddress.getText().toString(),tvCategory.getSelectedItem().toString(),
                tvPurpose.getSelectedItem().toString(),tvDesc.getText().toString(), tvPrice.getText().toString(),
                 tvFacility1.getText().toString(),tvFacility2.getText().toString(),
                tvFacility3.getText().toString(), tvFacility4.getText().toString(),tvKitchen.getText().toString(),
                tvBedroom.getText().toString(), tvLivingroom.getText().toString(), tvBatroom.getText().toString());

        try {
            String id = etpropertyId.getText().toString();
            PropertyApi propertyApi = Url.getInstance().create(PropertyApi.class);
            Call<Property> propertyCall = propertyApi.updateProperty(Url.token, id, property);
            propertyCall.enqueue(new Callback<Property>() {
                @Override
                public void onResponse(Call<Property> call, Response<Property> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(UpdatePropertyActivity.this, "error" + response.body(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(UpdatePropertyActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(UpdatePropertyActivity.this, DashboardActivity.class);
//                    startActivity(intent);

                }

                @Override
                public void onFailure(Call<Property> call, Throwable t) {
                    Toast.makeText(UpdatePropertyActivity.this, "error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }catch(Exception e){
            Log.e(TAG, "onCreateView: " + e.getLocalizedMessage(), e);
        }

    }

    private void deleteProperty(){

            try {
            String id = etpropertyId.getText().toString();
            PropertyApi propertyApi = Url.getInstance().create(PropertyApi.class);
            Call<Property> propertyCall = propertyApi.deleteProperty(Url.token, id);
            propertyCall.enqueue(new Callback<Property>() {
                @Override
                public void onResponse(Call<Property> call, Response<Property> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(UpdatePropertyActivity.this, "error" + response.body(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(UpdatePropertyActivity.this, "Property deleted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdatePropertyActivity.this, DashboardActivity.class);
                    startActivity(intent);

                }

                @Override
                public void onFailure(Call<Property> call, Throwable t) {
                    Toast.makeText(UpdatePropertyActivity.this, "error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }catch(Exception e){
            Log.e(TAG, "onCreateView: " + e.getLocalizedMessage(), e);
        }

    }
}

