package com.keensense.vrconvo.apps.intro.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.keensense.vrconvo.R;
import com.keensense.vrconvo.apps.intro.activity.IntroActivity;
import com.keensense.vrconvo.listeners.FragmentListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;


/**
 * A simple fragment class with listener included. Created by Panch.
 */
public class RegisterFragment extends Fragment {
    private int LayoutId = R.layout.fragment_register;

    private FragmentListener mListenerObject;

    @BindView(R.id.edittext_username_register)
    TextInputEditText mUsername;
    @BindView(R.id.edittext_password_register)
    TextInputEditText mPassword;
    @BindView(R.id.edittext_passwordrepeat_register)
    TextInputEditText mPasswordRepeat;
    @BindView(R.id.button_register)
    FancyButton mBtnRegister;
    @BindView(R.id.button_back)
    FancyButton mBtnBack;
    @BindView(R.id.layout)
    RelativeLayout mLayout;


    public RegisterFragment() {
        // Required empty public constructor
    }

    private static Fragment fragment=null;

    public static RegisterFragment newInstance() {
        if(fragment == null)
        {
            fragment = new RegisterFragment();
        }
        return (RegisterFragment)fragment;
    }

    private void initListeners() {

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListenerObject.onMessageReceived("login-fragment");
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(LayoutId, container, false);
        ButterKnife.bind(this,v);
        initListeners();
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
