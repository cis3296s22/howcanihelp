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

        TextView infoItem = view.findViewById(R.id.info_item);
        TextView infoPosted = view.findViewById(R.id.info_posted);
        TextView infoLocation = view.findViewById(R.id.info_location);

        infoItem.setText(marker.getTitle());
        infoPosted.setText(marker.getSnippet());

        infoLocation.setText("TEST LOCATION");

        return view;
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        return null;
    }
}
