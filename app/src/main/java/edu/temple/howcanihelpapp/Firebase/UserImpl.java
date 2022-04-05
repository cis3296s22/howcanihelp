package edu.temple.howcanihelpapp.Firebase;

import com.google.firebase.auth.FirebaseUser;

/**
 * Represents the User in the Firebase backend.
 */
public class UserImpl implements User {
    // fibaUser <- fiba user <- (fi)re(ba)se user <- firebase user
    private FirebaseUser fibaUser;
    UserImpl(FirebaseUser fibaUser) {
        this.fibaUser = fibaUser;
    }

    public String getDisplayName()
    { return fibaUser.getDisplayName(); }

    public String getEmail()
    { return fibaUser.getEmail(); }

    public String getPhoneNumber()
    { return fibaUser.getPhoneNumber(); }
}
