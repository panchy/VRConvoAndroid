package com.keensense.vrconvo.apps.profile.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keensense.vrconvo.R;
import com.keensense.vrconvo.apps.profile.activity.ProfileActivity;
import com.keensense.vrconvo.listeners.FragmentListener;

import butterknife.ButterKnife;


/**
 * A simple fragment class with listener included. Created by Panch.
 */
public class HomeFragment extends Fragment {
    private int LayoutId = R.layout.fragment_home;

    private FragmentListener mListenerObject;


    public HomeFragment() {
        // Required empty public constructor
    }
    private static Fragment fragment=null;
    public static HomeFragment newInstance() {
        if(fragment == null)
        {
            fragment = new HomeFragment();
        }
        return (HomeFragment)fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(LayoutId, container, false);
        ButterKnife.bind(this,v);
        return  v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListenerObject = (ProfileActivity) context;
        mListenerObject.onFragmentAttached();
        mListenerObject.onMessageReceived("show-gui");
    }

    @Override
    public void onDetach() {
        mListenerObject.onFragmentDetached();
        mListenerObject = null;
        super.onDetach();
    }
}
