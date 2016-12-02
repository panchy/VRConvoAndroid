package com.keensense.vrconvo.apps.profile.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keensense.vrconvo.R;
import com.keensense.vrconvo.apps.profile.activity.ProfileActivity;
import com.keensense.vrconvo.events.UserInfoChangedEvent;
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

/**
 * Created by Panch on 1.12.2016.
 */

@LayoutId(R.layout.item)
public class RoomViewHolder extends ItemViewHolder<Room> {

    //Annotate every field with the ID of the view in the layout.
    //The views will automatically be assigned to the fields.
    @ViewId(R.id.image)
    ImageView imageView;

    @ViewId(R.id.title)
    TextView textViewName;

    @ViewId(R.id.button)
    FancyButton button;

    //Extend ItemViewHolder and call super(view)
    public RoomViewHolder(View view) {
        super(view);
    }

    //Override onSetValues() to set the values of the items in the views.
    @Override
    public void onSetValues(final Room room, PositionInfo positionInfo) {
        //Log.e("RoomViewHolder",ConvoClient.baseUrl + "gameimages/"+room.getImage());
        Glide.with(getContext()).load(ConvoClient.baseUrl + "gameimages/"+room.getImage()).into(imageView);
        textViewName.setText(room.getRoomName());
        if(room.isLocked())
        {
            button.setText("UNLOCK");
            button.setIconResource("\uf023");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogInterface.OnClickListener onclick = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ConvoHelper mConvoHelper = new ConvoHelper(ProfileActivity.USER_INFO.getUsername(), ProfileActivity.USER_INFO.getPassword());
                            mConvoHelper.unlockContent("room", room.getId(), new Callback<Response<LoginResponse>>() {
                                @Override
                                public void onResponse(Call<Response<LoginResponse>> call, retrofit2.Response<Response<LoginResponse>> response) {
                                    if (response.body().getMessage().equals("OK!")) {
                                        ProfileActivity.USER_INFO = response.body().getData();
                                        button.setText("OWNED");
                                        button.setIconResource("\uf13e");
                                        button.setOnClickListener(null);
                                        EventBus.getDefault().post(new UserInfoChangedEvent(response.body().getData().getUserInfo().get(0)));
                                    }
                                }

                                @Override
                                public void onFailure(Call<Response<LoginResponse>> call, Throwable t) {

                                }
                            });

                        }
                    };
                    AlertDialog dialog = new AlertDialog.Builder(getContext())
                            .setMessage("Are you sure about unlocking this content?")
                            .setPositiveButton("Yes", onclick)
                            .setNegativeButton("No", null)
                            .create();
                    dialog.show();

                }
            });
        }
        else
        {
            button.setText("OWNED");
            button.setIconResource("\uf13e");
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fullscreen = new Intent(getContext(), FullscreenImageViewActivity.class);
                fullscreen.putExtra("image_url",ConvoClient.baseUrl + "gameimages/"+room.getImage());
                getContext().startActivity(fullscreen);
            }
        });

    }
}
