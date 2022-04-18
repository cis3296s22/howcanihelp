package edu.temple.howcanihelpapp.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class DbRefImpl<T extends Object> {

    private DatabaseReference reference;

    protected DbRefImpl(DatabaseReference reference) {
        this.reference = reference;
    }

    protected abstract T getFromSnapshot(@NonNull DataSnapshot snapshot);

    protected void set(T value, DatabaseReference.CompletionListener completionListener) {
        this.reference.setValue(value, completionListener);
    }

    void setChild(String path, Object value, DatabaseReference.CompletionListener completionListener) {
        this.reference.child(path).setValue(value, completionListener);
    }

    protected void updateChildren(Map<String, Object> values, DatabaseReference.CompletionListener completionListener) {
        this.reference.updateChildren(values, completionListener);
    }

    /**
     *
     * @return Cached value (if there is one) or gets it from the database.
     */
    public T get() {
        CompletableFuture<T> valueFuture = new CompletableFuture<>();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                valueFuture.complete(getFromSnapshot(snapshot));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DbRef.get.reference.addListener.onCancelled", "errorrrrr");
                valueFuture.complete(null);
            }
        });
        try {
            return valueFuture.get();
        } catch (Exception e) {
            Log.e("DbRef.get", "could not get dbref value", e);
            return null;
        }
    }

    public String getKey() {
        return this.reference.getKey();
    }
}
