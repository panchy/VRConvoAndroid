package com.keensense.vrconvo.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.keensense.vrconvo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class FullscreenImageViewActivity extends AppCompatActivity {

    private String imageUrl=null;
    @BindView(R.id.image)
    ImageView mImage;

    PhotoViewAttacher mAttacher=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image_view);
        imageUrl = getIntent().getStringExtra("image_url");
        ButterKnife.bind(this);
        Glide.with(this).load(imageUrl).into(mImage);

        mAttacher = new PhotoViewAttacher(mImage);
        
        mAttacher.update();

    }
}
