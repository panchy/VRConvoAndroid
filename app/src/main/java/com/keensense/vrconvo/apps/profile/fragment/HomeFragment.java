package com.keensense.vrconvo.apps.profile.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keensense.vrconvo.R;
import com.keensense.vrconvo.apps.profile.activity.ProfileActivity;
import com.keensense.vrconvo.apps.profile.adapter.FeedViewHolder;
import com.keensense.vrconvo.listeners.FragmentListener;
import com.keensense.vrconvo.models.Feed;
import com.keensense.vrconvo.models.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;

import static com.keensense.vrconvo.apps.profile.activity.ProfileActivity.mConvoHelper;


/**
 * A simple fragment class with listener included. Created by Panch.
 */
public class HomeFragment extends Fragment {
    private int LayoutId = R.layout.fragment_home;

    private FragmentListener mListenerObject;

    @BindView(R.id.infoText)
    TextView mInfoText;

    @BindView(R.id.recyclerview_feed)
    RecyclerView mFeedRecyclerView;

    private static List<Feed> mFeeds = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    private static Fragment fragment = null;

    public static HomeFragment newInstance() {
        if (fragment == null) {
            fragment = new HomeFragment();
        }
        return (HomeFragment) fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(LayoutId, container, false);
        ButterKnife.bind(this, v);

        mConvoHelper.updateStatus("In Android Application", new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });

        getData();

        return v;
    }

    Comparator<Feed> feedComparator = new Comparator<Feed>() {
        @Override
        public int compare(Feed feed, Feed t1) {
            return Long.valueOf(t1.ts()).compareTo(feed.ts());
        }
    };

    private void getData() {

        if (mFeeds == null) {
            mFeeds = new ArrayList<>();
        }

        mFeeds.clear();
        mConvoHelper.getFeeds(new Callback<Response<List<Feed>>>() {
            @Override
            public void onResponse(Call<Response<List<Feed>>> call, retrofit2.Response<Response<List<Feed>>> response) {

                mFeeds = response.body().getData();
                if (mFeeds != null && mFeeds.size() > 0) {
                    mFeedRecyclerView.setVisibility(View.VISIBLE);
                    mInfoText.setVisibility(View.GONE);
                    Collections.sort(mFeeds, feedComparator);
                    initRecyclerView();
                } else {
                    mFeedRecyclerView.setVisibility(View.GONE);
                    mInfoText.setVisibility(View.VISIBLE);
                    mInfoText.setText(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Response<List<Feed>>> call, Throwable t) {

            }
        });

    }

    private void initRecyclerView() {
        LinearLayoutManager LLM = new LinearLayoutManager(getActivity());
        mFeedRecyclerView.setLayoutManager(LLM);
        mFeedRecyclerView.setAdapter(new EasyRecyclerAdapter<Feed>(getActivity(), FeedViewHolder.class, mFeeds));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListenerObject = (ProfileActivity) context;
        mListenerObject.onFragmentAttached();
        mListenerObject.onMessageReceived("show-gui");
    }

    @Override
    public void onDetach() {
        mListenerObject.onFragmentDetached();
        mListenerObject = null;
        super.onDetach();
    }
}
