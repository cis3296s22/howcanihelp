package edu.temple.howcanihelpapp.Firebase;

public class SignInResult {
    public enum SignInFailReason {
        None,
        UserNotFound,
        UserDisabled,
        PasswordIncorrect
    }

    private User user;
    private SignInFailReason failReason = SignInFailReason.None;

    public SignInResult(User user) {
        this.user = user;
    }

    public SignInResult(SignInFailReason failReason) {
        this.user = null;
        this.failReason = failReason;
    }

    public SignInFailReason getFailReason() {
        return failReason;
    }

    public boolean isSuccessful() {
        return user != null;
    }

    public User getUser() {
        return user;
    }
}
