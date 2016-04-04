package com.maxmommersteeg.max.android_final;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An activity representing a list of Persons. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PersonDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class PersonListActivity extends BaseActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private ArrayList<Person> persons;

    public PersonListActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);

        // Get persons using API (Volley)
        VolleyService.init(getApplicationContext());
        RequestQueue queue = VolleyService.getRequestQueue();
        GsonRequest<Person[]> personRequest = new GsonRequest<Person[]>(
                Request.Method.GET,
                BASE_API_URL,
                Person[].class,
                new Response.Listener<Person[]>() {
                    @Override
                    public void onResponse(Person[] response) {
                        System.out.println("Success");
                        persons = new ArrayList<>(Arrays.asList(response));
                        View recyclerView = findViewById(R.id.person_list);
                        assert recyclerView != null;
                        setupRecyclerView((RecyclerView) recyclerView);
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.person_detail_container) != null) {
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimplePersonRecyclerViewAdapter(persons));
    }

    public class SimplePersonRecyclerViewAdapter
            extends RecyclerView.Adapter<SimplePersonRecyclerViewAdapter.ViewHolder> {

        private final List<Person> mPersons;

        public SimplePersonRecyclerViewAdapter(List<Person> items) {
            mPersons = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.person_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mPerson = mPersons.get(position);
            holder.mIdView.setText(String.valueOf(mPersons.get(position).getPersonId()));
            holder.mContentView.setText(mPersons.get(position).getFirstName());
            holder.mLatitudeView.setText(String.valueOf(mPersons.get(position).getCurrentLocation().getLatitude()));
            holder.mLongitudeView.setText(String.valueOf(mPersons.get(position).getCurrentLocation().getLongitude()));
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(PersonDetailFragment.ARG_PERSON_ID, String.valueOf(holder.mPerson.getPersonId()));
                        PersonDetailFragment fragment = new PersonDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.person_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, PersonDetailActivity.class);
                        intent.putExtra(PersonDetailFragment.ARG_PERSON_ID, holder.mPerson.getPersonId());

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mPersons == null ? 0 : mPersons.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public final TextView mLatitudeView;
            public final TextView mLongitudeView;

            public Person mPerson;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
                mLatitudeView = (TextView) view.findViewById(R.id.latitude);
                mLongitudeView = (TextView) view.findViewById(R.id.Longitude);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
