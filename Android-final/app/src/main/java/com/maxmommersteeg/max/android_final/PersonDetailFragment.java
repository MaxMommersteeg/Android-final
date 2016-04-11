package com.maxmommersteeg.max.android_final;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
        if(!getArguments().containsKey(ARG_PERSON_OBJECT)) {
            return;
        }
        person = (Person) getArguments().getSerializable(ARG_PERSON_OBJECT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.person_detail, container, false);

        if (person == null)
            return rootView;

        mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String alias = mPreferences.getString(ALIAS_PREFERENCE_KEY + person.getPersonId().toString(), "");

        // Bind buttons
        final Button showOnMapButton = (Button) rootView.findViewById(R.id.showOnMapButton);
        showOnMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect user to map
                Context context = v.getContext();
                Intent intent = new Intent(context, PersonMapActivity.class);
                intent.putExtra(ARG_PERSON_OBJECT, person);
                context.startActivity(intent);
            }
        });

        final EditText aliasEditText = (EditText) rootView.findViewById(R.id.aliasEditText);
        aliasEditText.setText(alias);
        final Button saveAliasButton = (Button) rootView.findViewById(R.id.saveAliasButton);
        saveAliasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Arrived in saveAlias");
                if(person == null) {
                    return;
                }
                String alias = aliasEditText.getText().toString();
                alias = alias.replaceAll("(\\r|\\n)", "");
                if(alias.equals("")) {
                    return;
                }
                mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                mPreferenceEditor = mPreferences.edit();
                mPreferenceEditor.putString(ALIAS_PREFERENCE_KEY + person.getPersonId().toString(), alias);
                mPreferenceEditor.apply();

                // Update list for tables
                PersonListActivity pla = (PersonListActivity) getActivity();
                pla.LoadPersonList();

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Alias saved");
                alertDialog.setMessage("Alias (" + alias + ") successfully saved");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                System.out.println("Saved alias");
            }
        });

        //Show person detail data
        ((TextView) rootView.findViewById(R.id.person_fullname)).setText(person.getFullName());
        ((TextView) rootView.findViewById(R.id.person_birthdate)).setText(String.valueOf(person.getBirthDate()));

        if (person.getCurrentLocation() == null)
            return rootView;

        ((TextView) rootView.findViewById(R.id.person_location_latitude)).setText(String.valueOf(person.getCurrentLocation().getLatitude()));
        ((TextView) rootView.findViewById(R.id.person_location_longitude)).setText(String.valueOf(person.getCurrentLocation().getLongitude()));

        //Get this activity
        Activity activity = this.getActivity();
        //Get app bar layout
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(person.getFullName());
        }
        return rootView;
    }
}
