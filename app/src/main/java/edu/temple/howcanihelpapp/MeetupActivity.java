package edu.temple.howcanihelpapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MeetupActivity extends AppCompatActivity {

    EditText addr, addrcity, addrstate, addrzip, useraffiliation, specialnotes;
    ToggleButton usermobility;
    Button meetupsubmit;
    private ArrayList<EditText> requiredTexts;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MeetupActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetup);

        // required declare variables
        addr = (EditText) findViewById(R.id.textAddressLine);
        addrcity = (EditText) findViewById(R.id.textCity);
        addrstate = (EditText) findViewById(R.id.textState);
        addrzip = (EditText) findViewById(R.id.textZip);

        // optional text-based variables AND other components
        useraffiliation = (EditText) findViewById(R.id.textOptAffiliation);
        usermobility = (ToggleButton) findViewById(R.id.toggleMobility);
        specialnotes = (EditText) findViewById(R.id.textareaSpecial);
        meetupsubmit = (Button) findViewById(R.id.meetupSubmit);

        // quick solution for confirming all required fields are filled in
        requiredTexts = new ArrayList<>(Arrays.asList(addr, addrcity, addrstate, addrzip));

        // button listener
        meetupsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // all fields must be filled out accordingly, otherwise notify with error(s)
                boolean meetupClear = true;
                StringBuilder addrToGeo = new StringBuilder();
                for (int i = 0; i < requiredTexts.size(); i++) {
                    EditText temp = (EditText) requiredTexts.get(i);
                    if (TextUtils.isEmpty(temp.getText())) {
                        meetupClear = false;
                        temp.setError(temp.getHint().toString() + "required!");
                    } else {
                        addrToGeo.append(temp.getText().toString()).append(" ");
                    }
                }

                // IMPORTANT,  CHANGE null to new page...
                if (meetupClear) {
                    Intent confirmMeetup = new Intent(MeetupActivity.this, null);
                    confirmMeetup.putExtra("ADDRESS_TO_GEOLOCATION", addrToGeo.toString());
                    startActivity(confirmMeetup);
                }
            }
        });

        // before we add the address to the database, get it's geolocation equivalent (coordinates):


    }
}
