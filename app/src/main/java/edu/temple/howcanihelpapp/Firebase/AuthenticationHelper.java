package edu.temple.howcanihelpapp.Firebase;

public interface AuthenticationHelper {
    class UnauthenticatedUserException extends Exception {

        public UnauthenticatedUserException(String s) {
            super(s);
        }
    }
    User getUser() throws UnauthenticatedUserException;

    public interface OnCompleteHandler<T> {
        public void onComplete(T result);
    }

    /**
     * @return True if the user is authenticated (signed in).
     */
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
