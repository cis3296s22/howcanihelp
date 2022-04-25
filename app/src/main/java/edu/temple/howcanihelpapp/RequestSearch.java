package edu.temple.howcanihelpapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import edu.temple.howcanihelpapp.Firebase.AuthenticationHelper;
import edu.temple.howcanihelpapp.Firebase.AuthenticationHelperImpl;
import edu.temple.howcanihelpapp.Firebase.CreateUserResult;
import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListing;
import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListingDbRef;

/**
 * The purpose of this class is to create the request search page using the
 * activity_request_search.xml for the layout.
 */
public class RequestSearch extends HelpListingSearch {
    /**
     * itemListView will be a ListView of the the items requested to be donated
     * back and donate are buttons with various functions
     */
    ListView itemListView;
    Button back, donate;

    @Override
    /**
     * onCreate(...) doesn't return anything. Its purpose is to build the Request Search page.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_search);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick doesn't return anything. Its purpose is to start the activity of i.
             */
            public void onClick(View view) {
                Intent i = new Intent(RequestSearch.this, DonationsActivity.class);
                startActivity(i);
            }
        });

        donate = findViewById(R.id.donate);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick doesn't return anything. Its purpose is to start the activity of i.
             */
            public void onClick(View view) {
                Intent i = new Intent(RequestSearch.this, ConfirmPage.class);
                startActivity(i);
            }
        });

        itemListView = findViewById(R.id.requestListings);
        HelpListingDbRef.getRequestListings(10, requestListings -> this.updateHelpListingList(itemListView, requestListings));
    }
}