package edu.temple.howcanihelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RequestsActivity extends AppCompatActivity {

    Button searchDon, reqItems, back;

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


        searchDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestsActivity.this, DonationSearch.class);
                startActivity(intent);
            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestsActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        reqItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestsActivity.this, RequestItem.class);
                startActivity(intent);
            }
        });
    }

}
