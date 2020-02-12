package com.example.meroghar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayPropertyActivity extends AppCompatActivity {

    private TextView tvTitle, tvAddress, tvCategory, tvPurpose, tvPrice, tvDesc, tvFacility1, tvFacility2,
            tvFacility3, tvFacility4, tvBedroom, tvKitchen, tvLivingroom, tvBatroom, tvOwnerName, tvOwnerAddress,
            tvOwnerEmail;
    private Button btnOwnerPhone;
    private ImageView propertyImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_property);

        getSupportActionBar().setTitle("Detail of the property");

        tvTitle = findViewById(R.id.viewPropertyTitle);
        tvAddress =findViewById(R.id.viewPropertyAddress);
        tvCategory = findViewById(R.id.viewPropertyCategory);
        tvPurpose = findViewById(R.id.viewPropertyPurpose);
        tvPrice = findViewById(R.id.viewPropertyPrice);
        tvDesc = findViewById(R.id.viewPropertyDescription);

        tvFacility1= findViewById(R.id.viewFacility1);
        tvFacility2= findViewById(R.id.viewFacility2);
        tvFacility3= findViewById(R.id.viewFacility3);
        tvFacility4= findViewById(R.id.viewFacility4);

        tvBedroom = findViewById(R.id.viewPropertyBedroom);
        tvKitchen = findViewById(R.id.viewPropertyKitchen);
        tvLivingroom = findViewById(R.id.viewPropertyLivingRoom);
        tvBatroom = findViewById(R.id.viewPropertyBathroom);

        tvOwnerName = findViewById(R.id.viewOwnerName);
        tvOwnerAddress = findViewById(R.id.viewOwnerAddress);
        tvOwnerEmail = findViewById(R.id.viewOwnerEmail);

        btnOwnerPhone = findViewById(R.id.viewOwnerPhone);

        propertyImage= findViewById(R.id.viewPropertyImage);

    }
}
