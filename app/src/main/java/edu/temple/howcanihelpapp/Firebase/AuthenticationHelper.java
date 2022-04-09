package edu.temple.howcanihelpapp.Firebase;

public interface AuthenticationHelper {
    User getUser();

    public interface CreateUserHandler {
        void handle(CreateUserResult res);
    }

    public interface SignOutEventHandler {
        void handle(boolean success);
    }

    public boolean isAuthenticated();

    /**
     * UI changes can be done in the handler.
     * @param email
     * @param password
     * @param name
     * @param phoneNumber
     * @param handler
     * @throws Exception
     */
    public void createUser(String email, String password, String name, String phoneNumber, AuthenticationHelperImpl.CreateUserHandler handler) throws Exception;

    /**
     * UI changes can be done in the handler.
     * @param handler
     */
    public void signOut(SignOutEventHandler handler);
}
