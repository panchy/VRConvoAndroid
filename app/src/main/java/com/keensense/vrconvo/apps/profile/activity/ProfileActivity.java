package com.keensense.vrconvo.apps.profile.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.keensense.vrconvo.R;
import com.keensense.vrconvo.apps.profile.fragment.FriendsFragment;
import com.keensense.vrconvo.apps.profile.fragment.HomeFragment;
import com.keensense.vrconvo.apps.profile.fragment.ItemsFragment;
import com.keensense.vrconvo.apps.profile.fragment.SettingsFragment;
import com.keensense.vrconvo.events.SnackbarRequestEvent;
import com.keensense.vrconvo.events.UserInfoChangedEvent;
import com.keensense.vrconvo.listeners.FragmentListener;
import com.keensense.vrconvo.models.Feed;
import com.keensense.vrconvo.models.LoginResponse;
import com.keensense.vrconvo.models.Response;
import com.keensense.vrconvo.network.ConvoHelper;
import com.keensense.vrconvo.utils.FragmentUtils;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class ProfileActivity extends AppCompatActivity implements FragmentListener {
    private int LayoutId = R.layout.activity_profile;

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation mBottomNav;

    @BindView(R.id.username)
    TextView mUsername;

    @BindView(R.id.convo_coins)
    TextView mConvoCoins;

    @BindView(R.id.loadingView)
    AVLoadingIndicatorView mLoading;

    @BindView(R.id.container)
    FrameLayout mLayout;


    @BindView(R.id.loadingLayout)
    FrameLayout mLoadingLayout;

    public static LoginResponse USER_INFO = null;
    public static ConvoHelper mConvoHelper = null;
    FragmentUtils mFutils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutId);
        ButterKnife.bind(this);
        initBottomNav();
        mFutils = new FragmentUtils(this);
        FragmentUtils.fragmentToAdd = HomeFragment.newInstance();
        mFutils.setFragment(R.id.container,true);
        mConvoHelper = new ConvoHelper();
        if (USER_INFO != null) {
            mUsername.setText(USER_INFO.getUsername());
            mConvoCoins.setText(String.valueOf(USER_INFO.getUserInfo().getCoins()));
            mConvoHelper.setCredentials(USER_INFO.getUsername(),USER_INFO.getPassword());
        }



    }

    @Override
    protected void onPause() {
        if(EventBus.getDefault().isRegistered(this))
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!EventBus.getDefault().isRegistered(this))
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onUserInfoChangedEvent(UserInfoChangedEvent event)
    {
        if (USER_INFO != null) {
            mUsername.setText(USER_INFO.getUsername());
            mConvoCoins.setText(String.valueOf(USER_INFO.getUserInfo().getCoins()));
        }
    }

    @Subscribe
    public void onSnackbarRequestReceived(SnackbarRequestEvent event)
    {
        Snackbar.make(mLayout,event.getText(),2000).show();
    }

    private void initBottomNav() {
        AHBottomNavigationItem homeNav = new AHBottomNavigationItem("Home", R.drawable.ic_home_black_18dp, R.color.colorPrimaryDark);
        AHBottomNavigationItem friendsNav = new AHBottomNavigationItem("Friends", R.drawable.ic_supervisor_account_black_18dp, R.color.colorPrimaryDark);
        AHBottomNavigationItem itemsNav = new AHBottomNavigationItem("Items", R.drawable.ic_shopping_cart_black_18dp, R.color.colorPrimaryDark);
        AHBottomNavigationItem settingsNav = new AHBottomNavigationItem("Settings", R.drawable.ic_settings_black_18dp, R.color.colorPrimaryDark);
        mBottomNav.addItem(homeNav);
        mBottomNav.addItem(friendsNav);
        mBottomNav.addItem(itemsNav);
        mBottomNav.addItem(settingsNav);
        mBottomNav.setDefaultBackgroundColor(getResources().getColor(R.color.white));
        mBottomNav.setAccentColor(getResources().getColor(R.color.teal));
        mBottomNav.setInactiveColor(getResources().getColor(R.color.colorPrimaryDark));
        mBottomNav.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        mBottomNav.setCurrentItem(0);


        mBottomNav.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (mBottomNav.getCurrentItem() == position) {
                    return false;
                }
                switch (position) {
                    case 0:
                        FragmentUtils.fragmentToAdd = HomeFragment.newInstance();
                        break;
                    case 1:
                        FragmentUtils.fragmentToAdd = FriendsFragment.newInstance();
                        break;
                    case 2:
                        FragmentUtils.fragmentToAdd = ItemsFragment.newInstance();
                        break;
                    case 3:
                        FragmentUtils.fragmentToAdd = SettingsFragment.newInstance();
                        break;
                }
                mFutils.setFragment(R.id.container,false);
                return true;
            }
        });


    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached() {

    }

    @Override
    public void onMessageReceived(String... messages) {
        if (messages[0].equals("show-gui")) {
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
