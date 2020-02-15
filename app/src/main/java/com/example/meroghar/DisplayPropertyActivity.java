package com.example.meroghar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meroghar.URL.Url;
import com.squareup.picasso.Picasso;

public class DisplayPropertyActivity extends AppCompatActivity {

    private TextView tvTitle, tvAddress, tvCategory, tvPurpose, tvPrice, tvDesc, tvFacility1, tvFacility2,
            tvFacility3, tvFacility4, tvBedroom, tvKitchen, tvLivingroom, tvBatroom, tvOwnerName, tvOwnerAddress,
            tvOwnerEmail;
    private Button btnOwnerPhone, btnfav;
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
        btnfav = findViewById(R.id.addtofavorite);

        btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DisplayPropertyActivity.this, "Property added to wishlist.", Toast.LENGTH_SHORT).show();

            }
        });

        btnOwnerPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dial();
            }
        });

        propertyImage= findViewById(R.id.viewPropertyImage);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            tvTitle.setText(bundle.getString("proTitle"));
            String image = bundle.getString("proImage");
            Picasso.get().load(Url.imagePath + image).into(propertyImage);
            tvAddress.setText(bundle.getString("proAddress"));
            tvCategory.setText(bundle.getString("proCategory"));
            tvPurpose.setText(bundle.getString("proPurpose"));
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

            tvOwnerName.setText(bundle.getString("ownerName"));
            tvOwnerEmail.setText(bundle.getString("ownerEmail"));
            tvOwnerAddress.setText(bundle.getString("ownerAddress"));

            btnOwnerPhone.setText(bundle.getString("ownerPhone"));


        }

    }
    private void Dial() {
        Uri u = Uri.parse("tel:" + btnOwnerPhone.getText().toString());
        Intent intent = new Intent(Intent.ACTION_DIAL, u);
        startActivity(intent);
    }

    public int TotalRentCost(int rent, int vat){
        int total =(rent +(rent *vat/100));
        return total;
    }
}
