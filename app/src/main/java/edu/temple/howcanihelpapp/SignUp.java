package edu.temple.howcanihelpapp;

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
 * The purpose of this class is to crete the Sign Up page with the activity_sign_up.xml as the
 * layout
 */
public class SignUp extends AppCompatActivity {
    /**
     * userNamer, number, email, and pass are EditText fields that can take in information
     * login is a TextView
     */
    EditText  number , email,pass,userName;
    TextView login;

    /**
     * onCreate doesn't return anything. Its purpose is to create the Sign Up page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userName=findViewById(R.id.textName);
        number=findViewById(R.id.textNumber);
        email=findViewById(R.id.textEmail);
        pass=findViewById(R.id.textPass);

        Button signUpAcc = findViewById(R.id.btnSignUpAcc);
        signUpAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * OnClick doesn't return anything. Its purpose is to sign up the user with their
             * entered informaiton.
             */
            public void onClick(View view) {
                String userName1 = userName.getText().toString();
                String number1 = number.getText().toString();
                String email1 = email.getText().toString();
                String pass1 = pass.getText().toString();

                if(userName1.length() == 0 || number1.length() == 0 || email1.length() == 0 ||
                pass1.length() == 0) {
                    Toast.makeText(SignUp.this, "Please fill out the form!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    AuthenticationHelperImpl.getInstance().createUser(
                            email1,
                            pass1,
                            userName1,
                            number1,
                            createUserResult -> {
                                if(!createUserResult.isSuccessful()) {
                                    switch (createUserResult.getFailReason()) {
                                        case EmailExists:
                                            Toast.makeText(
                                                    SignUp.this,
                                                    "The email is already in use!",
                                                    Toast.LENGTH_SHORT).show();
                                            break;
                                        case WeakPassword:
                                            Toast.makeText(
                                                    SignUp.this,
                                                    "Choose a stronger password!",
                                                    Toast.LENGTH_SHORT).show();
                                            break;
                                        case MalformedEmail:
                                            Toast.makeText(
                                                    SignUp.this,
                                                    "Choose a valid email address!",
                                                    Toast.LENGTH_SHORT).show();
                                            break;
                                        case None:
                                        default:
                                            Toast.makeText(
                                                    SignUp.this,
                                                    "Uh-oh this was not supposed to happen!",
                                                    Toast.LENGTH_SHORT).show();
                                            Log.w("createUser", "Uh-oh this was not supposed to happen!");
                                    }
                                    return;
                                }
                                User fibaUser = createUserResult.getCreatedUser();
                                Log.d(
                                        "createUser",
                                        "Success in creating a user!" +
                                                "\nName: " + fibaUser.getDisplayName() +
                                                "\nEmail: " + fibaUser.getEmail() +
                                                "\nPhoneNumber: " + fibaUser.getPhoneNumber() +
                                                "\nUid: " + fibaUser.getUid()
                                );
                                userName.setText("");
                                number.setText("");
                                email.setText("");
                                pass.setText("");
                                Toast.makeText(
                                        SignUp.this,
                                        "Welcome to the app " + fibaUser.getDisplayName() + "!",
                                        Toast.LENGTH_SHORT).show();
                                showMenuActivity(fibaUser);
                            }
                    );
                } catch (AuthenticationHelper.AuthenticatedUserIsPresent e) {
                    Log.w("sign up", e.getMessage());
                    showMenuActivity(e.user);
                }
            }
        });
        login=findViewById(R.id.loginAcc);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick doesn't return anything. Its purpose is to start the activity of i.
             */
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this,Login.class);
                startActivity(i);
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
        Log.w("user", user.toString());
        startActivity(new Intent(SignUp.this, MenuActivity.class));
    }
}