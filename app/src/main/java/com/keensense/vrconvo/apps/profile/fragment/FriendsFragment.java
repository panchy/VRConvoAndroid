package com.keensense.vrconvo.apps.profile.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.keensense.vrconvo.R;
import com.keensense.vrconvo.apps.profile.activity.ProfileActivity;
import com.keensense.vrconvo.apps.profile.adapter.FriendViewHolder;
import com.keensense.vrconvo.events.FriendsUpdatedEvent;
import com.keensense.vrconvo.listeners.FragmentListener;
import com.keensense.vrconvo.models.Friendship;
import com.keensense.vrconvo.models.LoginResponse;
import com.keensense.vrconvo.models.Response;
import com.keensense.vrconvo.models.User;
import com.keensense.vrconvo.models.UserSuggestion;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;

import static com.keensense.vrconvo.apps.profile.activity.ProfileActivity.mConvoHelper;


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
    FrameLayout mFriendsLayout;
    @BindView(R.id.requests_layout)
    LinearLayout mRequestsLayout;
    @BindView(R.id.friendsdata_layout)
    LinearLayout mFriendsDataLayout;
    @BindView(R.id.requests_button)
    FancyButton mButtonRequests;
    @BindView(R.id.friends_button)
    FancyButton mButtonFriends;
    @BindView(R.id.fab_search)
    FloatingActionButton mFabSearch;
    @BindView(R.id.floating_search_view)
    FloatingSearchView mSearchView;

    @BindView(R.id.textview_nofriends)
    AppCompatTextView mNoFriendsText;
    @BindView(R.id.textview_nofriendreqs)
    AppCompatTextView mNoFriendReqsText;

    private FragmentListener mListenerObject;

    private static List<Friendship> mFriends = new ArrayList<>();
    private static List<Friendship> mRequests = new ArrayList<>();

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
                if (response.body().getData() != null) {
                    for (Friendship fr : response.body().getData()) {
                        if (fr.getStatus() == 1) {
                            mFriends.add(fr);
                        } else {
                            mRequests.add(fr);
                        }
                    }
                    Log.e("Final Data", String.valueOf(mFriends.size()) + String.valueOf(mRequests.size()));

                }

                if (mFriends.size() == 0) {
                    mNoFriendsText.setVisibility(View.VISIBLE);
                } else {
                    mNoFriendsText.setVisibility(View.GONE);
                }

                if (mRequests.size() == 0) {
                    mNoFriendReqsText.setVisibility(View.VISIBLE);
                } else {
                    mNoFriendReqsText.setVisibility(View.GONE);
                }


                mRecyclerviewFriends.getAdapter().notifyDataSetChanged();
                mRecyclerviewRequests.getAdapter().notifyDataSetChanged();
                mListenerObject.onMessageReceived("show-gui");
            }

            @Override
            public void onFailure(Call<Response<List<Friendship>>> call, Throwable t) {
                Log.e("executed", t.getMessage());
                mListenerObject.onMessageReceived("show-gui");
            }
        });

    }

    private int lastIdSelected=0;
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

        mFabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchView.setVisibility(View.VISIBLE);
                mSearchView.setSearchFocused(true);
            }
        });

        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                mSearchView.setVisibility(View.VISIBLE);
                mFabSearch.setVisibility(View.GONE);
                mButtonFriends.setVisibility(View.GONE);
                mButtonRequests.setVisibility(View.GONE);
                mFriendsDataLayout.setVisibility(View.GONE);
            }

            @Override
            public void onFocusCleared() {
                mSearchView.clearQuery();
                mSearchView.clearSuggestions();
                mSearchView.setVisibility(View.GONE);
                mFabSearch.setVisibility(View.VISIBLE);
                mButtonFriends.setVisibility(View.VISIBLE);
                mButtonRequests.setVisibility(View.VISIBLE);
                mFriendsDataLayout.setVisibility(View.VISIBLE);
            }
        });

        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                mSearchView.clearSuggestions();
                if (newQuery.length() >= 2) {
                    mConvoHelper.searchUsers(newQuery, new Callback<Response<List<User>>>() {
                        @Override
                        public void onResponse(Call<Response<List<User>>> call, retrofit2.Response<Response<List<User>>> response) {
                            final List<UserSuggestion> sggs = new ArrayList<UserSuggestion>();
                            for (final User u : response.body().getData()) {
                                mConvoHelper.checkFriendship(String.valueOf(u.getId()), new Callback<Response>() {
                                    @Override
                                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                        UserSuggestion sug = new UserSuggestion(u.getUsername());
                                        sug.setId(u.getId());
                                        sug.setLast_active(u.getUserInfos().getLast_active());
                                        if (response.body().getMessage().equals("OK!")) {
                                            sug.setCanAdd(true);
                                        } else {
                                            sug.setCanAdd(false);
                                        }
                                        sggs.add(sug);
                                        mSearchView.swapSuggestions(sggs);
                                    }

                                    @Override
                                    public void onFailure(Call<Response> call, Throwable t) {

                                    }
                                });

                            }

                        }

                        @Override
                        public void onFailure(Call<Response<List<User>>> call, Throwable t) {

                        }
                    });
                }
            }
        });


        final DialogInterface.OnClickListener onClickListenerBind = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                mConvoHelper.sendFriendRequest(String.valueOf(lastIdSelected), new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if (response.body().getMessage().equals("OK!")) {
                            Snackbar.make(mFriendsLayout, "Request has been sent.", 2000).show();
                            getData();
                        } else {
                            Snackbar.make(mFriendsLayout, response.body().getMessage(), 2000);
                        }
                        mSearchView.setSearchFocused(false);
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Snackbar.make(mFriendsLayout, "A network error has occured!", 2000);
                        mSearchView.setSearchFocused(false);
                    }
                });

                dialogInterface.cancel();
            }
        };
        mSearchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {
            @Override
            public void onBindSuggestion(View suggestionView, ImageView leftIcon, TextView textView, SearchSuggestion item, int itemPosition) {
                final UserSuggestion sg = (UserSuggestion) item;
                if (sg.isCanAdd()) {
                    textView.setTextColor(Color.GREEN);
                    suggestionView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            lastIdSelected=sg.getId();
                            //TODO Add here. with a yes no material dialog.
                            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                                    .setMessage("Do you want to send this user ("+sg.getBody()+") a friend request?")
                                    .setNegativeButton("Cancel", null)
                                    .setPositiveButton("Yes", onClickListenerBind)
                                    .create();
                            dialog.show();
                        }
                    });
                } else {
                    textView.setTextColor(Color.RED);
                    textView.setText(String.format("%s (Already friends or pending request.)",sg.getBody()));
                }
            }
        });


    }

    @Subscribe
    public void onFriendsDataChangedEvent(FriendsUpdatedEvent event) {
        getData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListenerObject = (ProfileActivity) context;
        mListenerObject.onFragmentAttached();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDetach() {
        mListenerObject.onFragmentDetached();
        mListenerObject = null;
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDetach();
    }
}
