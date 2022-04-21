package edu.temple.howcanihelpapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DonateItem extends HelpListingForm {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_donate_item);
        super.onCreate(savedInstanceState);
        this.setIsRequest(false);
    }

    @Override
    protected void onBack(View view) {
        Intent i = new Intent(DonateItem.this, DonationsActivity.class);
        startActivity(i);
    }
}