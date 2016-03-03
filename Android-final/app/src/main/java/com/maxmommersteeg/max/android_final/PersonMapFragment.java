package com.maxmommersteeg.max.android_final;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maxmommersteeg.max.android_final.Model.Location;
import com.maxmommersteeg.max.android_final.Model.Person;

import java.util.Iterator;

public class PersonMapFragment extends Fragment implements
        OnMapReadyCallback {

    public static final String ARG_PERSON_ID = "ARG_PERSON_ID";

    private Person person;

    private LatLng currentLatLng;

    public PersonMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if arguments exist
        if (getArguments() == null)
            return;
        //Check if we received an objectid for the person
        if(!getArguments().containsKey(ARG_PERSON_ID))
            return;

        //Retrieve personId
        Integer personId = getArguments().getInt(ARG_PERSON_ID);
        System.out.println("PMF: " + String.valueOf(personId));

        Person p = new Person();
        p.setFirstName("Max");
        p.setLastName("Mommersteeg");

        //Load location by id here
        //TODO: hardcoded to API
        p.setCurrentLocation(51.692398, 5.177454); // (Kerkstraat 16, Nieuwkuijk)
        person = p;

        currentLatLng = new LatLng(person.getCurrentLocation().getLatitude(), person.getCurrentLocation().getLongitude());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.person_map, container, false);

        //Get Google map
        SupportMapFragment smf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googlemap);
        if(smf == null)
            return rootView;

        smf.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if(map == null)
            return;
        map.addMarker(new MarkerOptions().position(currentLatLng).title(person.getFullName()));
    }
}
