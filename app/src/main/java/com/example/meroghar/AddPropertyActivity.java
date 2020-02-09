package com.example.meroghar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.meroghar.Interfaces.PropertyApi;
import com.example.meroghar.Interfaces.UserApi;
import com.example.meroghar.ServerResponse.ImageResponse;
import com.example.meroghar.URL.Url;
import com.example.meroghar.strictmode.StrictModeClass;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.example.meroghar.URL.Url.imagePath;

public class AddPropertyActivity extends AppCompatActivity {

    EditText etPropertyTitle, etPropertyDescription, etPropertyPrice, etPropertyAddress,
            etBedroom, etKitchenroom, etLivingRoom, etBathroom;

    ImageView propertyImage;
    String imagePath;
    private String image="";

    Spinner categorySpinner, purposeSpinner;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4,
            checkBox5, checkBox6, checkBox7, checkBox8, checkBox9;

    Button btnAddProperty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        //SETTING TITLE ON ACTION BAR
        getSupportActionBar().setTitle("Add my property form");

        etPropertyTitle= findViewById(R.id.propertyTitle);
        etPropertyDescription = findViewById(R.id.propertyDescription);
        etPropertyPrice = findViewById(R.id.propertyPrice);
        etPropertyAddress = findViewById(R.id.propertyAddress);
        etBedroom= findViewById(R.id.bedRoom);
        etKitchenroom = findViewById(R.id.Kitchen);
        etLivingRoom = findViewById(R.id.livingRoom);
        etBathroom = findViewById(R.id.bathroom);

        //Image Initilization
        propertyImage= findViewById(R.id.propertyImage);

        //Checkbox initilization
        checkBox1 = findViewById(R.id.checkDrinkingWater);
        checkBox2 = findViewById(R.id.checkBoringWater);
        checkBox3 = findViewById(R.id.checkElectricity);
        checkBox4 = findViewById(R.id.checkInternet);
        checkBox5 = findViewById(R.id.checkBikeParking);
        checkBox6 = findViewById(R.id.checkCarParking);
        checkBox7 = findViewById(R.id.checkCableSharing);
        checkBox8 = findViewById(R.id.checkLaundry);
        checkBox9 = findViewById(R.id.checkEarthquakeProof);

        //Setting Values in spinner
        categorySpinner = findViewById(R.id.propertyPurposeSpinner);
        purposeSpinner = findViewById(R.id.propertyPurposeSpinner);

        //Button initilization
        btnAddProperty = findViewById(R.id.btnAddMyProperty);

        propertyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

        btnAddProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMyProperty();
            }

        });

    }

    //Image related functions
    private void BrowseImage(){

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_OK){
            if(data == null){
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();

            }
        }
        Uri uri = data.getData();
        propertyImage.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri){

        String[] projection ={MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null,null,null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }


    private void AddMyProperty() {
        Intent intent = new Intent(AddPropertyActivity.this, DashboardActivity.class);
        startActivity(intent);
        Toast.makeText(AddPropertyActivity.this, "Property Added Successfully", Toast.LENGTH_SHORT).show();
    }

    private void saveImage(){
        File file=new File(imagePath);
        RequestBody requestBody= RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body=MultipartBody.Part.createFormData("imageFile",file.getName(),requestBody);



        PropertyApi propertyApi= Url.getInstance().create(PropertyApi.class);
        Call<ImageResponse> imageResponseCall=propertyApi.uploadImage(body);

        StrictModeClass.StrictMode();

        try{

            Response<ImageResponse> imageResponseResponse = imageResponseCall.execute();
            image = imageResponseResponse.body().getFilename();
            Toast.makeText(this,"Profile Updated"+image,Toast.LENGTH_LONG).show();

        }catch (IOException e){
            Toast.makeText(this,"Error"+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }
}
