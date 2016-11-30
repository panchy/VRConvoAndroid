package com.keensense.vrconvo.apps.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.keensense.vrconvo.R;
import com.keensense.vrconvo.model.LoginResponse;
import com.keensense.vrconvo.model.UserInfo;

public class ProfileActivity extends AppCompatActivity {

    public static LoginResponse USER_INFO=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
}
