package com.keensense.vrconvo.apps.profile.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keensense.vrconvo.R;
import com.keensense.vrconvo.apps.profile.activity.ProfileActivity;
import com.keensense.vrconvo.events.FriendsUpdatedEvent;
import com.keensense.vrconvo.events.SnackbarRequestEvent;
import com.keensense.vrconvo.events.UserInfoChangedEvent;
import com.keensense.vrconvo.models.Friendship;
import com.keensense.vrconvo.models.LoginResponse;
import com.keensense.vrconvo.models.Response;
import com.keensense.vrconvo.models.Room;
import com.keensense.vrconvo.network.ConvoClient;
import com.keensense.vrconvo.network.ConvoHelper;
import com.keensense.vrconvo.widget.FullscreenImageViewActivity;

import org.greenrobot.eventbus.EventBus;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

import static com.keensense.vrconvo.apps.profile.activity.ProfileActivity.mConvoHelper;

/**
 * Created by Panch on 1.12.2016.
 */

@LayoutId(R.layout.friend)
public class FriendViewHolder extends ItemViewHolder<Friendship> {

    //Annotate every field with the ID of the view in the layout.
    //The views will automatically be assigned to the fields.

    @ViewId(R.id.username)
    TextView textViewName;
    @ViewId(R.id.userinfo)
    TextView textViewStatus;
    @ViewId(R.id.lastactive)
    TextView textViewLastActive;
    @ViewId(R.id.wasactive)
    TextView txtWasActive;

    @ViewId(R.id.buttonAccept)
    FancyButton buttonAccept;
    @ViewId(R.id.buttonRefuse)
    FancyButton buttonRefuse;
    @ViewId(R.id.buttonCancel)
    FancyButton buttonCancel;
    @ViewId(R.id.buttonRemove)
    FancyButton buttonRemove;

    //Extend ItemViewHolder and call super(view)
    public FriendViewHolder(View view) {
        super(view);
    }

    //Override onSetValues() to set the values of the items in the views.
    @Override
    public void onSetValues(final Friendship fr, PositionInfo positionInfo) {
        renderFriendship(fr,positionInfo);
    }

    private void renderFriendship(final Friendship fr,PositionInfo positionInfo)
    {
        buttonCancel.setVisibility(View.GONE);
        buttonAccept.setVisibility(View.GONE);
        buttonRefuse.setVisibility(View.GONE);
        txtWasActive.setVisibility(View.GONE);

        buttonAccept.setOnClickListener(null);
        buttonRefuse.setOnClickListener(null);
        buttonCancel.setOnClickListener(null);
        buttonRemove.setOnClickListener(null);

        //I have received a request
        if(fr.getPlayerReceivedId() == ProfileActivity.USER_INFO.getId())
        {
            if(!fr.getSender().getLast_active().equals("Online"))
            {
                txtWasActive.setVisibility(View.VISIBLE);
            }
            //Pending approval
            if(fr.getStatus() == 0)
            {
                buttonAccept.setVisibility(View.VISIBLE);
                buttonRefuse.setVisibility(View.VISIBLE);
                textViewName.setText(fr.getPlayerSentUsername());
                textViewStatus.setText(fr.getSender().getCurrent_status());
                textViewLastActive.setText(fr.getSender().getLast_active());


                buttonAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mConvoHelper.updateFriendRequest(fr.getId(), 1, new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                EventBus.getDefault().post(new FriendsUpdatedEvent());
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {

                            }
                        });

                    }
                });

                buttonRefuse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mConvoHelper.updateFriendRequest(fr.getId(), 0, new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                EventBus.getDefault().post(new FriendsUpdatedEvent());
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {

                            }
                        });
                    }
                });

            }
            //we are friends
            else
            {
                buttonRemove.setVisibility(View.VISIBLE);
                textViewName.setText(fr.getPlayerSentUsername());
                textViewStatus.setText(fr.getSender().getCurrent_status());
                textViewLastActive.setText(fr.getSender().getLast_active());


                buttonRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mConvoHelper.updateFriendRequest(fr.getId(), 0, new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                EventBus.getDefault().post(new FriendsUpdatedEvent());
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {

                            }
                        });

                    }
                });


            }
        }
        //I have sent a request
        else
        {
            if(!fr.getReceiver().getLast_active().equals("Online"))
            {
                txtWasActive.setVisibility(View.VISIBLE);
            }
            //Pending approval
            if(fr.getStatus() == 0)
            {
                buttonCancel.setVisibility(View.VISIBLE);
                textViewName.setText(fr.getPlayerReceivedUsername());
                textViewStatus.setText(fr.getReceiver().getCurrent_status());
                textViewLastActive.setText(fr.getReceiver().getLast_active());

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mConvoHelper.updateFriendRequest(fr.getId(), 0, new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                EventBus.getDefault().post(new FriendsUpdatedEvent());
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {

                            }
                        });

                    }
                });
            }
            //we are friends
            else
            {
                buttonRemove.setVisibility(View.VISIBLE);
                textViewName.setText(fr.getPlayerReceivedUsername());
                textViewStatus.setText(fr.getReceiver().getCurrent_status());
                textViewLastActive.setText(fr.getReceiver().getLast_active());

                buttonRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mConvoHelper.updateFriendRequest(fr.getId(), 0, new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                EventBus.getDefault().post(new FriendsUpdatedEvent());
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {

                            }
                        });

                    }
                });
            }
        }
    }
}
