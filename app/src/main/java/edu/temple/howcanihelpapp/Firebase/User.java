package edu.temple.howcanihelpapp.Firebase;

/**
 * Please implement this interface if you want to test anything User related but do
 * not want to make use of Firebase backend.
 */
public interface User {
    public String getDisplayName();
    public String getEmail();
    public String getPhoneNumber();
}
