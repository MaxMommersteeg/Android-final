package com.maxmommersteeg.max.android_final.helper;

import java.io.IOException;

/**
 * Created by Max on 11-4-2016.
 */
public enum Connectivity {
    INSTANCE;

    public final static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }
}
