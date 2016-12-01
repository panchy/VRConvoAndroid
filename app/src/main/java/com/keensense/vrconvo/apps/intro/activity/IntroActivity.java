package com.keensense.vrconvo.apps.intro.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.FrameLayout;

import com.keensense.vrconvo.App;
import com.keensense.vrconvo.R;
import com.keensense.vrconvo.apps.intro.fragment.LoginFragment;
import com.keensense.vrconvo.apps.intro.fragment.RegisterFragment;
import com.keensense.vrconvo.apps.profile.activity.ProfileActivity;
import com.keensense.vrconvo.listeners.FragmentListener;
import com.keensense.vrconvo.model.LoginResponse;
import com.keensense.vrconvo.model.Response;
import com.keensense.vrconvo.network.ConvoHelper;
import com.keensense.vrconvo.utils.FragmentUtils;
import com.keensense.vrconvo.utils.PermissionUtils;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class IntroActivity extends AppCompatActivity implements FragmentListener {

    private FragmentUtils mFutils;
    private static final int PERMISSIONS_STATUS_CODE = 101;

    @BindView(R.id.loadingView)
    AVLoadingIndicatorView mLoading;

    @BindView(R.id.container)
    FrameLayout mLayout;

    @BindView(R.id.loadingLayout)
    FrameLayout mLoadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);
        showGUI(false);
        init();
    }

    private void fastLogin() {
        ConvoHelper mConvoHelper = new ConvoHelper();
        mConvoHelper.deviceLogin(new Callback<Response<LoginResponse>>() {
            @Override
            public void onResponse(Call<Response<LoginResponse>> call, retrofit2.Response<Response<LoginResponse>> response) {
                if (response.body().getMessage().equals("OK!")) {
                    ProfileActivity.USER_INFO = response.body().getData();
                    loginSuccessful();
                } else {
                    showGUI(true);
                }
            }

            @Override
            public void onFailure(Call<Response<LoginResponse>> call, Throwable t) {

            }
        });
    }

    private void handlePermissions() {
        if (PermissionUtils.hasPermissions(this, App.PERMISSIONS)) {
            setDeviceId();
        } else {
            AlertDialog infoDialog = new AlertDialog.Builder(this)
                    .setMessage("VR Convo requires some permissions; internet connection, access your device id. In order to bind your device, you need to grant those permissions. Otherwise, application won't work.")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            PermissionUtils.askForPermissions(IntroActivity.this, App.PERMISSIONS, PERMISSIONS_STATUS_CODE);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setCancelable(false)
                    .create();

            infoDialog.show();
        }
    }

    private void setDeviceId() {
        //TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        App.DEVICE_ID = Settings.Secure.getString(getApplicationContext().getContentResolver(),Settings.Secure.ANDROID_ID);
        fastLogin();
    }

    private void init() {

        mFutils = new FragmentUtils(this);
        FragmentUtils.fragmentToAdd = LoginFragment.newInstance();
        mFutils.setFragment(R.id.container,true);

    }

    private void loginSuccessful() {
        Intent profile = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(profile);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (!PermissionUtils.hasPermissions(this, App.PERMISSIONS)) {
            handlePermissions();
        } else {
            setDeviceId();
        }

    }

    @Override
    public void onFragmentAttached() {
        handlePermissions();

    }

    @Override
    public void onFragmentDetached() {

    }


    @Override
    public void onMessageReceived(String... messages) {
        if (messages[0].equals("logged-in")) {
            loginSuccessful();
        } else if (messages[0].equals("register-fragment")) {
            FragmentUtils.fragmentToAdd = RegisterFragment.newInstance();
            mFutils.setFragment(R.id.container,true);
        } else if (messages[0].equals("login-fragment")) {
            FragmentUtils.fragmentToAdd = LoginFragment.newInstance();
            mFutils.setFragment(R.id.container,true);
        } else if (messages[0].equals("show-gui")) {
            showGUI(true);
        } else if (messages[0].equals("hide-gui")) {
            showGUI(false);
        }
    }

    private void showGUI(boolean shouldShow) {
        if (shouldShow) {
            mLoadingLayout.setVisibility(View.GONE);
            mLoading.hide();
            mLayout.setVisibility(View.VISIBLE);
        } else {
            mLoadingLayout.setVisibility(View.VISIBLE);
            mLoading.smoothToShow();
            mLayout.setVisibility(View.GONE);
        }
    }


}
