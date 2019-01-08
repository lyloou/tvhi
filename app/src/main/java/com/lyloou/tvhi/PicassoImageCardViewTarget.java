package com.lyloou.tvhi;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class PicassoImageCardViewTarget implements Target {
    private ImageCardView mImageCardView;

    public PicassoImageCardViewTarget(ImageCardView imageCardView) {
        mImageCardView = imageCardView;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
        Drawable bitmapDrawable = new BitmapDrawable(mImageCardView.getResources(), bitmap);
        mImageCardView.setMainImage(bitmapDrawable);
    }

    @Override
    public void onBitmapFailed(Drawable drawable) {
        mImageCardView.setMainImage(drawable);
    }

    @Override
    public void onPrepareLoad(Drawable drawable) {
        // Do nothing, default_background manager has its own transitions
    }
}