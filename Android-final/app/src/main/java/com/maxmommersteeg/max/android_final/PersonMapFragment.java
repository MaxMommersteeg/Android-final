package com.maxmommersteeg.max.android_final;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maxmommersteeg.max.android_final.app.VolleyService;
import com.maxmommersteeg.max.android_final.model.Person;
import com.maxmommersteeg.max.android_final.toolbox.GsonRequest;

public class PersonMapFragment extends BaseFragment implements
        OnMapReadyCallback {

    private Integer personId;
    private Person person;

    private LatLng currentLatLng;
    private SupportMapFragment googleMap;

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
        personId = getArguments().getInt(ARG_PERSON_ID);
        System.out.println("PMF: " + String.valueOf(personId));

        // Get persons using API (Volley)
        VolleyService.init(getActivity().getApplicationContext());
        RequestQueue queue = VolleyService.getRequestQueue();
        GsonRequest<Person[]> personRequest = new GsonRequest<Person[]>(
                Request.Method.GET,
                BASE_API_URL,
                Person[].class,
                new Response.Listener<Person[]>() {
                    @Override
                    public void onResponse(Person[] response) {
                        System.out.println("Success");
                        person = response[personId];
                        currentLatLng = new LatLng(person.getCurrentLocation().getLatitude(), person.getCurrentLocation().getLongitude());
                        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.person_map_container);
                        FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
                        fragTransaction.detach(currentFragment);
                        fragTransaction.attach(currentFragment);
                        fragTransaction.commit();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");
                System.out.println(error.getMessage());
            }
        }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.person_map, container, false);

        if(person == null)
            return rootView;

        //Get Google map
        googleMap = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googlemap);
        if(googleMap == null)
            return rootView;
        googleMap.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if(map == null)
            return;
        map.addMarker(new MarkerOptions().position(currentLatLng).title(person.getFullName()));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }
}
