package edu.temple.howcanihelpapp;

import static org.junit.Assert.*;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListing;
import edu.temple.howcanihelpapp.Firebase.AuthenticationHelper;
import edu.temple.howcanihelpapp.Firebase.AuthenticationHelperImpl;
import edu.temple.howcanihelpapp.Firebase.CreateUserResult;
import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListingBuilder;
import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListingUpdate;
import edu.temple.howcanihelpapp.Firebase.DatabaseSetResult;
import edu.temple.howcanihelpapp.Firebase.DatabaseItems.HelpListingDbRef;
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

    AuthenticationHelper authenticationHelper;

    UserI createUser() throws Exception {
        CompletableFuture<CreateUserResult> createRes = new CompletableFuture<>();
        UserI user = new UserI();
        authenticationHelper.createUser(
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
        return  user;
    }

    @Before
    public void resetAuth() throws Exception {
        authenticationHelper = AuthenticationHelperImpl.getInstance();
        CompletableFuture<Boolean> signOutRes = new CompletableFuture<>();

        if(!authenticationHelper.isAuthenticated())
            return;
        else
            FirebaseAuth.getInstance().getCurrentUser().delete();

        authenticationHelper.signOut(result -> signOutRes.complete(result));

        if(!signOutRes.get()) {
            fail("Could not sign the user out!\nUser: " + authenticationHelper.getUser().toString());
        }
    }

    /**
     * Tests creating a user with email and password.
     * @throws Exception
     */
    @Test
    public void testCreateUser() throws Exception {
        assertTrue("Expect no user to be authenticated before attempting to create a user.", !authenticationHelper.isAuthenticated());
        createUser();
    }

    /**
     * Tests signing a user out.
     * @throws Exception
     */
    @Test
    public void testSignOut() throws Exception {
        if(!authenticationHelper.isAuthenticated()) {
            createUser();
        }

        CompletableFuture<Boolean> signOutResFuture = new CompletableFuture<>();
        authenticationHelper.signOut((result) -> {
            Log.d("signOutRes", String.valueOf(result));
            signOutResFuture.complete(result);
        });
        assertTrue("Expected the sign out to be successful.", signOutResFuture.get());
    }

    /**
     * Tests signing a user back in after signing them out.
     * @throws Exception
     */
    @Test
    public void testSignIn() throws Exception {
        UserI userI = null;
        if(!authenticationHelper.isAuthenticated())
            userI = createUser();
        testSignOut();

        CompletableFuture<SignInResult> signInResFuture = new CompletableFuture<>();
        authenticationHelper.signIn(
                userI.getEmail(),
                userI.getPassword(),
                result -> signInResFuture.complete(result)
        );

        SignInResult signInResult = signInResFuture.get();
        if(!signInResult.isSuccessful())
            fail("Expected the sign in to be successful. Reason: " + signInResult.getFailReason().toString());
    }

    DatabaseSetResult<HelpListingDbRef> pushToDatabase() throws Exception {
        HelpListing helpListing = new HelpListingBuilder()
                .setTitle("canned food")
                .setDescription("I want canned food.")
                .setLocation(new HelpListing.Location("here", 1, -1))
                .getHelpListing();
        CompletableFuture<DatabaseSetResult<HelpListingDbRef>> databasePushResult = new CompletableFuture<>();
        Log.v("fd", "1");
        /**
         * Append the help listing to the main list and index it in the user's list.
         */
        HelpListingDbRef.pushToDb(helpListing, x -> databasePushResult.complete(x));

        Log.v("fd", "2");
        DatabaseSetResult<HelpListingDbRef> res1 = databasePushResult.get();

        Log.v("fd", "3");
        if(!res1.isSuccessful())
            fail("Expected the database set to be successful!");
        res1.getSetData().get().uid = "okokokok"; // Attempt to change so as to prove a new copy is returned every get() call
        HelpListing gettedHelpListing = res1.getSetData().get();
        assertEquals("Title is the same", gettedHelpListing.title, helpListing.title);
        assertEquals("UID is the same", gettedHelpListing.uid, helpListing.uid);
        return res1;
    }

    /**
     * Test if a user can put an item in the database.
     */
    @Test
    public void testPushToDatabase() throws Exception {
        createUser();
        pushToDatabase();
    }

    /**
     * Tests if a user can updated an item in the database.
     * @throws Exception
     */
    @Test
    public void testUpdateOnDatabase() throws Exception {
        createUser();
        DatabaseSetResult<HelpListingDbRef> res1 = pushToDatabase();
        HelpListing helpListing = res1.getSetData().get();
        // Test again
        CompletableFuture<DatabaseSetResult<HelpListingDbRef>> databasePushResult2 = new CompletableFuture<>();

        HelpListingUpdate helpListingUpdate = new HelpListingUpdate(helpListing)
                .update(new HelpListingUpdate.UpdateBuilder() {
                    @Override
                    public void builder(HelpListingBuilder update) {
                        update
                                .setTitle("Bagged Food")
                                .setDescription("I wanted to say bagged food.")
                                .setAsRequest()
                                .setIsUrgent(true)
                                .setCanRelocate(false)
                                .setLocation(new HelpListing.Location("IT is here actually",
                                        -1,1));
                    }
                });
        String oldKey = res1.getSetData().getKey();
        res1.getSetData().updateValues(helpListingUpdate, x -> databasePushResult2.complete(x));
        if(!databasePushResult2.get().isSuccessful())
            fail();
        assertEquals("Expected the updated item has the same key as before its update.",
                databasePushResult2.get().getSetData().getKey(), oldKey);
        assertEquals("Expected the updated item to have an updated title",
                databasePushResult2.get().getSetData().get().title, "Bagged Food");
    }

    /**
     * Tests if a user can fetch from the database.
     * @throws Exception
     */
    @Test
    public void testFetchFromDatabase() throws Exception {
        createUser();
        DatabaseSetResult<HelpListingDbRef> dbRefDatabaseSetResult = pushToDatabase();
        CompletableFuture<Boolean> isDone3 = new CompletableFuture<>();
        HelpListingDbRef.getHelpListings(100, new HelpListingDbRef.GetHelpListingsListener() {
            @Override
            public void onComplete(Map<String, HelpListing> helpListingMap) {
                assertNotNull(helpListingMap);
                AtomicBoolean found = new AtomicBoolean(false);
                String lookingForKey = dbRefDatabaseSetResult.getSetData().getKey();
                Log.i("Fetch from database", "Looking for data with key: " + lookingForKey);
                helpListingMap.forEach((key, val) -> {
                    Log.i("Fetch from database", "Key: " + key + " | Value: " + val.title + " | isRequest: " + Boolean.toString(val.isRequest));
                    if(key.equals(lookingForKey)) {
                        Log.i("Fetch from database", "Found the key.");
                        found.set(true);
                    }
                });
                isDone3.complete(found.get());
            }
        });
        assertTrue("Expected the pushed data to be fetched from the database.", isDone3.get());
    }

}
