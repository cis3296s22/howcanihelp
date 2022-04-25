package edu.temple.howcanihelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The purpose of this class is to create the donations page that uses the activity_donations.xml
 * for its layout
 */
public class DonationsActivity extends AppCompatActivity {
    /**
     * searchReq, donItems, and back are buttons which activate different onClick() methods
     */
    Button searchReq, donItems, back;

    @Override
    /**
     * onBackPressed() doesn't return anything. Its purpose it to go to the previous activity.
     */
    public void onBackPressed() {
        super.onBackPressed();
        DonationsActivity.this.finish();
    }

    @Override
    /**
     * onCreate(...) doesn't return anything. Its purpose is to build the Donations page.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations);

        searchReq = findViewById(R.id.searchReqBtn);
        donItems = findViewById(R.id.donateItem);


        donItems.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick(...) doesn't return anything. It's purpose is start the activity of intent
             */
            public void onClick(View view) {
                Intent intent = new Intent(DonationsActivity.this, DonateItem.class);
                startActivity(intent);
            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick(...) doesn't return anything. It's purpose is start the activity of intent
             */
            public void onClick(View view) {
                Intent intent = new Intent(DonationsActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        searchReq.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick(...) doesn't return anything. It's purpose is start the activity of intent
             */
            public void onClick(View view) {
                Intent intent = new Intent(DonationsActivity.this, RequestSearch.class);
                startActivity(intent);
            }
        });
    }

}