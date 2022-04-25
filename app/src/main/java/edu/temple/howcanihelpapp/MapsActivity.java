package edu.temple.howcanihelpapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListingDbRef;
import edu.temple.howcanihelpapp.databinding.ActivityMapsBinding;

/**
 *  The purpose of the MapsActivity is to assemble a Google Maps implementation for Android
 *  The activity provides an overview of what is available location-wise for convenience of user
 */
public class MapsActivity extends AppCompatActivity implements
        OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    /**
     *  gMap is a Google Maps instance
     *  customMarkers provides a List of markers, assumed to be pulled from database
     */
    private GoogleMap gMap;
    List<CustomMarker> customMarkers;

    /**
     *  Part of AppCompatActivity, backing out of activity
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * Part of AppCompatActivity, creates activity
     *
     * @param savedInstanceState
     */
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
        customMarkers = new ArrayList<>();
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
    /**
     * Part of Google Maps OnMapReadyCallback, creates the instance of Google maps onto the activity
     *
     * @param googleMap
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
        // Location hardcoded to Philadelphia, need to fix this later...
        gMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(39.980002, -75.155953)));
        gMap.setMinZoomPreference(13);

        // make adapter for info window on marker
        RequestInfoAdapter adapter = new RequestInfoAdapter(MapsActivity.this);
        gMap.setInfoWindowAdapter(adapter);

        HelpListingDbRef.getHelpListings(25, helpListingMap -> {
            Toast.makeText(MapsActivity.this, "There are " + helpListingMap.size() +
                    " listings in your area.", Toast.LENGTH_SHORT).show();
            if(helpListingMap.size() == 0)
                return;
            helpListingMap.forEach((key, helpListing) -> {
                // TODO: key is the identifier of the help listing from the database
                if(helpListing.isRequest)
                    customMarkers.add(new RecipientMarker(gMap, new RequestInfoPost(
                            helpListing.title, helpListing.location.latitude,
                            helpListing.location.longitude,
                            new Date(helpListing.timePostedMs).toString(),
                            helpListing.isUrgent, helpListing.canRelocate
                    )));
                else
                    customMarkers.add(new DonorMarker(gMap, new RequestInfoPost(
                            helpListing.title, helpListing.location.latitude,
                            helpListing.location.longitude,
                            new Date(helpListing.timePostedMs).toString(),
                            helpListing.isUrgent, helpListing.canRelocate
                    )));
            });
        });
    }

    /**
     * Part of GoogleMap.OnMarkerClickListener, provides event listener when user
     * clicks on the selected marker. When user engages, the marker will display
     * a bubble above, showing the information window containing the title, date
     * and time, location, and other important factors.
     *
     * @param marker
     * @return
     */
    @Override
    public boolean onMarkerClick(@NonNull final Marker marker) {
        assert marker.getTag() != null;
        marker.showInfoWindow();
        return true;
    }

    /**
     * Part of GoogleMap.OnInfoWindowClickListener, provides event listener
     * when the user clicks on the info window. Redirects to Post Activity
     *
     * @param marker
     */
    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {
        RequestInfoPost clicked = (RequestInfoPost) marker.getTag();
        assert clicked != null;

        Intent intent = new Intent(MapsActivity.this, PostActivity.class);
        intent.putExtra("WINDOW_POST", clicked);
        startActivity(intent);
    }

    /**
     * Part of GoogleMap.OnMyLocationButtonClickListener, event listener for user
     * to locate present location. Location button is provided when the user enables
     * location permissions via Android
     *
     * @return
     */
    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    /**
     * Part of GoogleMap.OnMyLocationClickListener, event listener for user to engage
     * with current location marker (blue); this implementation returns a toast message
     * explaining the current coordinates of the user.
     *
     * @param location
     */
    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_LONG).show();
    }
}