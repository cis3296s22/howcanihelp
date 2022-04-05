package edu.temple.howcanihelpapp.Firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationHelperImpl implements AuthenticationHelper {
    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    public AuthenticationHelperImpl() {
        this.auth = FirebaseAuth.getInstance();
        this.currentUser = auth.getCurrentUser();
    }

    /**
     * @return True if the user is authenticated (signed in).
     */
    public boolean isAuthenticated() {
        return currentUser != null;
    }

    /**
     * An example of how to utilize the CreateUserHandler parameter is in the "createUser()" test in the
     * "androidTest" package.
     * @param email
     * @param password
     * @param name
     * @param phoneNumber
     * @param handler
     * @throws Exception
     */
    public void createUser(String email, String password, String name, String phoneNumber, CreateUserHandler handler) throws Exception {
        if(this.isAuthenticated())
            throw new Exception("Could not create a new user since one is already signed in!");
        this.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    UserImpl fibaUser = new UserImpl(auth.getCurrentUser());
                    handler.onCreateUserSuccess(fibaUser);
                } else {
                    handler.onCreateUserFailure();
                }
            }
        });
    }
}
