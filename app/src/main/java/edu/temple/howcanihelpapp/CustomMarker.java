package edu.temple.howcanihelpapp;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

/**
 * The purpose of this class is to create a custom marker that can be used in the app layout
 */
public class CustomMarker {
    /**
     * gMap is GoogleMap instance
     * rip is a RequestInfoPost instance
     * marker is a Marker instance
     */
    GoogleMap gMap;
    RequestInfoPost rip;
    Marker marker;

    /**
     * CustomMarker(...) is the constructor method
     * @param g is the GoogleMap instance
     * @param r is the RequestInfoPost instance
     */
    public CustomMarker(GoogleMap g, RequestInfoPost r) {
        gMap = g;
        rip = r;
        marker = this.getInstance();
    }

    /**
     * getInstance() returns a marker from gMap at the correct position
     * @return Marker m
     */
    private Marker getInstance() {
        Marker m = Objects.requireNonNull(gMap.addMarker(new MarkerOptions()
                .position(rip.latlng)));
        m.setTag(rip);
        return m;
    }

    /**
     * getTitle() returns a String that is the title from RequestInfoPost
     * @return rip.title
     */
    public String getTitle() {
        return rip.title;
    }

    /**
     * getDateTime() returns a String that is the date and time of RequestInfoPost
     * @return rip.dateTimePosted
     */
    public String getDateTime() {
        return rip.dateTimePosted;
    }

    /**
     * getCoordinates() returns a Latlng that is coordinates from RequestInfoPost
     * @return rip.latlng
     */
    public LatLng getCoordinates() {
        return rip.latlng;
    }


    /**
     * isUrgent() returns a boolean for whether or not posting is urgent
     * @return rip.urgent
     */
    public boolean isUrgent() {
        return rip.urgent;
    }


    /**
     * canRelocate() return a boolean if owner of marker can relocate
     * @return rip.ableToRelocate
     */
    public boolean canRelocate() {
        return rip.ableToRelocate;
    }

}

