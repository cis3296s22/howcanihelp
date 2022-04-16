package edu.temple.howcanihelpapp.Firebase.DatabaseItems;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import edu.temple.howcanihelpapp.Firebase.DatabaseHelperImpl;
import edu.temple.howcanihelpapp.Firebase.DatabaseSetResult;
import edu.temple.howcanihelpapp.Firebase.DbRefImpl;

public class HelpListingDbRef extends DbRefImpl<HelpListing> {

    public interface CompletionListener {
        void onComplete(DatabaseSetResult<HelpListingDbRef> result);
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
}
