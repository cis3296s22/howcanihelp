package edu.temple.howcanihelpapp.Firebase.DatabaseItems;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class HelpListing {
    @IgnoreExtraProperties
    public static class Location {
        public String address;
        public float latitude;
        public float longitude;

        public Location() {

        }

        public Location(String address, float latitude, float longitude) {
            this.address = address;
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    public boolean isRequest;
    public String title;
    public String uid;
    public String description;
    public Location location;
    public boolean isUrgent;
    public boolean canRelocate;
    /**
     * Time represented in milliseconds. Use new Date(timePostedMs) to get a Date object.
     */
    public long timePostedMs;

    public HelpListing() {

    }

    /**
     *
     * @param uid User Id
     * @param isRequest Is this listing for a Request, or for a Donation.
     * @param title
     * @param description
     * @param location
     * @param isUrgent
     * @param canRelocate
     */
    public HelpListing(String uid, boolean isRequest, String title, String description, Location location, boolean isUrgent, boolean canRelocate, long timePostedMs) {
        this.uid = uid;
        this.isRequest = isRequest;
        this.title = title;
        this.description = description;
        this.location = location;
        this.isUrgent = isUrgent;
        this.canRelocate = canRelocate;
        this.timePostedMs = timePostedMs;
    }

    @Override
    public HelpListing clone() {
        return new HelpListing(
                this.uid,
                this.isRequest,
                this.title,
                this.description,
                new Location(this.location.address, this.location.latitude, this.location.longitude),
                this.isUrgent,
                this.canRelocate,
                this.timePostedMs
        );
    }
}
