package edu.temple.howcanihelpapp.Firebase;

import com.google.firebase.database.DatabaseReference;

public interface DatabaseHelper {
    DatabaseReference getRef(String path);
}
