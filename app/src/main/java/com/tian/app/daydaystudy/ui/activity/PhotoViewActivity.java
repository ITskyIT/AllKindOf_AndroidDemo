package com.tian.app.daydaystudy.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.tian.app.daydaystudy.R;
import com.tian.app.daydaystudy.util.MyUtils;
import com.tian.app.daydaystudy.util.SystemUiVisibilityUtil;
import com.tian.app.daydaystudy.view.PullBackLayout;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewActivity extends AppCompatActivity implements PullBackLayout.Callback{
    PullBackLayout pullBackLayout;
    RelativeLayout background;
    private boolean mIsToolBarHidden;
    private boolean mIsStatusBarHidden;
    private ColorDrawable mBackground;
    Toolbar toolbar;
    PhotoView photoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
       photoView= (PhotoView) findViewById(R.id.photo_touch_iv);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        background= (RelativeLayout) findViewById(R.id.background);
        pullBackLayout= (PullBackLayout) findViewById(R.id.pull_back_layout);


        pullBackLayout.setCallback(this);



        toolBarFadeIn();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initBackground();
        photoView.setImageResource(R.mipmap.gril);
        setPhotoViewClickEvent();
    }
    private void setPhotoViewClickEvent() {
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                hideOrShowToolbar();
                hideOrShowStatusBar();
            }
        });
    }

    private void initBackground() {
        mBackground = new ColorDrawable(Color.BLACK);
        MyUtils.getRootView(this).setBackgroundDrawable(mBackground);
    }


    protected void hideOrShowToolbar() {
        toolbar.animate()
                .alpha(mIsToolBarHidden ? 1.0f : 0.0f)
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsToolBarHidden = !mIsToolBarHidden;
    }

    private void hideOrShowStatusBar() {
        if (mIsStatusBarHidden) {
            SystemUiVisibilityUtil.enter(PhotoViewActivity.this);
        } else {
            SystemUiVisibilityUtil.exit(PhotoViewActivity.this);
        }
        mIsStatusBarHidden = !mIsStatusBarHidden;
    }

    private void toolBarFadeIn() {
        mIsToolBarHidden = true;
        hideOrShowToolbar();
    }

    @Override
    public void onPullStart() {
        toolBarFadeOut();

        mIsStatusBarHidden = true;
        hideOrShowStatusBar();
    }

    private void toolBarFadeOut() {
        mIsToolBarHidden = false;
        hideOrShowToolbar();
    }

    @Override
    public void onPull(float progress) {
        progress = Math.min(1f, progress * 3f);
        mBackground.setAlpha((int) (0xff/*255*/ * (1f - progress)));
    }

    @Override
    public void onPullCancel() {
        toolBarFadeIn();
    }

    @Override
    public void onPullComplete() {
        supportFinishAfterTransition();
    }

    @Override
    public void supportFinishAfterTransition() {
        super.supportFinishAfterTransition();
    }
}
