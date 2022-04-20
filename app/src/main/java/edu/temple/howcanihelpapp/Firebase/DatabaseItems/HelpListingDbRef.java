package edu.temple.howcanihelpapp.Firebase.DatabaseItems;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.temple.howcanihelpapp.Firebase.DatabaseHelperImpl;
import edu.temple.howcanihelpapp.Firebase.DatabaseSetResult;
import edu.temple.howcanihelpapp.Firebase.DbRefImpl;

public class HelpListingDbRef extends DbRefImpl<HelpListing> {

    public interface CompletionListener {
        void onComplete(DatabaseSetResult<HelpListingDbRef> result);
    }

    public interface GetHelpListingsListener {
        void onComplete(Map<String, HelpListing> helpListingMap);
    }

    HelpListingDbRef(DatabaseReference reference) {
        super(reference);
    }

    /**
     * Pushes a HelpListing to the database
     * @param helpListing
     * @param completionListener
     * @return
     */
    public static HelpListingDbRef pushToDb(HelpListing helpListing, CompletionListener completionListener) {
        DatabaseReference dbRef = DatabaseHelperImpl.getInstance().getRef("helpListings")
                .push();
        HelpListingDbRef helpListingRef = new HelpListingDbRef(dbRef);
        helpListingRef.setValue(helpListing, completionListener);
        return helpListingRef;
    }

    private void setValue(HelpListing value, CompletionListener completionListener) {
        this.set(value, (error, ref) -> {
            Log.v("fd", "2.4");
            if(error != null) {
                Log.d(
                        "helpListingPush",
                        "Unsuccessful in pushing helpListing to database.\n" + error.getMessage()
                );
                completionListener.onComplete(new DatabaseSetResult<>(
                        DatabaseSetResult.DatabaseSetFailReason.None));
                Log.v("fd", "2.6");
                return;
            }
            Log.v("fd", "2.2");
            completionListener.onComplete(new DatabaseSetResult<>(new HelpListingDbRef(ref)));
        });
    }

    public void updateValues(HelpListingUpdate update, CompletionListener completionListener) {
        this.updateChildren(update.getHelpListingUpdatedValues(), (error, ref) -> {
            if(error != null)
                completionListener.onComplete(new DatabaseSetResult<>(
                        DatabaseSetResult.DatabaseSetFailReason.None));
            completionListener.onComplete(new DatabaseSetResult<>(new HelpListingDbRef(ref)));
        });
    }

    @Override
    protected HelpListing getFromSnapshot(@NonNull DataSnapshot snapshot) {
        return snapshot.getValue(HelpListing.class);
    }

    private static void getHelpListings(boolean isRequest, int numListings, GetHelpListingsListener getHelpListingsListener) {
        Query query = DatabaseHelperImpl.getInstance().getRef("helpListings")
                .orderByChild("isRequest").equalTo(true).limitToFirst(numListings);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("getRequestListings.onDataChange", "request listings data changed");
                Map<String, HelpListing> requestListings = new HashMap<>();
                for(DataSnapshot requestListingSnapshot: snapshot.getChildren()) {
                    requestListings.put(requestListingSnapshot.getKey(),
                            requestListingSnapshot.getValue(HelpListing.class));
                }
                getHelpListingsListener.onComplete(requestListings);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("getRequestListings", error.getMessage());
                getHelpListingsListener.onComplete(new HashMap<>());
            }
        });
    }

    public static void getDonationListings(int numListings, GetHelpListingsListener getDonationListings) {
        getHelpListings(false, numListings, getDonationListings);
    }

    public static void getRequestListings(int numListings, GetHelpListingsListener getRequestListingsListener) {
        Log.d("getRequestListings", "request listings");
        getHelpListings(true, numListings, getRequestListingsListener);
    }
}
