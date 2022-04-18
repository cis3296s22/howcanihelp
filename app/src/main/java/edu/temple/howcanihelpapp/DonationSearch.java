package edu.temple.howcanihelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DonationSearch extends AppCompatActivity {

    Button back, request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_search);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DonationSearch.this, RequestsActivity.class);
                startActivity(i);
            }
        });

        request = findViewById(R.id.donate);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DonationSearch.this, ConfirmPage.class);
                startActivity(i);
            }
        });
    }
}