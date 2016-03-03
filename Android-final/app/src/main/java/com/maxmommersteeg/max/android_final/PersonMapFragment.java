package com.maxmommersteeg.max.android_final;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.maxmommersteeg.max.android_final.Model.Location;

public class PersonMapFragment extends Fragment {

    private static final String ARG_LOCATION_ID = "LOCATION_ID";

    private Location location;

    private GoogleMap googleMap;
    private LatLng position;

    public PersonMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if arguments exist
        if (getArguments() == null)
            return;
        //Check if we received an objectid for the location
        if(!getArguments().containsKey(ARG_LOCATION_ID))
            return;

        //Load location by id here
        //TODO: hardcoded to API
        Location l = new Location(51.692398, 5.177454); // (Kerkstraat 16, Nieuwkuijk)
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.person_map, container, false);

        if(location == null)
            return rootView;

        return rootView;
    }
}
