package edu.temple.howcanihelpapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * The purpose of this class is to generate the layout of the page to enter what item is to be
 * donated using the activity_donate_item.xml
 */
public class DonateItem extends HelpListingForm {
    @Override
    /**
     * OnCreate() doesn't return anything. Its purpose is to build the donate item page.
     */
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_donate_item);
        super.onCreate(savedInstanceState);
        this.setIsRequest(false);
    }

    /**
     * onBack(...) does not return anything. Its purpose is to return the user to the previous page
     * @param view is a View
     */
    @Override
    protected void onBack(View view) {
        Intent i = new Intent(DonateItem.this, DonationsActivity.class);
        startActivity(i);
    }
}