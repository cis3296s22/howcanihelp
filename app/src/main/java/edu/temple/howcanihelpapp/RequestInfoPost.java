package edu.temple.howcanihelpapp;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 *  The purpose of this class is to provide a unit of information for map markers
 *  where users are able to recognize information regarding a user's donation or request posting
 */
public class RequestInfoPost implements Parcelable {

    /**
     *  title indicates the main heading that captures the essence of what the user looks for
     *  dateTimePosted indicates the time and date that the post was published onto the app
     *  urgent indicates whether a request is high priority
     *  ableToRelocate indicates whether user is free to locate elsewhere, related to issues regarding mobility (e.g. disability)
     *  latlng indicates the coordinates where the marker is located
     *  postId is the ID number for the RequestInfoPost that is made
     */
    String title, dateTimePosted;
    boolean urgent, ableToRelocate;
    LatLng latlng;
    int postId;

    /**
     * Constructor for RequestInfoPost, all fields are required to fulfill a complete window of information when
     * user views marker information and/or marker's post activity
     *
     * @param newTitle is a String
     * @param lat is a double
     * @param lng id a double
     * @param dtp is a String
     * @param u is a boolean value
     * @param atr is a boolean value
     */
    public RequestInfoPost(String newTitle, double lat, double lng, String dtp, boolean u, boolean atr) {
        postId = 0;
        title = newTitle;
        latlng = new LatLng(lat, lng);
        dateTimePosted = dtp;
        urgent = u;
        ableToRelocate = atr;
    }

    /**
     * Alternate constructor to include the ID number of the RequestInfoPost made
     *
     * @param id
     * @param newTitle
     * @param lat
     * @param lng
     * @param dtp
     * @param u
     * @param atr
     */
    // update: RequestInfoPost to fetch additional information based on ID assigned from database
    public RequestInfoPost(int id, String newTitle, double lat, double lng, String dtp, boolean u, boolean atr) {
        postId = id;
        title = newTitle;
        latlng = new LatLng(lat, lng);
        dateTimePosted = dtp;
        urgent = u;
        ableToRelocate = atr;
    }

    /**
     * Parcelable implementation of RequestInfoPost, needed when the information is passed between the MapsActivity
     * and PostActivity. The marker sets the identified RequestInfoPost as its tag before passing.
     *
     * @param in
     */
    protected RequestInfoPost(Parcel in) {
        title = in.readString();
        dateTimePosted = in.readString();
        urgent = in.readByte() != 0;
        ableToRelocate = in.readByte() != 0;
        latlng = in.readParcelable(LatLng.class.getClassLoader());
        postId = in.readInt();
    }

    /**
     *  Default method as part of the Parcelable interface, CREATOR generates instances of
     *  the RequestInfoPost parcelable implementation from a Parcel. Methods help create a new instance
     *  and create a new array of the Parcelable class
     */
    public static final Creator<RequestInfoPost> CREATOR = new Creator<RequestInfoPost>() {
        @Override
        public RequestInfoPost createFromParcel(Parcel in) {
            return new RequestInfoPost(in);
        }

        @Override
        public RequestInfoPost[] newArray(int size) {
            return new RequestInfoPost[size];
        }
    };

    /**
     * Default method as part of the Parcelable interface, describes the kinds of
     * special objects contained in the Parcelable instance's marshaled representation
     * More info: https://developer.android.com/reference/android/os/Parcelable#describeContents()
     *
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Default method as part of the Parcelable interface, converts object to a Parcel object
     * More info: https://developer.android.com/reference/android/os/Parcelable#writeToParcel(android.os.Parcel,%20int)
     *
     * @param parcel
     * @param i
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(dateTimePosted);
        parcel.writeByte((byte) (urgent ? 1 : 0));
        parcel.writeByte((byte) (ableToRelocate ? 1 : 0));
    }
}
