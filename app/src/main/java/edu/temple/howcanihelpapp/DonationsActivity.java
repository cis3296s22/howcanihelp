package edu.temple.howcanihelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DonationsActivity extends AppCompatActivity {
    Button searchReq, donItems, back;

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