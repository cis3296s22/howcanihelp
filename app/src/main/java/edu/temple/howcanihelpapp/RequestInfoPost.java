package edu.temple.howcanihelpapp;

import com.google.android.gms.maps.model.LatLng;

public class RequestInfoPost {

    String title, dateTimePosted;
    boolean urgent, ableToRelocate;
    LatLng latlng;

    public RequestInfoPost(String newTitle, double lat, double lng, String dtp, boolean u, boolean atr) {
        title = newTitle;
        latlng = new LatLng(lat, lng);
        dateTimePosted = dtp;
        urgent = u;
        ableToRelocate = atr;
    }
}
