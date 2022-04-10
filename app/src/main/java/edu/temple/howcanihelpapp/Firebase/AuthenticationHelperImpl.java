package edu.temple.howcanihelpapp.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

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

    public boolean isAuthenticated() {
        return this.auth.getCurrentUser() != null;
    }

    public void createUser(String email, String password, String name, String phoneNumber, OnCompleteHandler<CreateUserResult> createUserHandler) throws Exception {
        if(this.isAuthenticated())
            throw new Exception("Could not create a new user since one is already signed in!");
        this.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    User fibaUser = new UserImpl(auth.getCurrentUser());
                    CreateUserResult res = new CreateUserResult(fibaUser);
                    fibaUser.setName(name, new User.SetNameHandler() {
                        @Override
                        public void handle(boolean success) {
                            createUserHandler.onComplete(res);
                        }
                    });
                    //fibaUser.setPhoneNumber(phoneNumber);
                } else {
                    Exception e = task.getException();
                    Log.w("createUser_w_EmailPassword", e);
                    CreateUserResult.CreateUserFailReason failReason;
                    if(e instanceof FirebaseAuthWeakPasswordException) {
                        createUserHandler.onComplete(new CreateUserResult(CreateUserResult.CreateUserFailReason.WeakPassword));
                    } else if(e instanceof FirebaseAuthInvalidCredentialsException) {
                        createUserHandler.onComplete(new CreateUserResult(CreateUserResult.CreateUserFailReason.MalformedEmail));
                    } else if(e instanceof FirebaseAuthUserCollisionException) {
                        createUserHandler.onComplete(new CreateUserResult(CreateUserResult.CreateUserFailReason.EmailExists));
                    } else {
                        createUserHandler.onComplete(new CreateUserResult(CreateUserResult.CreateUserFailReason.None));
                    }
                }
            }
        });
    }

    @Override
    public void signIn(String email, String password, OnCompleteHandler<SignInResult> signInHandler) {
        this.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    signInHandler.onComplete(new SignInResult(getUser()));
                } else {
                    Exception e = task.getException();
                    if(e instanceof FirebaseAuthInvalidUserException) {
                        String errorCode = ((FirebaseAuthInvalidUserException) e).getErrorCode();
                        Log.d("Error code sign in", errorCode);
                        switch (errorCode) {
                            case "ERROR_USER_NOT_FOUND":
                                signInHandler.onComplete(new SignInResult(SignInResult.SignInFailReason.UserNotFound));
                                break;
                            case "ERROR_USER_DISABLED":
                                signInHandler.onComplete(new SignInResult(SignInResult.SignInFailReason.UserDisabled));
                                break;
                            default:
                                signInHandler.onComplete(new SignInResult(SignInResult.SignInFailReason.None));
                        }
                    } else if(e instanceof FirebaseAuthInvalidCredentialsException) {
                        signInHandler.onComplete(new SignInResult(SignInResult.SignInFailReason.PasswordIncorrect));
                    } else {
                        signInHandler.onComplete(new SignInResult(SignInResult.SignInFailReason.None));
                    }
                }
            }
        });
    }

    @Override
    public void signOut(OnCompleteHandler<Boolean> signOutHandler) {
        this.auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            boolean registered = false;

            /**
             * The function will get triggered for the first time, right after it has been registered as a listener,
             * and a second time when a sign-out event is triggered.
             * @param firebaseAuth
             */
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(!registered) {
                    Log.d("SignOut", "Listener registered");
                    registered = true;
                    return;
                }
                if(isAuthenticated())
                    return;
                Log.d("SignOut", "sign out triggered.");
                auth = firebaseAuth;
                auth.removeAuthStateListener(this);
                signOutHandler.onComplete(true);
            }
        });
        this.auth.signOut();
    }

    public static AuthenticationHelper getInstance() {
        return authenticationHelper;
    }
}
