package com.baseball;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by YURU on 2018/6/16.
 */

public class RecordItemFirstBase {
    private ImageView showZeroView,showHRView;
    private FrameLayout showOneView,showTwoView,showThreeView;
    private ImageView showSacrificeFly,showSacrificeHits;

    public void setShowZeroView(ImageView showZeroView) {
        this.showZeroView = showZeroView;
    }

    public void setShowZeroViewVisibility(boolean isShow) {
        if (showZeroView != null)
            showZeroView.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    public void setShowOneView(FrameLayout showOneView) {
        this.showOneView = showOneView;
    }

    public void setShowOneViewVisibility(boolean isShow) {
        if (showOneView != null)
            showOneView.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    public void setShowTwoView(FrameLayout showTwoView) {
        this.showTwoView = showTwoView;
    }

    public void setShowTwoViewVisibility(boolean isShow) {
        if (showTwoView != null)
            showTwoView.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    public void setShowThreeView(FrameLayout showThreeView) {
        this.showThreeView = showThreeView;
    }

    public void setShowThreeViewVisibility(boolean isShow) {
        if (showThreeView != null)
            showThreeView.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    public void setShowHRView(ImageView showHRView) {
        this.showHRView = showHRView;
    }

    public void setShowHRViewVisibility(boolean isShow) {
        if (showHRView != null)
            showHRView.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    public void setShowSacrificeFly(ImageView showSacrificeFly) {
        this.showSacrificeFly = showSacrificeFly;
    }

    public void setShowSacrificeFlyVisibility(boolean isShow) {
        if (showSacrificeFly != null)
            showSacrificeFly.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    public void setShowSacrificeHits(ImageView showSacrificeHits) {
        this.showSacrificeHits = showSacrificeHits;
    }

    public void setShowSacrificeHitsVisibility(boolean isShow) {
        if (showSacrificeHits != null)
            showSacrificeHits.setVisibility(isShow?View.VISIBLE:View.GONE);
    }
}
