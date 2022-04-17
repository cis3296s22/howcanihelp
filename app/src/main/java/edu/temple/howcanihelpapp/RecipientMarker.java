package edu.temple.howcanihelpapp;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class RecipientMarker extends CustomMarker {

    Marker m;

    public RecipientMarker(GoogleMap g, RequestInfoPost r) {
        super(g, r);
        m = super.marker;
        m.setIcon(getIcon());
    }

    private BitmapDescriptor getIcon() {
        int c = (this.isUrgent()) ? R.drawable.recipient_marker_urgent : R.drawable.recipient_marker;
        return BitmapDescriptorFactory.fromResource(c);
    }

    @Override
    public String getTitle() {
        return "Need: " + super.getTitle();
    }

    @Override
    public String getDateTime() {
        return super.getDateTime();
    }

    @Override
    public LatLng getCoordinates() {
        return super.getCoordinates();
    }

    @Override
    public boolean isUrgent() {
        return super.isUrgent();
    }

    @Override
    public boolean canRelocate() {
        return super.canRelocate();
    }
}
