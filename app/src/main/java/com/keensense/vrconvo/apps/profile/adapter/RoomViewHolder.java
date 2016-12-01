package com.keensense.vrconvo.apps.profile.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keensense.vrconvo.R;
import com.keensense.vrconvo.model.Room;
import com.keensense.vrconvo.network.ConvoClient;
import com.keensense.vrconvo.widget.FullscreenImageViewActivity;

import mehdi.sakout.fancybuttons.FancyButton;
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
