package com.maxmommersteeg.max.android_final.app;

import android.app.ActivityManager;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Max on 4-4-2016.
 */
public class VolleyService {
    private static RequestQueue mRequestQueue;

    private VolleyService() {

    }

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);

        int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        // Use 1/8th of the available memory for this memory cache
        int cacheSize = 1024 * 1024 * memClass / 8;
    }

    public static RequestQueue getRequestQueue() {
        if(mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue is not initialized");
        }
    }
}
