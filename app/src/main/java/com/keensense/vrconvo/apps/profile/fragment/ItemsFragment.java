package com.keensense.vrconvo.apps.profile.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keensense.vrconvo.R;
import com.keensense.vrconvo.apps.profile.activity.ProfileActivity;
import com.keensense.vrconvo.apps.profile.adapter.CharacterViewHolder;
import com.keensense.vrconvo.apps.profile.adapter.RoomViewHolder;
import com.keensense.vrconvo.listeners.FragmentListener;
import com.keensense.vrconvo.models.Character;
import com.keensense.vrconvo.models.Response;
import com.keensense.vrconvo.models.Room;
import com.keensense.vrconvo.models.UnlockedCharacter;
import com.keensense.vrconvo.models.UnlockedRoom;
import com.keensense.vrconvo.network.ConvoHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;


/**
 * A simple fragment class with listener included. Created by Panch.
 */
public class ItemsFragment extends Fragment {
    private int LayoutId = R.layout.fragment_items;

    @BindView(R.id.recyclerview_characters)
    RecyclerView mRecyclerviewCharacters;
    @BindView(R.id.recyclerview_rooms)
    RecyclerView mRecyclerviewRooms;

    private FragmentListener mListenerObject;

    private static List<Room> mRooms = new ArrayList<>();
    private static List<Character> mCharacters = new ArrayList<>();

    private ConvoHelper mConvoHelper = null;


    public ItemsFragment() {
        // Required empty public constructor
    }

    private static Fragment fragment = null;

    public static ItemsFragment newInstance() {
        if (fragment == null) {
            fragment = new ItemsFragment();
        }
        return (ItemsFragment) fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(LayoutId, container, false);
        mConvoHelper = new ConvoHelper(ProfileActivity.USER_INFO.getUsername(), ProfileActivity.USER_INFO.getPassword());
        ButterKnife.bind(this, v);
        initAdapters();
        getData();
        return v;
    }

    private void initAdapters() {
        mRecyclerviewRooms.setAdapter(new EasyRecyclerAdapter<Room>(getActivity(), RoomViewHolder.class, mRooms));
        GridLayoutManager GLM = new GridLayoutManager(getActivity(), 2);
        mRecyclerviewRooms.setLayoutManager(GLM);
        mRecyclerviewCharacters.setAdapter(new EasyRecyclerAdapter<Character>(getActivity(), CharacterViewHolder.class, mCharacters));
        GridLayoutManager GLM2 = new GridLayoutManager(getActivity(), 2);
        mRecyclerviewCharacters.setLayoutManager(GLM2);
    }

    private void getData() {
        mListenerObject.onMessageReceived("hide-gui");
        mCharacters.clear();
        mRooms.clear();
        for (UnlockedRoom room : ProfileActivity.USER_INFO.getUnlockedRooms()) {
            room.getRoom().setLocked(false);
            mRooms.add(room.getRoom());
        }
        for (UnlockedCharacter character : ProfileActivity.USER_INFO.getUnlockedCharacters()) {
            character.getCharacter().setLocked(false);
            mCharacters.add(character.getCharacter());
        }

        mConvoHelper.getAllRooms(new Callback<Response<List<Room>>>() {
            @Override
            public void onResponse(Call<Response<List<Room>>> call, retrofit2.Response<Response<List<Room>>> response) {
                for (Room room : response.body().getData()) {
                    if (!mRooms.contains(room)) {
                        room.setLocked(true);
                        mRooms.add(room);
                    }
                }
                mRecyclerviewRooms.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Response<List<Room>>> call, Throwable t) {

            }
        });

        mConvoHelper.getAllCharacters(new Callback<Response<List<Character>>>() {
            @Override
            public void onResponse(Call<Response<List<Character>>> call, retrofit2.Response<Response<List<Character>>> response) {
                for (Character character : response.body().getData()) {
                    if (!mCharacters.contains(character)) {
                        character.setLocked(true);
                        mCharacters.add(character);
                    }
                }
                mRecyclerviewCharacters.getAdapter().notifyDataSetChanged();
                mListenerObject.onMessageReceived("show-gui");
            }

            @Override
            public void onFailure(Call<Response<List<Character>>> call, Throwable t) {
                mListenerObject.onMessageReceived("show-gui");
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
