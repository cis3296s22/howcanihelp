package edu.temple.howcanihelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class RequestsActivity extends AppCompatActivity {
    Button searchDon, reqItems;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RequestsActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        searchDon = findViewById(R.id.searchDonBtn);
        reqItems = findViewById(R.id.requestItem);

        //map
        SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        assert smf != null;
        smf.getMapAsync((OnMapReadyCallback) this);

        searchDon = findViewById(R.id.btnSignUp);
        searchDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestsActivity.this, donationSearch.class);
                startActivity(intent);
            }
        });
    }

}
