package edu.temple.howcanihelpapp.Firebase;

public class CreateUserResult {
    public enum CreateUserFailReason {
        None,
        WeakPassword,
        MalformedEmail,
        EmailExists
    }
    private User user;
    private CreateUserFailReason failReason = CreateUserFailReason.None;

    public CreateUserResult(User createdUser) {
        this.user = createdUser;
    }

    public CreateUserResult(CreateUserFailReason failReason) {
        this.user = null;
        this.failReason = failReason;
    }

    public boolean isSuccessful() {
        return this.user != null;
    }

    public User getCreatedUser() {
        return this.user;
    }

    public CreateUserFailReason getFailReason() {
        return this.failReason;
    }
}
