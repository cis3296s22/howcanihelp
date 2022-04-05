package edu.temple.howcanihelpapp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.temple.howcanihelpapp.Firebase.AuthenticationHelper;
import edu.temple.howcanihelpapp.Firebase.AuthenticationHelperImpl;
import edu.temple.howcanihelpapp.Firebase.User;

public class FirebaseTest {
    class AuthHelperImplTest implements AuthenticationHelper {

        @Override
        public boolean isAuthenticated() {
            return false;
        }

        @Override
        public void createUser(String email, String password, String name, String phoneNumber, AuthenticationHelperImpl.CreateUserHandler handler) throws Exception {

        }
    }

    class UserImplTest implements User {

        @Override
        public String getDisplayName() {
            return null;
        }

        @Override
        public String getEmail() {
            return null;
        }

        @Override
        public String getPhoneNumber() {
            return null;
        }
    }

    @Test
    public void createUser() throws Exception {
        AuthenticationHelperImpl authHelper = new AuthenticationHelperImpl();
        assertTrue("Expect no user to be authenticated before attempting to create a user.", !authHelper.isAuthenticated());
        authHelper.createUser(
                "mendez.jonathan3099@gmail.com",
                "test1234password",
                "Jon",
                "111111111",
                new AuthenticationHelperImpl.CreateUserHandler() {
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
