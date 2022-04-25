package edu.temple.howcanihelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListingDbRef;

/**
 * The purpose of this class is to create the donations search page using the
 * activity_donation_search.xml for the layout.
 */

public class DonationSearch extends HelpListingSearch {
    /**
     * itemListView will be a ListView of the the items requested to be donated
     * back and request are buttons with various functions
     */
    ListView itemListView;
    Button back, request;

    @Override
    /**
     * onCreate(...) doesn't return anything. Its purpose is to build the Donations Search page.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_search);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick doesn't return anything. Its purpose is to start the activity of i.
             */
            public void onClick(View view) {
                Intent i = new Intent(DonationSearch.this, RequestsActivity.class);
                startActivity(i);
            }
        });

        request = findViewById(R.id.donate);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick doesn't return anything. Its purpose is to start the activity of i.
             */
            public void onClick(View view) {
                Intent i = new Intent(DonationSearch.this, ConfirmPage.class);
                startActivity(i);
            }
        });

        itemListView = findViewById(R.id.donationListings);
        HelpListingDbRef.getDonationListings(10, requestListings -> this.updateHelpListingList(itemListView, requestListings));
    }
}