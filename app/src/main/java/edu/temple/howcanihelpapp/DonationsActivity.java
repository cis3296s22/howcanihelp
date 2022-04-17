package edu.temple.howcanihelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;

public class DonationsActivity extends AppCompatActivity {
    Button searchReq, donItems, back;
    GoogleMap mMap;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DonationsActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations);

        searchReq = findViewById(R.id.searchReqBtn);
        donItems = findViewById(R.id.donateItem);

        //map
        /*
        SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        assert smf != null;
        smf.getMapAsync((OnMapReadyCallback) this);
        */

        donItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonationsActivity.this, RequestSearch.class);
                startActivity(intent);
            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonationsActivity.this, MenuActivity.class);
            }
        });

    }

}