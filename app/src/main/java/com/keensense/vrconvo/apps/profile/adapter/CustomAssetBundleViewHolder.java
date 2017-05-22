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
import com.keensense.vrconvo.events.SnackbarRequestEvent;
import com.keensense.vrconvo.events.UserInfoChangedEvent;
import com.keensense.vrconvo.models.Character;
import com.keensense.vrconvo.models.CustomAssetBundle;
import com.keensense.vrconvo.models.LoginResponse;
import com.keensense.vrconvo.models.Response;
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
public class CustomAssetBundleViewHolder extends ItemViewHolder<CustomAssetBundle> {

    //Annotate every field with the ID of the view in the layout.
    //The views will automatically be assigned to the fields.
    @ViewId(R.id.image)
    ImageView imageView;

    @ViewId(R.id.title)
    TextView textViewName;

    @ViewId(R.id.button)
    FancyButton button;


    @ViewId(R.id.type)
    TextView textViewType;


    //Extend ItemViewHolder and call super(view)
    public CustomAssetBundleViewHolder(View view) {
        super(view);
    }

    //Override onSetValues() to set the values of the items in the views.
    @Override
    public void onSetValues(final CustomAssetBundle character, PositionInfo positionInfo) {
        //Log.e("RoomViewHolder",ConvoClient.baseUrl + "gameimages/"+room.getImage());
        Glide.with(getContext()).load(ConvoClient.baseUrl + ConvoClient.imagesFolder + "/" + character.getImage()).into(imageView);
        textViewName.setText("( "+String.valueOf(character.getCost())+" CC"+" )");
        switch (character.getType())
        {
            case 0:
                textViewType.setText("Custom Asset");
                break;
            case 1:
                textViewType.setText("Room");
                break;
            case 2:
                textViewType.setText("Character");
                break;
        }
        if (character.isLocked()) {
            button.setText("UNLOCK");
            button.setIconResource("\uf023");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogInterface.OnClickListener onclick = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ConvoHelper mConvoHelper = new ConvoHelper(ProfileActivity.USER_INFO.getUsername(), ProfileActivity.USER_INFO.getPassword());
                            mConvoHelper.unlockContent("customassetbundle", character.getId(), new Callback<Response<LoginResponse>>() {
                                @Override
                                public void onResponse(Call<Response<LoginResponse>> call, retrofit2.Response<Response<LoginResponse>> response) {
                                    if (response.body().getMessage().equals("OK!")) {
                                        ProfileActivity.USER_INFO = response.body().getData();
                                        button.setText("OWNED");
                                        button.setIconResource("\uf13e");
                                        button.setOnClickListener(null);
                                        EventBus.getDefault().post(new UserInfoChangedEvent(response.body().getData().getUserInfo()));
                                    }
                                    else
                                    {
                                        EventBus.getDefault().post(new SnackbarRequestEvent(response.body().getMessage()));
                                    }
                                }

                                @Override
                                public void onFailure(Call<Response<LoginResponse>> call, Throwable t) {
                                    EventBus.getDefault().post(new SnackbarRequestEvent("An error occurred."));
                                }
                            });

                        }
                    };
                    AlertDialog dialog = new AlertDialog.Builder(getContext())
                            .setMessage(Html.fromHtml("Are you sure about unlocking this content for <b>"+ String.valueOf(character.getCost()) +"</b> Convo Coins ?"))
                            .setPositiveButton("Yes", onclick)
                            .setNegativeButton("No", null)
                            .create();
                    dialog.show();

                }
            });
        } else {

            button.setText("OWNED");
            button.setIconResource("\uf13e");
            button.setOnClickListener(null);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fullscreen = new Intent(getContext(), FullscreenImageViewActivity.class);
                fullscreen.putExtra("image_url", ConvoClient.baseUrl + "gameimages/" + character.getImage());
                getContext().startActivity(fullscreen);
            }
        });

    }
}
