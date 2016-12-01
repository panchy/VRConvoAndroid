package com.keensense.vrconvo.rawclasses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.keensense.vrconvo.R;
import com.keensense.vrconvo.apps.intro.fragment.LoginFragment;
import com.keensense.vrconvo.listeners.FragmentListener;
import com.keensense.vrconvo.utils.FragmentUtils;

import butterknife.ButterKnife;

public class RawActivity extends AppCompatActivity implements FragmentListener {
    private int LayoutId = R.layout.activity_intro;

    FragmentUtils mFutils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutId);
        ButterKnife.bind(this);
        mFutils = new FragmentUtils(this);
        FragmentUtils.fragmentToAdd = LoginFragment.newInstance();
        mFutils.setFragment(R.id.container,true);
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached() {

    }

    @Override
    public void onMessageReceived(String... messages) {

    }
}
