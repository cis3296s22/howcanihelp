package edu.temple.howcanihelpapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.temple.howcanihelpapp.Firebase.AuthenticationHelper;
import edu.temple.howcanihelpapp.Firebase.AuthenticationHelperImpl;
import edu.temple.howcanihelpapp.Firebase.User;

/**
 * The purpose of this class is to crete the login page with the activity_login.xml as the layout
 */
public class Login extends AppCompatActivity {
    /**
     * userNamer and password are EditText fields that can take in information
     * btnSubmit is a Button that can be clicked
     * createAcc is a TextView
     */
    EditText userName, password;
    Button btnSubmit;
    TextView createAcc;


    @Override
    /**
     * onCreate(...) doesn't return anything. Its purpose is to build the Login page.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName =findViewById(R.id.text_email);
        password=findViewById(R.id.text_pass);
        btnSubmit = findViewById(R.id.btnSubmit_login);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * OnClick doesn't return anything. Its purpose is to login the user with their
             * username and password.
             */
            public void onClick(View view) {
                String userNameVal = userName.getText().toString();
                String passwordVal = password.getText().toString();
                if(userNameVal.length() == 0 || passwordVal.length() == 0) {
                    Toast.makeText(Login.this, "Please fill in the login form.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                AuthenticationHelperImpl.getInstance().signIn(
                        userName.getText().toString(),
                        password.getText().toString(),
                        signResult -> {
                            if (!signResult.isSuccessful()) {
                                switch (signResult.getFailReason()) {
                                    case UserNotFound:
                                        Toast.makeText(Login.this, "The login information is not associated with any user", Toast.LENGTH_SHORT).show();
                                        break;
                                    case UserDisabled:
                                        Toast.makeText(Login.this, "This user account is disabled.", Toast.LENGTH_SHORT).show();
                                        break;
                                    case PasswordIncorrect:
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                        builder.setCancelable(true);
                                        builder.setTitle("Wrong Credential");
                                        builder.setMessage("Wrong Credential");
                                        builder.show();
                                        break;
                                }
                                return;
                            }
                            User user = signResult.getUser();
                            Toast.makeText(Login.this,"You are now logged in " + user.getDisplayName()  + " !", Toast.LENGTH_SHORT).show();
                            showMenuActivity(user);
                        }
                );
            }
        });
        createAcc=findViewById(R.id.createAcc);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick doesn't return anything. Its purpose is to start the activity of intent.
             */
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });
    }

    @Override
    /**
     * onStart() doesn't return anything. Its purpose is to start the activity
     */
    protected void onStart() {
        super.onStart();
        showMenuActivityIfAuth();
    }

    /**
     * showMenuActivityIfAuth() doesn't return anything. Its purpose is to authenticate
     */
    void showMenuActivityIfAuth() {
        try {
            showMenuActivity(AuthenticationHelperImpl.getInstance().getUser());
        } catch (AuthenticationHelper.UnauthenticatedUserException e) {
            // Do nothing
        }
    }

    /**
     * showMenuActivity(...) doesn't return anything. Its purpose is to start the activity of
     * intent
     * @param user is a User
     */
    void showMenuActivity(User user) {
        Log.w("user", user.toString());;
        startActivity(new Intent(Login.this, MenuActivity.class));
    }
}