package com.keensense.vrconvo.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Panch on 29.11.2016.
 */

public class FragmentUtils {

    private AppCompatActivity activity = null;
    private static FragmentTransaction fragTrans = null;

    public static Fragment fragmentToAdd = null;

    public FragmentUtils(AppCompatActivity _activity) {
        this.activity = _activity;
    }

    public void setFragment(int layoutid,boolean withAnim) {

        if(activity.getSupportFragmentManager().getFragments()!=null)
        {
            fragTrans = activity.getSupportFragmentManager().beginTransaction();
            if(withAnim)
            fragTrans.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragTrans.replace(layoutid, fragmentToAdd);
            fragTrans.commitAllowingStateLoss();
        }
        else
        {
            fragTrans = activity.getSupportFragmentManager().beginTransaction();
            if(withAnim)
            fragTrans.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragTrans.add(layoutid, fragmentToAdd);
            fragTrans.commitAllowingStateLoss();
        }



    }


}
