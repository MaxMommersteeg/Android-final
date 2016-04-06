package com.maxmommersteeg.max.android_final;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.maxmommersteeg.max.android_final.app.VolleyService;
import com.maxmommersteeg.max.android_final.model.Person;
import com.maxmommersteeg.max.android_final.toolbox.GsonRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * A fragment representing a single Person detail screen.
 * This fragment is either contained in a {@link PersonListActivity}
 * in two-pane mode (on tablets) or a {@link PersonDetailActivity}
 * on handsets.
 */
public class PersonDetailFragment extends BaseFragment {
    /*
    * Used data object
    */
    private Integer personId;
    private Person person;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PersonDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if arguments exist
        if(getArguments() == null)
            return;
        //Check if we received an objectid for the person
        if(!getArguments().containsKey(ARG_PERSON_ID))
            return;

        personId = getArguments().getInt(ARG_PERSON_ID);

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
                        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.person_detail_container);
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
        queue.add(personRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.person_detail, container, false);

        if(person == null)
            return rootView;

        //Show person detail data
        ((TextView) rootView.findViewById(R.id.person_fullname)).setText(person.getFullName());
        ((TextView) rootView.findViewById(R.id.person_birthdate)).setText(String.valueOf(person.getBirthDate()));

        if(person.getCurrentLocation() == null)
            return rootView;

        ((TextView) rootView.findViewById(R.id.person_location_latitude)).setText(String.valueOf(person.getCurrentLocation().getLatitude()));
        ((TextView) rootView.findViewById(R.id.person_location_longitude)).setText(String.valueOf(person.getCurrentLocation().getLongitude()));

        //Get this activity
        Activity activity = this.getActivity();
        //Get app bar layout
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if(appBarLayout != null) {
            appBarLayout.setTitle(person.getFullName());
        }
        return rootView;
    }
}
