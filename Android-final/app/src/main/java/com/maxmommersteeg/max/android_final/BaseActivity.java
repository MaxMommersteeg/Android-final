package com.maxmommersteeg.max.android_final;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.lang.Override;

/**
 * Created by Max on 4-4-2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected static final String BASE_API_URL = "http://www.maxmommersteeg.nl/users.json";
    protected static final String ARG_PERSON_OBJECT = "ARG_PERSON_OBJECT";
    protected static final String ALIAS_PREFERENCE_KEY = "ALIAS_PREFERENCE_KEY";

    protected void showAlert(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.app_name))
            .setMessage(msg)
            .setCancelable(false)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
    }
}
