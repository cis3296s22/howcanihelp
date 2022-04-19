package edu.temple.howcanihelpapp;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class RequestInfoAdapter implements GoogleMap.InfoWindowAdapter {
    private Activity context;

    public RequestInfoAdapter(Activity context) {
        this.context = context;
    }

    // for Google Maps adapter
    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
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

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        return null;
    }
}
