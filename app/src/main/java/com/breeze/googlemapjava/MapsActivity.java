package com.breeze.googlemapjava;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.breeze.googlemapjava.roomdb.DatabaseClient;
import com.breeze.googlemapjava.roomdb.FormEntity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ArrayList<LatLng> polyLatLng=null;


        Handler mainHandler = new Handler(Looper.getMainLooper());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                ArrayList<FormEntity> locationDetails=new ArrayList<>(DatabaseClient.getInstance(MapsActivity.this).formDao().getFormDetails());

                for (int i=0;i<locationDetails.size();i++) {
                    Double lat = Double.valueOf(locationDetails.get(i).latitude);
                    Double lng = Double.valueOf(locationDetails.get(i).longitude);
                    LatLng addLatLng = new LatLng(lat, lng);

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(addLatLng);


                    FormEntity data=new FormEntity();
                    data.setName(locationDetails.get(i).name);
                    data.setEmail(locationDetails.get(i).email);
                    data.setAddress(locationDetails.get(i).address);

                    InfoWindowGoogleMap customInfoWindow = new InfoWindowGoogleMap(MapsActivity.this);
                    mMap.setInfoWindowAdapter(customInfoWindow);

                    Marker m = mMap.addMarker(markerOptions);
                    m.setTag(data);
//                    For Custom Marker Icon
//                    m.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_icon));
                    m.showInfoWindow();

                    mMap.addCircle(new CircleOptions()
                            .center(new LatLng(lat,lng))
                            .radius(100)
                            .strokeColor(Color.RED)
                            .fillColor(Color.rgb(135,206,235)));



//                    polyLatLng.add(addLatLng);

                    mMap.addPolyline(new PolylineOptions().add(
                            new LatLng(Double.parseDouble(locationDetails.get(i).latitude),Double.parseDouble(locationDetails.get(i).longitude)))
                            .width(5).color(Color.BLUE));


                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(addLatLng, 15));

                            

                }

            }
        };
        mainHandler.post(myRunnable);
    }

}