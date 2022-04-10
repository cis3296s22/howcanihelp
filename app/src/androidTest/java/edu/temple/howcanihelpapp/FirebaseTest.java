package edu.temple.howcanihelpapp;

import static org.junit.Assert.*;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import edu.temple.howcanihelpapp.Firebase.AuthenticationHelper;
import edu.temple.howcanihelpapp.Firebase.AuthenticationHelperImpl;
import edu.temple.howcanihelpapp.Firebase.CreateUserResult;
import edu.temple.howcanihelpapp.Firebase.User;

public class FirebaseTest {
    static private int userNo = 0;

    class UserI {
        String displayName;
        String email;
        String password;
        String phoneNumber;

        UserI() {
            displayName = String.format("user%d", userNo);
            email = String.format("%s@email.test", displayName);
            password = String.format("passwordFor%s", displayName);
            phoneNumber = String.format("%10d", userNo).replace(' ', '0');
            userNo++;
        }

        String getDisplayName() {
            return displayName;
        }

        String getEmail() {
            return email;
        }

        String getPassword() {
            return password;
        }

        String getPhoneNumber() {
            return phoneNumber;
        }
    }

    @Before
    public void resetAuth() throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> signOutRes = new CompletableFuture<>();
        AuthenticationHelper authenticationHelper = AuthenticationHelperImpl.getInstance();
        authenticationHelper.signOut(new AuthenticationHelper.SignOutEventHandler() {
            @Override
            public void handle(boolean success) {
                signOutRes.complete(success);
            }
        });

        if(!signOutRes.get()) {
            fail("Could not sign the user out!\nUser: " + authenticationHelper.getUser().toString());
        }
    }

    @Test
    public void createUser() throws Exception {
        CompletableFuture<CreateUserResult> createRes = new CompletableFuture<>();
        AuthenticationHelper authHelper = AuthenticationHelperImpl.getInstance();
        assertTrue("Expect no user to be authenticated before attempting to create a user.", !authHelper.isAuthenticated());

        UserI user = new UserI();
        authHelper.createUser(
                user.getEmail(),
                user.getPassword(),
                user.getDisplayName(),
                user.getPhoneNumber(),
                new AuthenticationHelperImpl.CreateUserHandler() {
                    @Override
                    public void handle(CreateUserResult res) {
                        createRes.complete(res);
                    }
                }
        );

        CreateUserResult res = createRes.get();
        if(res.isSuccessful()) {
            User fibaUser = res.getCreatedUser();
            Log.d(
                    "createUser",
                    "Success in creating a user!" +
                            "\nName: " + fibaUser.getDisplayName() +
                            "\nEmail: " + fibaUser.getEmail() +
                            "\nPhoneNumber: " + fibaUser.getPhoneNumber() +
                            "\nUid: " + fibaUser.getUid()
            );
        } else {
            Log.w("createUser", "Unsuccessful in creating a user!");
            fail("Unsuccessful in creating a user!");
        }
    }
}
