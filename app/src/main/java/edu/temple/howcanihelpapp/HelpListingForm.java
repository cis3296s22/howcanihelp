package edu.temple.howcanihelpapp;

import static java.lang.Math.random;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListing;
import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListingBuilder;
import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListingDbRef;

abstract public class HelpListingForm extends AppCompatActivity {
    private Button back, submit;
    private CheckBox isUrgent, canRelocate;
    private TextView title, description, address;
    private boolean isRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBack(view);
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

    protected abstract void onBack(View view);

    protected void setIsRequest(boolean isRequest) {
        this.isRequest = isRequest;
    }

    private void donateItem() {
        try {
            final double SAMPLE_LAT = 39.975794;
            final double SAMPLE_LNG = -75.165123;
            double randLat =  Math.random()*0.03;
            double randLNG = Math.random()*0.02;
            HelpListingBuilder helpListingBuilder = new HelpListingBuilder().setAsDonor()
                    .setTitle(title.getText().toString())
                    .setDescription(description.getText().toString())
                    .setLocation(new HelpListing.Location(address.getText().toString(),
                            SAMPLE_LAT + randLat,
                            SAMPLE_LNG + randLNG))
                    .setIsUrgent(isUrgent.isChecked())
                    .setCanRelocate(canRelocate.isChecked());
            if(isRequest)
                helpListingBuilder.setAsRequest();
            else
                helpListingBuilder.setAsDonor();
            HelpListing helpListing = helpListingBuilder
                    .getHelpListing();
            HelpListingDbRef.pushToDb(helpListing, result -> {
                if(!result.isSuccessful())
                    Toast.makeText(this, "Unsuccessful in processing the request :(", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MenuActivity.class);
                startActivity(i);
            });

        } catch (Exception e) {
            // nothing
            Log.e("donateItem", e.getMessage());
        }
    }
}
