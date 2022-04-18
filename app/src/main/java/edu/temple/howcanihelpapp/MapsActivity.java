package edu.temple.howcanihelpapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import edu.temple.howcanihelpapp.databinding.ActivityMapsBinding;

public class MapsActivity extends AppCompatActivity implements
        OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener {

    // for location purposes
    private GoogleMap gMap;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        edu.temple.howcanihelpapp.databinding.ActivityMapsBinding binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;

        // set location preferences and permissions
        gMap.setOnMyLocationButtonClickListener(this);
        gMap.setOnMyLocationClickListener(this);

        // set marker and info window for map
        gMap.setOnInfoWindowClickListener(this);
        gMap.setOnMarkerClickListener(this);

        // set map camera/zoom preferences for map
        /**
         *  Location hardcoded to Philadelphia, need to fix this later...
         */
        gMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(39.980002, -75.155953)));
        gMap.setMinZoomPreference(13);

        // make adapter for info window on marker
        RequestInfoAdapter adapter = new RequestInfoAdapter(MapsActivity.this);
        gMap.setInfoWindowAdapter(adapter);

        /**
         *  FOR TESTING PURPOSES, GATHER DATA FROM DATABASE
         */
        RequestInfoPost recipient = new RequestInfoPost(
                "looking for food", 39.975794, -75.165123,
                "10:53AM 04/11/2022", true, false
        );
        RequestInfoPost donor = new RequestInfoPost(
                "canned food items", 39.983645, -75.156856,
                "01:30PM 04/11/2022", false, true
        );
        DonorMarker d1 = new DonorMarker(gMap, donor);
        RecipientMarker r1 = new RecipientMarker(gMap, recipient);

    }

    @Override
    public boolean onMarkerClick(@NonNull final Marker marker) {
        assert marker.getTag() != null;
        marker.showInfoWindow();
        return true;
    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {
        Intent intent = new Intent(MapsActivity.this, PostActivity.class);
        intent.putExtra("WINDOW_NAME", marker.getTitle());
        intent.putExtra("WINDOW_DATETIME", marker.getSnippet());
        startActivity(intent);
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_LONG).show();
    }
}