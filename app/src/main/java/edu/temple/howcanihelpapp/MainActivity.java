package edu.temple.howcanihelpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.temple.howcanihelpapp.
        Sql.DBHelper;
/**
 * The purpose of this class is to create the main activity page that uses the activity_main.xml
 * for its layout
 */
public class MainActivity extends AppCompatActivity {
    /**
     * login and Reg are buttons with various purposes
     * toolbar is a Toolbar
     * dbHelper is a DBHelper
     */
    Button login, Reg;
    Toolbar toolbar;
    DBHelper dbHelper;

    @Override
    /**
     * onBackPressed() doesn't return anything. Its purpose it to go to the previous activity.
     */
    public void onBackPressed() {
        MainActivity.this.finish();
    }

    @Override
    /**
     * onCreate(...) doesn't return anything. Its purpose is to build the main page.
     */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        login = (Button) findViewById(R.id.btnLogin);
        toolbar = (Toolbar) findViewById(R.id.tool_main);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick(...) doesn't return anything. It's purpose is start the activity of intent
             */
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        Reg = findViewById(R.id.btnSignUp);
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick(...) doesn't return anything. It's purpose is start the activity of intent
             */
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

    }
}