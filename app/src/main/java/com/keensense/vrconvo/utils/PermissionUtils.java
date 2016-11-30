package com.keensense.vrconvo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;


/**
 * Created by Panch on 30.11.2016.
 */

public class PermissionUtils {

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void askForPermissions(Activity a, String[] PERMISSIONS, int statuscode)
    {
        ActivityCompat.requestPermissions(a, PERMISSIONS, statuscode);
    }



}
