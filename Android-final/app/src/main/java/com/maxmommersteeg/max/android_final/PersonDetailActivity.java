package com.maxmommersteeg.max.android_final;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.maxmommersteeg.max.android_final.Model.Person;

/**
 * An activity representing a single Person detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link PersonListActivity}.
 */
public class PersonDetailActivity extends AppCompatActivity {

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        //Retrieve personId
        Integer personId = getIntent().getIntExtra(PersonMapFragment.ARG_PERSON_ID, -1);
        //Check if the id is valid
        if(personId == -1) {
            Toast.makeText(this, "Invalid Person ID", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, PersonMapActivity.class);
                intent.putExtra(PersonMapFragment.ARG_PERSON_ID, getIntent().getIntExtra(PersonMapFragment.ARG_PERSON_ID, -1));
                context.startActivity(intent);
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(PersonDetailFragment.ARG_PERSON_ID, getIntent().getIntExtra(PersonMapFragment.ARG_PERSON_ID, -1));
            PersonDetailFragment pdf = new PersonDetailFragment();
            pdf.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.person_detail_container, pdf)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, PersonListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void ShowOnMap(View view) {
        // redirect user to map
        Context context = view.getContext();
        Intent intent = new Intent(context, PersonMapActivity.class);
        intent.putExtra(PersonMapFragment.ARG_PERSON_ID, getIntent().getIntExtra(PersonMapFragment.ARG_PERSON_ID, -1));
        context.startActivity(intent);

        //Showing a dialog
        //AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setMessage("This is the message").setTitle("This is the title");
        //AlertDialog dialog = builder.create();
        //dialog.show();
    }
}
