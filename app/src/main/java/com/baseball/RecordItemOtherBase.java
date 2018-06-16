package com.baseball;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by YURU on 2018/6/16.
 */

public class RecordItemOtherBase {
    private ImageView showActionNameView,showPushNumView,showToBaseView;
    private LinearLayout showActionView,showThrowView;

    public void setShowActionNameView(ImageView showActionNameView) {
        this.showActionNameView = showActionNameView;
    }

    public void setShowActionNameViewVisibility(boolean isShow) {
        if (showActionNameView != null)
            showActionNameView.setVisibility(isShow? View.VISIBLE:View.GONE);
    }

    public void setShowActionView(LinearLayout showActionView) {
        this.showActionView = showActionView;
    }

    public void setShowActionViewVisibility(boolean isShow) {
        if (showActionView != null)
            showActionView.setVisibility(isShow? View.VISIBLE:View.GONE);
    }

    public void setShowThrowView(LinearLayout showThrowView) {
        this.showThrowView = showThrowView;
    }

    public void setShowThrowViewVisibility(boolean isShow) {
        if (showThrowView != null)
            showThrowView.setVisibility(isShow? View.VISIBLE:View.GONE);
    }

    public void setShowPushNumView(ImageView showPushNumView) {
        this.showPushNumView = showPushNumView;
    }

    public void setShowPushNumViewVisibility(boolean isShow) {
        if (showPushNumView != null)
            showPushNumView.setVisibility(isShow? View.VISIBLE:View.INVISIBLE);
    }

    public void setShowToBaseView(ImageView showToBaseView) {
        this.showToBaseView = showToBaseView;
    }

    public void setShowToBaseViewVisibility(boolean isShow) {
        if (showToBaseView != null)
            showToBaseView.setVisibility(isShow? View.VISIBLE:View.INVISIBLE);
    }

}
