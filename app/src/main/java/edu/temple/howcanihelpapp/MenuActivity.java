package edu.temple.howcanihelpapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import edu.temple.howcanihelpapp.Firebase.AuthenticationHelperImpl;
/**
 * The purpose of this class is to create the menu activity page that uses the activity_menu.xml
 * for its layout
 */
public class MenuActivity extends AppCompatActivity {
    /**
     * request and donate are buttons with various purposes
     * mapbtn and logout are ImageButtons
     * welcomeUser is a TextView
     * sb is a StringBuilder
     */
    Button request, donate;
    ImageButton mapbtn, logout;
    TextView welcomeUser;
    StringBuilder sb;

    @Override
    /**
     * onBackPressed() doesn't return anything. Its purpose it to go to the previous activity.
     */
    public void onBackPressed() {
        super.onBackPressed();
        MenuActivity.this.finish();
    }

    @Override
    /**
     * onCreate(...) doesn't return anything. Its purpose is to build the menu page.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // declare variables:
        request = findViewById(R.id.menuRequestButton);
        donate = findViewById(R.id.menuDonateButton);
        welcomeUser = (TextView) findViewById(R.id.welcomeText);
        mapbtn = (ImageButton) findViewById(R.id.mapButton);
        logout = findViewById(R.id.logout);

        // button configs
        mapbtn.setColorFilter(Color.argb(255, 0, 0, 0));

        /*// make StringBuilder for welcoming app user:
        sb = new StringBuilder(R.string.welcome_user);
        sb.append(R.string.user_name);
        welcomeUser.setText(sb.toString());*/

        // declare click listeners:
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick(...) doesn't return anything. It's purpose is start the activity of
             * requestIntent
             */
            public void onClick(View view) {
                // redirect to RequestsActivity
                Intent requestIntent = new Intent(MenuActivity.this, RequestsActivity.class);
                startActivity(requestIntent);
            }
        });
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick(...) doesn't return anything. It's purpose is start the activity of
             * donateIntent
             */
            public void onClick(View view) {
                // redirect to DonationsActivity
                Intent donateIntent = new Intent(MenuActivity.this, DonationsActivity.class);
                startActivity(donateIntent);
            }
        });
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick(...) doesn't return anything. It's purpose is start the activity of
             * mapIntent
             */
            public void onClick(View v) {
                // redirect to MapsActivity
                Intent mapIntent = new Intent(MenuActivity.this, MapsActivity.class);
                startActivity(mapIntent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick(...) doesn't return anything. It's purpose is start the activity of
             * logoutIntent
             */
            public void onClick(View view) {
                AuthenticationHelperImpl.getInstance().signOut(signOutSuccess -> {
                    if(!signOutSuccess)
                        Toast.makeText(MenuActivity.this,
                                "There was an error signing out.", Toast.LENGTH_SHORT).show();
                    Intent logoutIntent = new Intent(MenuActivity.this, Login.class);
                    startActivity(logoutIntent);
                });
            }
        });
    }

    @Override
    /**
     * onStart() doesn't return anything. Its purpose is to start the activity
     */
    protected void onStart() {
        super.onStart();
        if(!AuthenticationHelperImpl.getInstance().isAuthenticated())
            startActivity(new Intent(MenuActivity.this, Login.class));
    }
}
