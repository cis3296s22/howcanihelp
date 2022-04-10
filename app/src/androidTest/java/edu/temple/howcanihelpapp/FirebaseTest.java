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
import edu.temple.howcanihelpapp.Firebase.SignInResult;
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

        if(!authenticationHelper.isAuthenticated())
            return;

        authenticationHelper.signOut(result -> signOutRes.complete(result));

        if(!signOutRes.get()) {
            fail("Could not sign the user out!\nUser: " + authenticationHelper.getUser().toString());
        }
    }

    /**
     * Tests the following:
     * - Creating a user with email and password.
     * - Signing them out.
     * - Signing them back in.
     * @throws Exception
     */
    @Test
    public void testAuthenticationHelper() throws Exception {
        CompletableFuture<CreateUserResult> createRes = new CompletableFuture<>();
        AuthenticationHelper authHelper = AuthenticationHelperImpl.getInstance();
        assertTrue("Expect no user to be authenticated before attempting to create a user.", !authHelper.isAuthenticated());

        UserI user = new UserI();
        authHelper.createUser(
                user.getEmail(),
                user.getPassword(),
                user.getDisplayName(),
                user.getPhoneNumber(),
                result -> createRes.complete(result)
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
            switch (res.getFailReason()) {
                case EmailExists:
                    Log.w("createUser", "Email is already in use");
                    break;
                case WeakPassword:
                    Log.w("createUser", "Weak password");
                    break;
                case MalformedEmail:
                    Log.w("createUser", "Malformed email");
                    break;
                case None:
                default:

            }
            fail("Unsuccessful in creating a user!");
        }

        CompletableFuture<Boolean> signOutResFuture = new CompletableFuture<>();
        authHelper.signOut((result) -> {
            Log.d("signOutRes", String.valueOf(result));
            signOutResFuture.complete(result);
        });
        assertTrue(signOutResFuture.get());

        CompletableFuture<SignInResult> signInResFuture = new CompletableFuture<>();
        authHelper.signIn(
                user.getEmail(),
                user.getPassword(),
                result -> signInResFuture.complete(result)
        );

        SignInResult signInResult = signInResFuture.get();
        if(!signInResult.isSuccessful())
            fail("Expected the sign in to be successful. Reason: " + signInResult.getFailReason().toString());
    }
}
