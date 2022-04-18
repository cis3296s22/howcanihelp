package edu.temple.howcanihelpapp;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class RequestInfoPost implements Parcelable {

    String title, dateTimePosted;
    boolean urgent, ableToRelocate;
    LatLng latlng;
    int postId;

    public RequestInfoPost(String newTitle, double lat, double lng, String dtp, boolean u, boolean atr) {
        postId = 0;
        title = newTitle;
        latlng = new LatLng(lat, lng);
        dateTimePosted = dtp;
        urgent = u;
        ableToRelocate = atr;
    }

    // update: RequestInfoPost to fetch additional information based on ID assigned from database
    public RequestInfoPost(int id, String newTitle, double lat, double lng, String dtp, boolean u, boolean atr) {
        postId = id;
        title = newTitle;
        latlng = new LatLng(lat, lng);
        dateTimePosted = dtp;
        urgent = u;
        ableToRelocate = atr;
    }

    protected RequestInfoPost(Parcel in) {
        title = in.readString();
        dateTimePosted = in.readString();
        urgent = in.readByte() != 0;
        ableToRelocate = in.readByte() != 0;
        latlng = in.readParcelable(LatLng.class.getClassLoader());
        postId = in.readInt();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(dateTimePosted);
        parcel.writeByte((byte) (urgent ? 1 : 0));
        parcel.writeByte((byte) (ableToRelocate ? 1 : 0));
    }
}
