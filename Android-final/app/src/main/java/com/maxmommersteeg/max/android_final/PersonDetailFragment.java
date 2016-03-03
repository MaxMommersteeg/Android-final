package com.maxmommersteeg.max.android_final;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxmommersteeg.max.android_final.Model.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    public static final String ARG_ITEM_ID = "item_id";

    /*
    * Used data object
    */
    private Person person;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PersonDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if we received an objectid
        if(!getArguments().containsKey(ARG_ITEM_ID))
            return;

        //Load person by id here
        Person p = new Person();
        p.setFirstName("Max");
        p.setLastName("Mommersteeg");
        person = p;

        //Set birthdate
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date d = sdf.parse("01/02/1994");
            p.setBirthDate(d);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //Get this activity
        Activity activity = this.getActivity();
        //Get app bar layout
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        //Null check
        if (appBarLayout != null) {
            //Set its title
            appBarLayout.setTitle(person.getFullName());
        }
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
        return rootView;
    }
}
