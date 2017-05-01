package com.keensense.vrconvo.apps.profile.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keensense.vrconvo.R;
import com.keensense.vrconvo.models.Feed;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

/**
 * Created by Panch on 1.12.2016.
 */

@LayoutId(R.layout.feed)
public class FeedViewHolder extends ItemViewHolder<Feed> {

    //Annotate every field with the ID of the view in the layout.
    //The views will automatically be assigned to the fields.

    @ViewId(R.id.feed_image)
    ImageView mFeedImage;

    @ViewId(R.id.feed_content)
    TextView mFeedContent;

    @ViewId(R.id.feed_owner)
    TextView mFeedOwner;

    @ViewId(R.id.feed_time)
    TextView mFeedTime;

    //Extend ItemViewHolder and call super(view)
    public FeedViewHolder(View view) {
        super(view);
    }

    //Override onSetValues() to set the values of the items in the views.
    @Override
    public void onSetValues(final Feed fr, PositionInfo positionInfo) {
        renderFeed(fr);
    }

    private void renderFeed(final Feed feed) {
        mFeedContent.setText(feed.getFeed_content());
        mFeedOwner.setText(feed.getUser().getUsername());
        mFeedTime.setText(feed.getFeed_time());
        if (feed.getFeed_image() != null && feed.getFeed_image().length() > 0) {
            Glide.with(getContext()).load(feed.getFeed_image()).into(mFeedImage);
        }
    }
}
