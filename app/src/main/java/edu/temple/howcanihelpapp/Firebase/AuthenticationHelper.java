package edu.temple.howcanihelpapp.Firebase;

public interface AuthenticationHelper {
    public interface CreateUserHandler {
        void onCreateUserSuccess(User fibaUser);
        void onCreateUserFailure();
    }

    public boolean isAuthenticated();
    public void createUser(String email, String password, String name, String phoneNumber, AuthenticationHelperImpl.CreateUserHandler handler) throws Exception;
}
