package edu.temple.howcanihelpapp.Firebase;

import java.util.function.Consumer;

public interface AuthenticationHelper {
    User getUser();

    public interface OnCompleteHandler<T> {
        public void onComplete(T result);
    }

    public boolean isAuthenticated();

    /**
     * UI changes can be done in the handler.
     * @param email
     * @param password
     * @param name
     * @param phoneNumber
     * @param createUserHandler
     * @throws Exception
     */
    public void createUser(String email, String password, String name, String phoneNumber, OnCompleteHandler<CreateUserResult> createUserHandler) throws Exception;

    public void signIn(String email, String password, OnCompleteHandler<SignInResult> signInHandler);

    /**
     * UI changes can be done in the handler.
     * @param signOutHandler
     */
    public void signOut(OnCompleteHandler<Boolean> signOutHandler);
}
