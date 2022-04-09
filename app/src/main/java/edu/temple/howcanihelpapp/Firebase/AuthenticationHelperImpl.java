package edu.temple.howcanihelpapp.Firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.temple.howcanihelpapp.BuildConfig;

/**
 * The class for production use.
 */
public class AuthenticationHelperImpl implements AuthenticationHelper {
    private static final AuthenticationHelper authenticationHelper = new AuthenticationHelperImpl();
    private FirebaseAuth auth;
    private AuthenticationHelperImpl() {
        if (BuildConfig.USE_FIREBASE_EMULATORS)
            FirebaseAuth.getInstance().useEmulator("10.0.2.2",9099);
        this.auth = FirebaseAuth.getInstance();
    }

    @Override
    public User getUser() {
        return new UserImpl(this.auth.getCurrentUser());
    }

    /**
     * @return True if the user is authenticated (signed in).
     */
    public boolean isAuthenticated() {
        return this.auth.getCurrentUser() != null;
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
                    User fibaUser = new UserImpl(auth.getCurrentUser());
                    fibaUser.setName(name, new User.SetNameHandler() {
                        @Override
                        public void handle(boolean success) {
                            if(success)
                                handler.onCreateUserSuccess(fibaUser);
                            else
                                handler.onCreateUserFailure();
                        }
                    });
                    //fibaUser.setPhoneNumber(phoneNumber);
                } else {
                    handler.onCreateUserFailure();
                }
            }
        });
    }

    @Override
    public void signOut(SignOutEventHandler handler) {
        this.auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseAuth.removeAuthStateListener(this);
                if(isAuthenticated())
                    handler.onSignOutSuccess();
                else
                    handler.onSignOutFailure();
            }
        });
        this.auth.signOut();
    }

    public static AuthenticationHelper getInstance() {
        return authenticationHelper;
    }
}
