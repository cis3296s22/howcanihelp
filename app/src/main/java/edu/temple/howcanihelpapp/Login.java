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

public class Login extends AppCompatActivity {
    EditText userName, password;
    Button btnSubmit;
    TextView createAcc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName =findViewById(R.id.text_email);
        password=findViewById(R.id.text_pass);
        btnSubmit = findViewById(R.id.btnSubmit_login);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
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
                            Toast.makeText(Login.this,"You are now logged in" + user.getDisplayName()  + " !", Toast.LENGTH_SHORT).show();
                            showMenuActivityNoThrow();
                        }
                );
            }
        });
        createAcc=findViewById(R.id.createAcc);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        showMenuActivityNoThrow();
    }

    void showMenuActivityNoThrow() {
        if(AuthenticationHelperImpl.getInstance().isAuthenticated()) {
            // The user is already signed in, redirect them to the menu activity.
            try {
                showMenuActivity();
            } catch (AuthenticationHelper.UnauthenticatedUserException e) {
                // Not gonna happen if isAuthenticated() == true
            }
        }
    }

    void showMenuActivity() throws AuthenticationHelper.UnauthenticatedUserException {
        Log.w("user", AuthenticationHelperImpl.getInstance().getUser().toString());
        startActivity(new Intent(Login.this, MenuActivity.class));
    }
}