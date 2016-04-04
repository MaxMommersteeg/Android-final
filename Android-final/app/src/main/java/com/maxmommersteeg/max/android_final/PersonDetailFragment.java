package com.maxmommersteeg.max.android_final;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxmommersteeg.max.android_final.model.Person;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A fragment representing a single Person detail screen.
 * This fragment is either contained in a {@link PersonListActivity}
 * in two-pane mode (on tablets) or a {@link PersonDetailActivity}
 * on handsets.
 */
public class PersonDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_PERSON_ID = "ARG_PERSON_ID";

    /*
    * Used data object
    */
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

        //Load person by id here
        //TODO: hardcoded to API
        Person p = new Person();
        p.setFirstName("Max");
        p.setLastName("Mommersteeg");

        //Set birthdate
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date d = sdf.parse("01/02/1994");
            p.setBirthDate(d);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        p.setCurrentLocation(51.692512, 5.177475);
        person = p;
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
