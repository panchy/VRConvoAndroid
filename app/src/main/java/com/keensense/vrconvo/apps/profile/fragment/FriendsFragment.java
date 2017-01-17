package com.keensense.vrconvo.apps.profile.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.keensense.vrconvo.R;
import com.keensense.vrconvo.apps.profile.activity.ProfileActivity;
import com.keensense.vrconvo.apps.profile.adapter.CharacterViewHolder;
import com.keensense.vrconvo.apps.profile.adapter.FriendViewHolder;
import com.keensense.vrconvo.apps.profile.adapter.RoomViewHolder;
import com.keensense.vrconvo.listeners.FragmentListener;
import com.keensense.vrconvo.models.Character;
import com.keensense.vrconvo.models.Friendship;
import com.keensense.vrconvo.models.Response;
import com.keensense.vrconvo.models.Room;
import com.keensense.vrconvo.network.ConvoHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;


/**
 * A simple fragment class with listener included. Created by Panch.
 */
public class FriendsFragment extends Fragment {

    private int LayoutId = R.layout.fragment_friends;

    @BindView(R.id.recyclerview_friends)
    RecyclerView mRecyclerviewFriends;
    @BindView(R.id.recyclerview_requests)
    RecyclerView mRecyclerviewRequests;
    @BindView(R.id.friends_layout)
    LinearLayout mFriendsLayout;
    @BindView(R.id.requests_layout)
    LinearLayout mRequestsLayout;
    @BindView(R.id.requests_button)
    FancyButton mButtonRequests;
    @BindView(R.id.friends_button)
    FancyButton mButtonFriends;

    private FragmentListener mListenerObject;

    private static List<Friendship> mFriends = new ArrayList<>();
    private static List<Friendship> mRequests = new ArrayList<>();

    private ConvoHelper mConvoHelper = null;

    public FriendsFragment() {
        // Required empty public constructor
    }

    private static Fragment fragment = null;

    public static FriendsFragment newInstance() {
        if (fragment == null) {
            fragment = new FriendsFragment();
        }
        return (FriendsFragment) fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(LayoutId, container, false);
        ButterKnife.bind(this, v);
        mConvoHelper = new ConvoHelper(ProfileActivity.USER_INFO.getUsername(), ProfileActivity.USER_INFO.getPassword());
        initListeners();
        initAdapters();
        getData();
        return v;
    }

    private void initAdapters() {
        mRecyclerviewFriends.setAdapter(new EasyRecyclerAdapter<Friendship>(getActivity(), FriendViewHolder.class, mFriends));
        LinearLayoutManager LM = new LinearLayoutManager(getActivity());
        mRecyclerviewFriends.setLayoutManager(LM);
        mRecyclerviewRequests.setAdapter(new EasyRecyclerAdapter<Friendship>(getActivity(), FriendViewHolder.class, mRequests));
        LinearLayoutManager LM2 = new LinearLayoutManager(getActivity());
        mRecyclerviewRequests.setLayoutManager(LM2);
    }

    private void getData() {
        mListenerObject.onMessageReceived("hide-gui");
        mFriends.clear();
        mRequests.clear();

        mConvoHelper.getFriendships(new Callback<Response<List<Friendship>>>() {
            @Override
            public void onResponse(Call<Response<List<Friendship>>> call, retrofit2.Response<Response<List<Friendship>>> response) {
                Log.e("executed",".");
                Log.e("first Data",String.valueOf(response.body().getData().size()));
                for (Friendship fr : response.body().getData()) {
                    if (fr.getStatus() == 1) {
                        mFriends.add(fr);
                    } else {
                        mRequests.add(fr);
                    }
                }
                Log.e("Final Data",String.valueOf(mFriends.size())+String.valueOf(mRequests.size()));
                mRecyclerviewFriends.getAdapter().notifyDataSetChanged();
                mRecyclerviewRequests.getAdapter().notifyDataSetChanged();
                mListenerObject.onMessageReceived("show-gui");
            }

            @Override
            public void onFailure(Call<Response<List<Friendship>>> call, Throwable t) {
                Log.e("executed",t.getMessage());
                mListenerObject.onMessageReceived("show-gui");
            }
        });

    }

    private void initListeners() {

        mButtonRequests.setBackgroundColor(getResources().getColor(R.color.white));
        mButtonRequests.setTextColor(getResources().getColor(R.color.teal));
        mButtonRequests.setIconColor(getResources().getColor(R.color.teal));
        mButtonFriends.setBackgroundColor(getResources().getColor(R.color.teal));
        mButtonFriends.setTextColor(getResources().getColor(R.color.white));
        mButtonFriends.setIconColor(getResources().getColor(R.color.white));

        mButtonFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRequestsLayout.setVisibility(View.GONE);
                mFriendsLayout.setVisibility(View.VISIBLE);

                mButtonRequests.setBackgroundColor(getResources().getColor(R.color.white));
                mButtonRequests.setTextColor(getResources().getColor(R.color.teal));
                mButtonRequests.setIconColor(getResources().getColor(R.color.teal));
                mButtonFriends.setBackgroundColor(getResources().getColor(R.color.teal));
                mButtonFriends.setTextColor(getResources().getColor(R.color.white));
                mButtonFriends.setIconColor(getResources().getColor(R.color.white));

            }
        });

        mButtonRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFriendsLayout.setVisibility(View.GONE);
                mRequestsLayout.setVisibility(View.VISIBLE);

                mButtonFriends.setBackgroundColor(getResources().getColor(R.color.white));
                mButtonFriends.setTextColor(getResources().getColor(R.color.teal));
                mButtonFriends.setIconColor(getResources().getColor(R.color.teal));
                mButtonRequests.setBackgroundColor(getResources().getColor(R.color.teal));
                mButtonRequests.setTextColor(getResources().getColor(R.color.white));
                mButtonRequests.setIconColor(getResources().getColor(R.color.white));


            }
        });
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
}
