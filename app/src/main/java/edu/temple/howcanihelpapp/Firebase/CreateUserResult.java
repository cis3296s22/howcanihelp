package edu.temple.howcanihelpapp.Firebase;

/**
 * Create user result from Firebase authentication through email + password.
 */
public class CreateUserResult {
    /**
     * Reasons as to why user creation failed.
     */
    public enum CreateUserFailReason {
        /**
         * No reason was specified.
         */
        None,
        /**
         * The password needs to be more secure.
         */
        WeakPassword,
        /**
         * The "email" string supplied is not a valid format for an email address.
         */
        MalformedEmail,
        /**
         * A user already exists with the supplied email.
         */
        EmailExists
    }
    private User user;
    private CreateUserFailReason failReason = CreateUserFailReason.None;

    /**
     * The failReason property will be "None".
     * @param createdUser
     */
    public CreateUserResult(User createdUser) {
        this.user = createdUser;
    }

    /**
     * The user property will be null.
     * @param failReason
     */
    public CreateUserResult(CreateUserFailReason failReason) {
        this.user = null;
        this.failReason = failReason;
    }

    /**
     * @return True if the creation was successful.
     */
    public boolean isSuccessful() {
        return this.user != null;
    }

    /**
     * @return A User object if the creation was successful, null otherwise.
     */
    public User getCreatedUser() {
        return this.user;
    }

    /**
     * @return If the creating a user failed, return a good reason.
     */
    public CreateUserFailReason getFailReason() {
        return this.failReason;
    }
}
