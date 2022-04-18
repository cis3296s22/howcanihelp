package edu.temple.howcanihelpapp;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class CustomMarker {

    GoogleMap gMap;
    RequestInfoPost rip;
    Marker marker;

    public CustomMarker(GoogleMap g, RequestInfoPost r) {
        gMap = g;
        rip = r;
        marker = this.getInstance();
    }

    private Marker getInstance() {
        Marker m = Objects.requireNonNull(gMap.addMarker(new MarkerOptions()
                .position(rip.latlng).title(rip.title)
                .snippet(rip.dateTimePosted)));
        m.setTag((rip.urgent ? "U" : "O"));
        return m;
    }

    public String getTitle() {
        return rip.title;
    }

    public String getDateTime() {
        return rip.dateTimePosted;
    }

    public LatLng getCoordinates() {
        return rip.latlng;
    }

    public boolean isUrgent() {
        return rip.urgent;
    }

    public boolean canRelocate() {
        return rip.ableToRelocate;
    }
}

