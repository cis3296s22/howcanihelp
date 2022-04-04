package edu.temple.howcanihelpapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MenuActivity extends AppCompatActivity implements OnMapReadyCallback {
    Button request, donate;
    TextView welcomeUser;
    GoogleMap mMap;
    StringBuilder sb;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MenuActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // declare variables:
        request = (Button) findViewById(R.id.menuRequestButton);
        donate = (Button) findViewById(R.id.menuDonateButton);
        welcomeUser = (TextView) findViewById(R.id.welcomeText);

        // make StringBuilder for welcoming app user:
        sb = new StringBuilder(R.string.welcome_user);
        sb.append(R.string.user_name);
        welcomeUser.setText(sb.toString());

        // map
        SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.mapView);
        assert smf != null;
        smf.getMapAsync(this);

        // declare click listeners:
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // RequestsActivity will be made
                Intent requestIntent = new Intent(MenuActivity.this, RequestsActivity.class);
                startActivity(requestIntent);
            }
        });
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DonationsActivity will be made
                Intent donateIntent = new Intent(MenuActivity.this, DonationsActivity.class);
                startActivity(donateIntent);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        // Philadelphia cooordinates, for testing
        LatLng home = new LatLng(39.980457, -75.155272);
        mMap.addMarker(
                new MarkerOptions().position(home).title("Home")
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLng(home));
    }
}
