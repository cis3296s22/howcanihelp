package edu.temple.howcanihelpapp.Firebase;

public class CreateUserResult {
    private User user;

    public CreateUserResult(User createdUser) {
        this.user = createdUser;
    }

    public boolean isSuccessful() {
        return this.user != null;
    }

    public User getCreatedUser() {
        return this.user;
    }
}
