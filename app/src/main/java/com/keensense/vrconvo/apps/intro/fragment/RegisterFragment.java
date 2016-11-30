package com.keensense.vrconvo.apps.intro.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.keensense.vrconvo.R;
import com.keensense.vrconvo.apps.intro.activity.IntroActivity;
import com.keensense.vrconvo.apps.profile.ProfileActivity;
import com.keensense.vrconvo.listeners.FragmentListener;
import com.keensense.vrconvo.model.LoginResponse;
import com.keensense.vrconvo.model.Response;
import com.keensense.vrconvo.model.UserInfo;
import com.keensense.vrconvo.network.ConvoHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;


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

    private ConvoHelper mConvoHelper;

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

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidInput())
                {
                    String username = mUsername.getText().toString();
                    String password = mPassword.getText().toString();
                    mConvoHelper.setCredentials(username,password);
                    mConvoHelper.register(new Callback<Response<LoginResponse>>() {
                        @Override
                        public void onResponse(Call<Response<LoginResponse>> call, retrofit2.Response<Response<LoginResponse>> response) {
                            if (response.body().getMessage().equals("OK!")) {
                                ProfileActivity.USER_INFO=response.body().getData();
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
            }
        });

        mUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence,int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkUsername();
            }
        });


    }

    private boolean isValidInput()
    {
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        String passwordRepeat = mPasswordRepeat.getText().toString();
        boolean toReturn = true;
        if(username.length()==0)
        {
            mUsername.setError("Don't leave this field empty!");
            toReturn=false;
        }
        if(password.length()==0)
        {
            mPassword.setError("Don't leave this field empty!");
            toReturn=false;
        }
        if(passwordRepeat.length()==0)
        {
            mPasswordRepeat.setError("Don't leave this field empty!");
            toReturn=false;
        }
        if((passwordRepeat.length()>0&&password.length()>0)&&(!password.equals(passwordRepeat)))
        {
            mPasswordRepeat.setError("Passwords don't match!");
            toReturn=false;
        }

        return toReturn;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(LayoutId, container, false);
        ButterKnife.bind(this,v);
        mConvoHelper = new ConvoHelper();
        initListeners();
        return  v;
    }

    private void checkUsername()
    {
        mConvoHelper.setCredentials(mUsername.getText().toString(),mPassword.getText().toString());
        mConvoHelper.checkUser(new Callback<Response<UserInfo>>() {
            @Override
            public void onResponse(Call<Response<UserInfo>> call, retrofit2.Response<Response<UserInfo>> response) {
                if(response.body().getMessage().equals("OK!"))
                {
                    mUsername.setTextColor(Color.GREEN);
                }
                else
                {
                    mUsername.setTextColor(Color.RED);
                }
            }

            @Override
            public void onFailure(Call<Response<UserInfo>> call, Throwable t) {

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
