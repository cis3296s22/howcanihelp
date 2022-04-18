package edu.temple.howcanihelpapp.Firebase;

import com.google.firebase.database.DatabaseReference;

public interface DbRef {
    void set(Object value, DatabaseReference.CompletionListener completionListener);

    void setChild(String path, Object value, DatabaseReference.CompletionListener completionListener);

    String getKey();
}
