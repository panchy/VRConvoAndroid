package com.keensense.vrconvo;

import android.Manifest;
import android.app.Application;
import android.content.Context;

/**
 * Created by Panch on 29.11.2016.
 */

public class App extends Application {

    public static boolean DEBUG_MODE = true;

    public static String DEVICE_ID;

    public static String[] PERMISSIONS =  {Manifest.permission.INTERNET};

    private static Context appContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
