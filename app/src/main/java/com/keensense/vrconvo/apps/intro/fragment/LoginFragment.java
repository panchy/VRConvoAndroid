package com.keensense.vrconvo.apps.intro.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.keensense.vrconvo.R;
import com.keensense.vrconvo.apps.intro.activity.IntroActivity;
import com.keensense.vrconvo.listeners.FragmentListener;
import com.keensense.vrconvo.model.LoginResponse;
import com.keensense.vrconvo.model.Response;
import com.keensense.vrconvo.network.ConvoHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple fragment class with listener included. Created by Panch.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.edittext_username_login)
    TextInputEditText mUsername;
    @BindView(R.id.edittext_password_login)
    TextInputEditText mPassword;
    @BindView(R.id.button_login)
    FancyButton mBtnLogin;
    @BindView(R.id.button_register)
    FancyButton mBtnRegister;
    @BindView(R.id.layout)
    RelativeLayout mLayout;

    private FragmentListener mListenerObject;
    private ConvoHelper mConvoHelper;

    public LoginFragment() {
        // Required empty public constructor
    }

    private static Fragment fragment=null;

    public static LoginFragment newInstance() {
        if(fragment == null)
        {
            fragment = new LoginFragment();
        }
        return (LoginFragment)fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, v);
        mConvoHelper = new ConvoHelper();
        initListeners();
        return v;
    }

    private void initListeners() {

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                mConvoHelper.setCredentials(username, password);
                mConvoHelper.login(new Callback<Response<LoginResponse>>() {
                    @Override
                    public void onResponse(Call<Response<LoginResponse>> call, retrofit2.Response<Response<LoginResponse>> response) {
                        if (response.body().getMessage().equals("OK!")) {
                            mListenerObject.onMessageReceived("logged-in");
                        } else {
                            Snackbar.make(mLayout,response.body().getMessage(),2000).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<LoginResponse>> call, Throwable t) {

                    }
                });
            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListenerObject.onMessageReceived("register-fragment");
            }
        });
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
