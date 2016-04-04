package com.maxmommersteeg.max.android_final;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Max on 4-4-2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected static final String PREFERENCE_FILE = "PREFERENCE_FILE";
    protected static final String  BASE_API_URL = "https://api.myjson.com/bins/2lzia";
    protected static final String ARG_PERSON_ID = "ARG_PERSON_ID";

    public BaseActivity() {
    }
}
