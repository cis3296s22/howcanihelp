package edu.temple.howcanihelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListing;
import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListingBuilder;
import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListingDbRef;

public class DonateItem extends AppCompatActivity {
    Button back, submit;
    CheckBox isUrgent, canRelocate;
    TextView title, description, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_item);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DonateItem.this, DonationsActivity.class);
                startActivity(i);
            }
        });

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donateItem();
            }
        });

        canRelocate = findViewById(R.id.newHelpItemCanRelocate);
        isUrgent = findViewById(R.id.newHelpItemIsUrgent);

        title = findViewById(R.id.newHelpItemTitle);
        description = findViewById(R.id.newHelpItemDescription);
        address = findViewById(R.id.newHelpItemAddress);
    }

    void donateItem() {
        try {
            HelpListing helpListing = new HelpListingBuilder().setAsDonor()
                    .setTitle(title.getText().toString())
                    .setDescription(description.getText().toString())
                    .setLocation(new HelpListing.Location(address.getText().toString(), 0, 0))
                    .setIsUrgent(isUrgent.isChecked())
                    .setCanRelocate(canRelocate.isChecked())
                    .getHelpListing();
            HelpListingDbRef.pushToDb(helpListing, result -> {
                if(!result.isSuccessful())
                    Toast.makeText(DonateItem.this, "Unsuccessful in processing the request :(", Toast.LENGTH_SHORT).show();
                Toast.makeText(DonateItem.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(DonateItem.this, MenuActivity.class);
                startActivity(i);
            });

        } catch (Exception e) {
            // nothing
            Log.e("donateItem", e.getMessage());
        }
    }
}