package edu.temple.howcanihelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * The purpose of this class is to create the request page that uses the activity_requests.xml
 * for its layout
 */
public class RequestsActivity extends AppCompatActivity {
    /**
     * searchDon, reqItems, and back are Buttons with various purposes
     */
    Button searchDon, reqItems, back;

    @Override
    /**
     * onBackPressed() doesn't return anything. Its purpose it to go to the previous activity.
     */
    public void onBackPressed() {
        super.onBackPressed();
        RequestsActivity.this.finish();
    }

    @Override
    /**
     * onCreate(...) doesn't return anything. Its purpose is to build the requests page.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        searchDon = findViewById(R.id.searchDonBtn);
        reqItems = findViewById(R.id.requestItem);


        searchDon.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick(...) doesn't return anything. It's purpose is start the activity of intent
             */
            public void onClick(View view) {
                Intent intent = new Intent(RequestsActivity.this, DonationSearch.class);
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
                Intent intent = new Intent(RequestsActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        reqItems.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick(...) doesn't return anything. It's purpose is start the activity of intent
             */
            public void onClick(View view) {
                Intent intent = new Intent(RequestsActivity.this, RequestItem.class);
                startActivity(intent);
            }
        });
    }

}
