package edu.temple.howcanihelpapp;

import static org.junit.Assert.*;

import android.util.Log;

import org.junit.Test;

import edu.temple.howcanihelpapp.Firebase.AuthenticationHelper;
import edu.temple.howcanihelpapp.Firebase.AuthenticationHelperImpl;
import edu.temple.howcanihelpapp.Firebase.User;

public class FirebaseTest {

    @Test
    public void createUser() throws Exception {
        AuthenticationHelper authHelper = AuthenticationHelperImpl.getInstance();
        assertTrue("Expect no user to be authenticated before attempting to create a user.", !authHelper.isAuthenticated());
        authHelper.createUser(
                "mendez.joodnataohan3099@gmail.com",
                "test1234password",
                "Jondo",
                "111111111",
                new AuthenticationHelperImpl.CreateUserHandler() {
                    @Override
                    public void onCreateUserSuccess(User fibaUser) {
                        Log.d(
                                "createUser",
                                "Success in creating a user!" +
                                "\nName: " + fibaUser.getDisplayName() +
                                "\nEmail: " + fibaUser.getEmail() +
                                "\nPhoneNumber: " + fibaUser.getPhoneNumber() +
                                "\nUid: " + fibaUser.getUid()
                        );
                    }

                    @Override
                    public void onCreateUserFailure() {
                        Log.w("createUser", "Unsuccessful in creating a user!");
                        fail("Unsuccessful in creating a user!");
                    }
                }
        );
    }

//    @Test
//    public void signOutUser() {
//        AuthenticationHelper authHelper = AuthenticationHelperImpl.getInstance();
//        authHelper.signOut(new AuthenticationHelper.SignOutEventHandler() {
//            @Override
//            public void onSignOutSuccess() {
//                assertFalse("Expected the user to not be authenticated.", authHelper.isAuthenticated());
//                Log.d("signOutUser", "The user was signed out.");
//            }
//
//            @Override
//            public void onSignOutFailure() {
//                assert(true);
//            }
//        });
//    }

    @Test
    public void getUserInfo() {
        AuthenticationHelper authenticationHelper = AuthenticationHelperImpl.getInstance();
        assertTrue(authenticationHelper.isAuthenticated());
        User fibaUser = authenticationHelper.getUser();
        Log.d("getUserInfo: ", String.valueOf(fibaUser.getDisplayName().length()));
        Log.d("getUserInfo",
                "Success in getting userinfo" +
                        "\nName: " + fibaUser.getDisplayName() +
                        "\nEmail: " + fibaUser.getEmail() +
                        "\nPhoneNumber: " + fibaUser.getPhoneNumber() +
                        "\nUid: " + fibaUser.getUid()
        );
    }
}
