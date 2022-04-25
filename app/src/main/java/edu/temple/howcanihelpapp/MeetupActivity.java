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

/**
 *  The purpose of this class is to provide a bridge between the user wanting to
 *  complete a request and completing the request. This post requests information
 *  in regards to location, urgency, ability to relocate, user affiliation, and
 *  other notes the user wants the other party to know.
 */
public class MeetupActivity extends AppCompatActivity {

    /**
     *  addr is a Text field for the main address
     *  addrcity is a Text field for the user's city
     *  addrstate is a Text field for the user's state
     *  addrzip is a Text field for the user's ZIP code
     *  useraffiliation is a Text field that asks if a user is with a charity organization or by themselves
     *  specialnotes is a Text area field that holds special notes from the user
     *  usermobility is a ToggleButton that answers if a user is able to relocate
     *  meetupsubmit is the Button that submits the information and confirms the request
     */
    EditText addr, addrcity, addrstate, addrzip, useraffiliation, specialnotes;
    ToggleButton usermobility;
    Button meetupsubmit;

    /**
     *  only used to indicate which fields are required before submission
     */
    private ArrayList<EditText> requiredTexts;

    /**
     *  Part of the AppCompatActivity class, functionality for backing out of activity
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MeetupActivity.this.finish();
    }

    /**
     * Part of the AppCompatActivity class, creates activity
     *
     * @param savedInstanceState
     */
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
            /**
             * Provides a method for the View.OnClickListener and enables an
             * event listener when user clicks on button
             *
             * @param v
             */
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
