package com.example.meroghar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener,com.google.android.gms.location.LocationListener
{

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Marker currentUserLocationMarker;
    private static final int Request_user_location_code = 99;
    CameraUpdate center, zoom;
    private Location lastLocation;
    Button btn_go_backto_nav;

    //private static final String TAG = NearetLocation_map.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkUserLocationPermission();
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        LatLng Thamel = new LatLng(27.715389, 85.312332);
        mMap.addMarker(new MarkerOptions().position(Thamel).title("Thamel"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Thamel));

        LatLng Softwarica = new LatLng(27.7052354, 85.3294158);
        mMap.addMarker(new MarkerOptions().position(Softwarica).title("Dillibazar"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Softwarica));

        LatLng Jorpati = new LatLng(27.7278, 85.3782);
        mMap.addMarker(new MarkerOptions().position(Jorpati).title("Jorapati"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Jorpati));

        LatLng Samakhushi = new LatLng(27.7273, 85.3175);
        mMap.addMarker(new MarkerOptions().position(Samakhushi).title("Samakhushi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Samakhushi));

        LatLng Koteshwor = new LatLng(27.6756, 85.3459);
        mMap.addMarker(new MarkerOptions().position(Koteshwor).title("Koteshwor"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Koteshwor));

        LatLng Kalimaati = new LatLng(27.7000, 85.2891);
        mMap.addMarker(new MarkerOptions().position(Kalimaati).title("Kalimaati"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Kalimaati));

        LatLng sanepa = new LatLng(27.6844, 85.3059);
        mMap.addMarker(new MarkerOptions().position(sanepa).title("sanepa"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sanepa));

        LatLng dhobighat = new LatLng(27.6770, 85.3020);
        mMap.addMarker(new MarkerOptions().position(dhobighat).title("dhobighat"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dhobighat));

        LatLng maharajgunj = new LatLng(27.7365, 85.3304);
        mMap.addMarker(new MarkerOptions().position(maharajgunj).title("maharajgunj"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(maharajgunj));

        LatLng satdobato = new LatLng(27.6515, 85.3278);
        mMap.addMarker(new MarkerOptions().position(satdobato).title("satdobato"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(satdobato));

        LatLng budhanilkantha = new LatLng(27.7654, 85.3653);
        mMap.addMarker(new MarkerOptions().position(budhanilkantha).title("budhanilkantha"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(budhanilkantha));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case Request_user_location_code:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED){
                        if(googleApiClient==null){
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }else{
                    Toast.makeText(this,"Permission denied...",Toast.LENGTH_LONG).show();
                }
                return;
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        if(currentUserLocationMarker!=null){
            currentUserLocationMarker.remove();
        }

        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Your current location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        currentUserLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(12));

        if(googleApiClient!=null){
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest, (com.google.android.gms.location.LocationListener) this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    protected  synchronized  void buildGoogleApiClient(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    public boolean checkUserLocationPermission(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_user_location_code);
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_user_location_code);
            }

            return false;
        }else{
            return true;
        }
    }
}
