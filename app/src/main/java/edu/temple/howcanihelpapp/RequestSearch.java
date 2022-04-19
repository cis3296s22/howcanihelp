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

public class RequestSearch extends AppCompatActivity {

    class HelpListingView extends ArrayAdapter {
        private Context mContext;
        private ArrayList<Map.Entry<String, HelpListing>> mItems;
        public HelpListingView(@NonNull Context context, int resource,
                               ArrayList<Map.Entry<String, HelpListing>> items) {
            super(context, resource, items);
            mItems = items;
            mContext = context;
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public View getView(int position, View helpListingListItem, ViewGroup parent) {
            Log.d("HelpListingView.getView", "position: " + position);
            if(helpListingListItem == null)
                helpListingListItem = LayoutInflater.from(mContext).inflate(
                        R.layout.help_listing_list_item, parent, false);
            HelpListing helpListing = mItems.get(position).getValue();
            TextView title = helpListingListItem.findViewById(R.id.helpListingTitle);
            title.setText(helpListing.title);
            return helpListingListItem;
        }
    }

    ListView itemListView;
    Button back, donate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_search);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RequestSearch.this, DonationsActivity.class);
                startActivity(i);
            }
        });

        donate = findViewById(R.id.donate);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RequestSearch.this, ConfirmPage.class);
                startActivity(i);
            }
        });

        itemListView = findViewById(R.id.helpListingList);
        HelpListingDbRef.getRequestListings(10, requestListings -> updateList(requestListings));
    }

    private void updateList(Map<String, HelpListing> helpListingMap) {
        if(helpListingMap.size() == 0) {
            // TODO: Instead of display a Toast after this check, it would probably be better if the view
            // was updated to say "No listings were found :("
            Toast.makeText(RequestSearch.this, "No listings were found :(",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayList<Map.Entry<String, HelpListing>> items = new ArrayList<>(helpListingMap.entrySet());
        HelpListingView itemListAdapter =
                new HelpListingView(this, android.R.layout.simple_list_item_1, items);
        itemListView.setAdapter(itemListAdapter);
    }
}