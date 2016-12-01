package com.keensense.vrconvo.apps.profile.fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.keensense.vrconvo.App;
import com.keensense.vrconvo.R;
import com.keensense.vrconvo.apps.profile.activity.ProfileActivity;
import com.keensense.vrconvo.listeners.FragmentListener;
import com.keensense.vrconvo.model.LoginResponse;
import com.keensense.vrconvo.model.Response;
import com.keensense.vrconvo.network.ConvoHelper;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;


/**
 * A simple fragment class with listener included. Created by Panch.
 */
public class SettingsFragment extends Fragment {
    private int LayoutId = R.layout.fragment_settings;

    private FragmentListener mListenerObject;

    @BindView(R.id.button_unbind_device)
    FancyButton mButtonBind;

    @BindView(R.id.button_changepassword)
    FancyButton mButtonChangePassword;

    @BindView(R.id.edittext_currentpassword_settings)
    TextInputEditText mCurrentPassword;

    @BindView(R.id.edittext_psaswordrepeat_settings)
    TextInputEditText mPasswordRepeat;

    @BindView(R.id.edittext_password_settings)
    TextInputEditText mPassword;

    @BindView(R.id.layout)
    PercentRelativeLayout mLayout;

    private ConvoHelper mConvoHelper = null;

    public SettingsFragment() {
        // Required empty public constructor
    }

    private static Fragment fragment = null;

    public static SettingsFragment newInstance() {
        if (fragment == null) {
            fragment = new SettingsFragment();
        }
        return (SettingsFragment) fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(LayoutId, container, false);
        ButterKnife.bind(this, v);
        mConvoHelper = new ConvoHelper(ProfileActivity.USER_INFO.getUsername(), ProfileActivity.USER_INFO.getPassword());
        initListeners();
        init();
        return v;
    }

    private void init() {

        if (ProfileActivity.USER_INFO.getDeviceId() == null || ProfileActivity.USER_INFO.getDeviceId().length() == 0) {
            mButtonBind.setBackgroundColor(Color.GREEN);
            mButtonBind.setText("Allow");

        } else {
            mButtonBind.setBackgroundColor(Color.RED);
            mButtonBind.setText("Forbid");

        }

    }

    private void initListeners() {

        mButtonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidInput()) {
                    enableGUI(false);
                    String currentPassword = mCurrentPassword.getText().toString();
                    String newPassword = mPassword.getText().toString();
                    mConvoHelper.setCredentials(ProfileActivity.USER_INFO.getUsername(), currentPassword);
                    mConvoHelper.changePassword(newPassword, new Callback<Response<LoginResponse>>() {
                        @Override
                        public void onResponse(Call<Response<LoginResponse>> call, retrofit2.Response<Response<LoginResponse>> response) {
                            if (response.body().getMessage().equals("OK!")) {
                                ProfileActivity.USER_INFO = response.body().getData();
                                Snackbar.make(mLayout, R.string.passhaschanged, 2000).show();
                            } else {
                                Snackbar.make(mLayout, response.body().getMessage(), 2000).show();
                            }


                            enableGUI(true);

                        }

                        @Override
                        public void onFailure(Call<Response<LoginResponse>> call, Throwable t) {

                            enableGUI(true);
                        }
                    });
                }
            }
        });


        final DialogInterface.OnClickListener onClickListenerBind = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                enableGUI(false);
                mConvoHelper.bindDeviceId(new Callback<Response<LoginResponse>>() {
                    @Override
                    public void onResponse(Call<Response<LoginResponse>> call, retrofit2.Response<Response<LoginResponse>> response) {
                        if (response.body().getMessage().equals("OK!")) {
                            Snackbar.make(mLayout, "Device is now bound to this account.", 3000).show();
                            ProfileActivity.USER_INFO = response.body().getData();
                            init();
                            enableGUI(true);
                        } else {
                            Snackbar.make(mLayout, response.body().getMessage(), 2000);
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<LoginResponse>> call, Throwable t) {
                        enableGUI(true);
                    }
                });

                dialogInterface.cancel();
            }
        };


        final DialogInterface.OnClickListener onClickListenerUnbind = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                enableGUI(false);
                mConvoHelper.unbindDeviceId(new Callback<Response<LoginResponse>>() {
                    @Override
                    public void onResponse(Call<Response<LoginResponse>> call, retrofit2.Response<Response<LoginResponse>> response) {
                        if (response.body().getMessage().equals("OK!")) {
                            Snackbar.make(mLayout, R.string.deviceisnotrelatedanymore, 3000).show();
                            ProfileActivity.USER_INFO = response.body().getData();
                            init();
                            enableGUI(true);
                        } else {
                            Snackbar.make(mLayout, response.body().getMessage(), 2000);
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<LoginResponse>> call, Throwable t) {
                        enableGUI(true);
                    }
                });


                dialogInterface.cancel();
            }
        };

        mButtonBind.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               if (ProfileActivity.USER_INFO.getDeviceId() == null || ProfileActivity.USER_INFO.getDeviceId().length() == 0) {
                                                   AlertDialog dialog = new AlertDialog.Builder(getActivity())
                                                           .setMessage("Do you want to bind this device to your account and VR Convo?")
                                                           .setNegativeButton("Cancel", null)
                                                           .setPositiveButton("Yes", onClickListenerBind)
                                                           .create();
                                                   dialog.show();
                                               } else {
                                                   AlertDialog dialog = new AlertDialog.Builder(getActivity())
                                                           .setMessage("Do you want to unbind this device from your account and VR Convo?")
                                                           .setNegativeButton("Cancel", null)
                                                           .setPositiveButton("Yes", onClickListenerUnbind)
                                                           .create();
                                                   dialog.show();
                                               }

                                           }
                                       }

        );

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListenerObject = (ProfileActivity) context;
        mListenerObject.onFragmentAttached();
    }

    @Override
    public void onDetach() {
        mListenerObject.onFragmentDetached();
        mListenerObject = null;
        super.onDetach();
    }

    private boolean isValidInput() {
        String curpassword = mCurrentPassword.getText().toString();
        String password = mPassword.getText().toString();
        String passwordRepeat = mPasswordRepeat.getText().toString();
        boolean toReturn = true;
        if (curpassword.length() == 0) {
            mCurrentPassword.setError("Don't leave this field empty!");
            toReturn = false;
        }
        if (password.length() == 0) {
            mPassword.setError("Don't leave this field empty!");
            toReturn = false;
        }
        if (passwordRepeat.length() == 0) {
            mPasswordRepeat.setError("Don't leave this field empty!");
            toReturn = false;
        }
        if ((passwordRepeat.length() > 0 && password.length() > 0) && (!password.equals(passwordRepeat))) {
            mPasswordRepeat.setError("Passwords don't match!");
            toReturn = false;
        }

        return toReturn;
    }

    private void enableGUI(boolean e) {
        if (e) {
            mButtonBind.setEnabled(true);
            mButtonChangePassword.setEnabled(true);
            mPassword.setEnabled(true);
            mPasswordRepeat.setEnabled(true);
            mCurrentPassword.setEnabled(true);
        } else {
            mButtonBind.setEnabled(false);
            mButtonChangePassword.setEnabled(false);
            mPassword.setEnabled(false);
            mPasswordRepeat.setEnabled(false);
            mCurrentPassword.setEnabled(false);
        }
    }
}
