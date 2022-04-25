package edu.temple.howcanihelpapp;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 *  The purpose of this adapter class is to fit a layout for the information window
 *  that appears when the user interacts with a select Donor or Recipient marker on Google Maps
 */
public class RequestInfoAdapter implements GoogleMap.InfoWindowAdapter {
    /**
     *  context is necessary for keeping a reference to the class that calls RequestInfoAdapter
     */
    private Activity context;

    /**
     * Constructor for RequestInfoAdapter, passes context
     * @param context
     */
    public RequestInfoAdapter(Activity context) {
        this.context = context;
    }

    /**
     * Part of the Google Maps InfoWindowAdapter interface, provides contents for the
     * information window. All contents that are passed from marker shown using TextView
     * variables. Information is passed to a RequestInfoPost; the method returns a View
     *
     * @param marker
     * @return
     */
    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        /**
         *  from the context, we pass in the info window layout for the adapter
         */
        View view = context.getLayoutInflater().inflate(R.layout.infowindow, null);

        TextView infoItem = (TextView) view.findViewById(R.id.info_item);
        TextView infoPosted = (TextView) view.findViewById(R.id.info_posted);
        TextView infoLocation = (TextView) view.findViewById(R.id.info_location);
        TextView infoIsUrgent = (TextView) view.findViewById(R.id.urgentText);
        TextView infoIsRelocatable = (TextView) view.findViewById(R.id.ableToMoveText);

        RequestInfoPost selected = (RequestInfoPost) marker.getTag();
        assert selected != null;

        infoItem.setText(selected.title);
        infoPosted.setText(selected.dateTimePosted);
        infoLocation.setText(selected.latlng.toString());
        infoIsUrgent.setText((selected.urgent) ? "URGENT" : "");
        infoIsRelocatable.setText((selected.ableToRelocate) ? "ABLE TO RELOCATE" : "");

        return view;
    }

    /**
     * Part of the Google Maps InfoWindowAdapter interface, provides a info window for
     * the select marker.
     *
     * @param marker
     * @return
     */
    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        return null;
    }
}
