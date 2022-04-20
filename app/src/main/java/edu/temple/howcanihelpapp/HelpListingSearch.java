package edu.temple.howcanihelpapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Map;

import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListing;
import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListingDbRef;

abstract public class HelpListingSearch extends AppCompatActivity {
    class HelpListingView extends ArrayAdapter {
        private Context mContext;
        private ArrayList<Map.Entry<String, HelpListing>> mItems;
        public HelpListingView(@NonNull Context context,
                               ArrayList<Map.Entry<String, HelpListing>> items) {
            super(context, 0, items);
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

    protected void updateHelpListingList(ListView helpListingListView, Map<String, HelpListing> helpListingMap) {
        if(helpListingMap.size() == 0) {
            // TODO: Instead of display a Toast after this check, it would probably be better if the view
            // was updated to say "No listings were found :("
            Toast.makeText(this, "No listings were found :(",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayList<Map.Entry<String, HelpListing>> items = new ArrayList<>(helpListingMap.entrySet());
        HelpListingView itemListAdapter =
                new HelpListingView(this, items);
        helpListingListView.setAdapter(itemListAdapter);
    }
}
