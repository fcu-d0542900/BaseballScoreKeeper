package com.example.yuru.baseballscorekeeper.Modal;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by YURU on 2018/6/16.
 */

public class RecordItemCenter {
    private ImageView showCenterView;
    private ImageView showEndView,showHRView;
    private ImageView showHit1View,showHit2View,showHit3View,showHit4View;
    private ImageView showChangeGarrison,showChangeHitter;


    //ShowCenter
    public void setShowCenterView(ImageView showCenterView) {
        this.showCenterView = showCenterView;
    }

    public  void setShowCenterValue(int imageID) {
        showCenterView.setImageResource(imageID);
    }

    public void setShowCenterVisibility(boolean isShow) {
        if (showCenterView != null)
            showCenterView.setVisibility(isShow? View.VISIBLE:View.GONE);
    }


    //ShowEnd
    public void setShowEndView(ImageView showEndView) {
        this.showEndView = showEndView;
    }

    public void setShowEndViewVisibility(boolean isShow) {
        if (showEndView != null)
            showEndView.setVisibility(isShow? View.VISIBLE:View.GONE);
    }


    //ShowHit1
    public void setShowHit1View(ImageView showHit1View) {
        this.showHit1View = showHit1View;
    }

    public void setShowHit1ViewVisibility(boolean isShow) {
        if (showHit1View != null)
            showHit1View.setVisibility(isShow? View.VISIBLE:View.GONE);
    }

    //ShowHit2
    public void setShowHit2View(ImageView showHit2View) {
        this.showHit2View = showHit2View;
    }

    public void setShowHit2ViewVisibility(boolean isShow) {
        if (showHit2View != null)
            showHit2View.setVisibility(isShow? View.VISIBLE:View.GONE);
    }

    //ShowHit3
    public void setShowHit3View(ImageView showHit3View) {
        this.showHit3View = showHit3View;
    }

    public void setShowHit3ViewVisibility(boolean isShow) {
        if (showHit3View != null)
            showHit3View.setVisibility(isShow? View.VISIBLE:View.GONE);
    }

    //ShowHit4
    public void setShowHit4View(ImageView showHit4View) {
        this.showHit4View = showHit4View;
    }

    public void setShowHit4ViewVisibility(boolean isShow) {
        if (showHit4View != null)
            showHit4View.setVisibility(isShow? View.VISIBLE:View.GONE);
    }

    //ShowChangeHitter
    public void setShowChangeHitter(ImageView showChangeHitter) {
        this.showChangeHitter = showChangeHitter;
    }

    public void setShowChangeHitterVisibility(boolean isShow) {
        if (showChangeHitter != null)
            showChangeHitter.setVisibility(isShow? View.VISIBLE:View.GONE);
    }

    //ShowChangeGarrison
    public void setShowChangeGarrison(ImageView showChangeGarrison) {
        this.showChangeGarrison = showChangeGarrison;
    }

    public void setShowChangeGarrisonVisibility(boolean isShow) {
        if (showChangeGarrison != null)
            showChangeGarrison.setVisibility(isShow? View.VISIBLE:View.GONE);
    }

    //HR
    public void setShowHRView(ImageView showHRView) {
        this.showHRView = showHRView;
    }

    public void setShowHRViewVisibility(boolean isShow) {
        if (showHRView != null)
            showHRView.setVisibility(isShow?View.VISIBLE:View.GONE);
    }
}
