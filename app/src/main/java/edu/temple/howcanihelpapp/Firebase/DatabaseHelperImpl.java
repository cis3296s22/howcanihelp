package edu.temple.howcanihelpapp.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.temple.howcanihelpapp.BuildConfig;

public class DatabaseHelperImpl implements DatabaseHelper {
    private static final DatabaseHelper databaseHelper = new DatabaseHelperImpl();
    FirebaseDatabase firebaseDatabase;

    DatabaseHelperImpl() {
        if (BuildConfig.USE_FIREBASE_EMULATORS)
            FirebaseDatabase.getInstance().useEmulator("10.0.2.2",9000);
        this.firebaseDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public DatabaseReference getRef(String path) {
        return this.firebaseDatabase.getReference(path);
    }

    public static DatabaseHelper getInstance() {
        return databaseHelper;
    }
}
