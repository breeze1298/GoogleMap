package com.breeze.googlemapjava;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.breeze.googlemapjava.roomdb.FormEntity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class InfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public InfoWindowGoogleMap(Context context) {
        this.context=context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.map_custom_infowindow, null);

        TextView email=view.findViewById(R.id.tvEmail);
        TextView address=view.findViewById(R.id.tvAddress);
        TextView name=view.findViewById(R.id.tvName);

        email.setText(marker.getSnippet());
        address.setText(marker.getSnippet());

        FormEntity infoWindowData = (FormEntity) marker.getTag();

        email.setText(infoWindowData.getEmail());
        address.setText(infoWindowData.getAddress());
        name.setText(infoWindowData.getName());


        return view;
    }
}
