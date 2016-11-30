package com.keensense.vrconvo.rawclasses;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keensense.vrconvo.R;
import com.keensense.vrconvo.apps.intro.activity.IntroActivity;
import com.keensense.vrconvo.listeners.FragmentListener;

import butterknife.ButterKnife;


/**
 * A simple fragment class with listener included. Created by Panch.
 */
public class RawFragment extends Fragment {
    private int LayoutId = R.layout.fragment_login;

    private FragmentListener mListenerObject;


    public RawFragment() {
        // Required empty public constructor
    }
    private static Fragment fragment=null;
    public static RawFragment newInstance() {
        if(fragment == null)
        {
            fragment = new RawFragment();
        }
        return (RawFragment)fragment;
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
        mListenerObject = (IntroActivity) context;
        mListenerObject.onFragmentAttached();
    }

    @Override
    public void onDetach() {
        mListenerObject.onFragmentDetached();
        mListenerObject = null;
        super.onDetach();
    }
}
