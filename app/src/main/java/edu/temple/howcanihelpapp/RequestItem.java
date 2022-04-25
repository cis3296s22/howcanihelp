package edu.temple.howcanihelpapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/**
 * The purpose of this class is to generate the layout of the page to enter what item is to be
 * requested using the activity_request_item.xml
 */
public class RequestItem extends HelpListingForm {
    @Override
    /**
     * OnCreate() doesn't return anything. Its purpose is to build the request item page.
     */
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_request_item);
        super.onCreate(savedInstanceState);
        this.setIsRequest(true);
    }

    @Override
    /**
     * onBack(...) does not return anything. Its purpose is to return the user to the previous page
     * @param view is a View
     */
    protected void onBack(View view) {
        Intent i = new Intent(RequestItem.this, RequestsActivity.class);
        startActivity(i);
    }
}