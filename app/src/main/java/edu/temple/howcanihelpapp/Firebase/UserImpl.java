package edu.temple.howcanihelpapp.Firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

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

    public String getUid()
    { return fibaUser.getUid(); }

    @Override
    public void setPhoneNumber(String phoneNumber) throws Exception {
        throw new Exception("Not yet implemented.");
    }

    @Override
    public void setName(String name, SetNameHandler handler) {
        this.fibaUser.updateProfile(
                new UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()
        ).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                handler.handle(task.isSuccessful());
            }
        }); // Wait until the name is updated.
    }

    @Override
    public String toString() {
        return "Name: " + getDisplayName() + "\nEmail: " + getEmail() + "\nPhone number: " + getPhoneNumber() + "\nUid: " + getUid();
    }
}
