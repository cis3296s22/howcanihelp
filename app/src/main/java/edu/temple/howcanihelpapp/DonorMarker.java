package edu.temple.howcanihelpapp;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class DonorMarker extends CustomMarker {
/**
 * The purpose of this class is to extend CustomMarker and make it specific to Donors on
 * the map.
 */
    /**
     * Marker m will be the marker taken from CustomMarker
     */
    Marker m;
    /**
     * DonorMarker(...) is the constructor method
     * @param g is the GoogleMap instance
     * @param r is the RequestInfoPost instance
     */
    public DonorMarker(GoogleMap g, RequestInfoPost r) {
        super(g, r);
        m = super.marker;
        m.setIcon(getIcon());
    }
    /**
     * getIcon() returns a BitmapDescriptor. The purpose is to get a marker to use in the map
     * layout
     * @return BitmapDescriptorFactory.fromResource(c)
     */
    private BitmapDescriptor getIcon() {
        int c = (this.isUrgent()) ? R.drawable.donor_marker_urgent : R.drawable.donor_marker;
        return BitmapDescriptorFactory.fromResource(c);
    }
    /**
     * getTitle() returns a String that is the title from rip with the word "Giving: " in front
     * @return rip.title
     */
    @Override
    public String getTitle() {
        return "Giving: " + super.getTitle();
    }
    /**
     * getDateTime() returns a String that is the date and time of rip
     * @return rip.dateTimePosted
     */
    @Override
    public String getDateTime() {
        return super.getDateTime();
    }

    /**
     * getCoordinates() returns a Latlng that is coordinates from rip
     * @return rip.latlng
     */
    @Override
    public LatLng getCoordinates() {
        return super.getCoordinates();
    }

    /**
     * isUrgent() returns a boolean for whether or not rip is urgent
     * @return rip.urgent
     */
    @Override
    public boolean isUrgent() {
        return super.isUrgent();
    }

    /**
     * canRelocate() return a boolean if rip is able to relocate
     * @return rip.ableToRelocate
     */
    @Override
    public boolean canRelocate() {
        return super.canRelocate();
    }
}
