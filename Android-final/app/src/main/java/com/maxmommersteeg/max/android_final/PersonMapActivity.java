package com.maxmommersteeg.max.android_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.maxmommersteeg.max.android_final.model.Person;

/**
 * An activity representing a single Person detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link PersonListActivity}.
 */
public class PersonMapActivity extends BaseActivity {

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Retrieve person
        person = (Person) getIntent().getExtras().getSerializable(ARG_PERSON_OBJECT);
        // Check if person is valid
        if(person == null) {
            Toast.makeText(this, "Invalid Person", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        System.out.println("PMA: " + String.valueOf(person.getPersonId()));

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putSerializable(ARG_PERSON_OBJECT, person);
            PersonMapFragment pmf = new PersonMapFragment();
            pmf.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.person_map_container, pmf)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, PersonListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
