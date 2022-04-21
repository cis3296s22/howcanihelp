package edu.temple.howcanihelpapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RequestItem extends HelpListingForm {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_request_item);
        super.onCreate(savedInstanceState);
        this.setIsRequest(true);
    }

    @Override
    protected void onBack(View view) {
        Intent i = new Intent(RequestItem.this, RequestsActivity.class);
        startActivity(i);
    }
}