package edu.temple.howcanihelpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * The purpose of this class is to create the confirm page that uses the activity_confirm_page.xml
 * for the layout.
 */
public class ConfirmPage extends AppCompatActivity {
    /**
     * It has the buttons home and logout. The home button return the user to the home page and the
     * logout button will logout the user from their account.
     */
    Button home;
    ImageButton logout;

    @Override
    /**
     * OnCreate() doesn't return anything. Its purpose is to build the confirm page.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_page);

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick() doesn't return anything. The purpose is to start the i intent
             */
            public void onClick(View view) {
                Intent i = new Intent(ConfirmPage.this, MenuActivity.class);
                startActivity(i);
            }
        });

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick() doesn't return anything. The purpose is to start the logout
             */
            public void onClick(View view) {
                Intent logoutIntent = new Intent(ConfirmPage.this, Login.class);
                startActivity(logoutIntent);
            }
        });
    }
}