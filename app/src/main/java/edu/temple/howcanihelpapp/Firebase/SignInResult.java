package edu.temple.howcanihelpapp.Firebase;

/**
 * Sign in result from Firebase Authentication through email + password.
 */
public class SignInResult {
    /**
     * Reasons as to why a sign in has failed.
     */
    public enum SignInFailReason {
        /**
         * No reason was specified.
         */
        None,
        /**
         * The email address did not belong to a user.
         */
        UserNotFound,
        /**
         * The user account is disabled.
         */
        UserDisabled,
        /**
         * The password was incorrect.
         */
        PasswordIncorrect
    }

    private User user;
    private SignInFailReason failReason = SignInFailReason.None;

    /**
     * The failReason property will be "None".
     * @param user
     */
    public SignInResult(User user) {
        this.user = user;
    }

    /**
     * The user property will be null.
     * @param failReason
     */
    public SignInResult(SignInFailReason failReason) {
        this.user = null;
        this.failReason = failReason;
    }

    /**
     * @return If the sign in failed, return a good reason.
     */
    public SignInFailReason getFailReason() {
        return failReason;
    }

    /**
     * @return True if the sign in was successful.
     */
    public boolean isSuccessful() {
        return user != null;
    }

    /**
     * @return A User object if the sign in was successful, null otherwise.
     */
    public User getUser() {
        return user;
    }
}
