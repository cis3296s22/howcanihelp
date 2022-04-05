package edu.temple.howcanihelpapp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.temple.howcanihelpapp.Firebase.AuthenticationHelper;
import edu.temple.howcanihelpapp.Firebase.User;

public class FirebaseTest {
    @Test
    public void createUser() throws Exception {
        AuthenticationHelper authHelper = new AuthenticationHelper();
        assertTrue("Expect no user to be authenticated before attempting to create a user.", !authHelper.isAuthenticated());
        authHelper.createUser(
                "mendez.jonathan3099@gmail.com",
                "test1234password",
                "Jon",
                "111111111",
                new AuthenticationHelper.CreateUserHandler() {
                    @Override
                    public void onCreateUserSuccess(User fibaUser) {
                        System.out.println("Success in creating a user!");
                        // name
                        // email
                        // phoneNumber
                        // uid
                    }

                    @Override
                    public void onCreateUserFailure() {
                        assertTrue("Unsuccessful in creating a user!", false);
                    }
                }
        );
    }
}
