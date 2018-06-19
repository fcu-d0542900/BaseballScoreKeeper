package com.baseball;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by YURU on 2018/6/16.
 */

public class RecordItemFirstBase {
    private ImageView showEndView;

    private ImageView showZeroView,showHRView;
    private FrameLayout showOneView,showTwoView,showThreeView;
    private ImageView showSacrificeFly,showSacrificeHits;

    public ImageView showFirstViewOneTop,firstViewOneNum,showFirstViewOneBottom;
    public ImageView showFirstViewOneAc1,showFirstViewOneAc2;

    public ImageView firstViewTwoNum,showFirstViewTwoAc;
    public ImageView firstViewThreeNum,showFirstViewThreeAc;

    //showEndView
    //在Center選擇結束半局後 顯示在一壘
    public void setShowEndView(ImageView showEndView) {
        this.showEndView = showEndView;
    }

    public void setShowEndViewVisibility(boolean isShow) {
        if (showEndView != null)
            showEndView.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //ZeroView
    public void setShowZeroView(ImageView showZeroView) {
        this.showZeroView = showZeroView;
    }

    public  void setShowZeroValue(int imageID) {
        showZeroView.setImageResource(imageID);
    }

    public void setShowZeroViewVisibility(boolean isShow) {
        if (showZeroView != null)
            showZeroView.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //OneView
    public void setShowOneView(FrameLayout showOneView) {
        this.showOneView = showOneView;
    }

    public void setShowOneViewVisibility(boolean isShow) {
        if (showOneView != null)
            showOneView.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //TwoView
    public void setShowTwoView(FrameLayout showTwoView) {
        this.showTwoView = showTwoView;
    }

    public void setShowTwoViewVisibility(boolean isShow) {
        if (showTwoView != null)
            showTwoView.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //ThreeView
    public void setShowThreeView(FrameLayout showThreeView) {
        this.showThreeView = showThreeView;
    }

    public void setShowThreeViewVisibility(boolean isShow) {
        if (showThreeView != null)
            showThreeView.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //HRView
    public void setShowHRView(ImageView showHRView) {
        this.showHRView = showHRView;
    }

    public void setShowHRViewVisibility(boolean isShow) {
        if (showHRView != null)
            showHRView.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //SacrificeFly
    public void setShowSacrificeFly(ImageView showSacrificeFly) {
        this.showSacrificeFly = showSacrificeFly;
    }

    public void setShowSacrificeFlyVisibility(boolean isShow) {
        if (showSacrificeFly != null)
            showSacrificeFly.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //SacrificeHits
    public void setShowSacrificeHits(ImageView showSacrificeHits) {
        this.showSacrificeHits = showSacrificeHits;
    }

    public void setShowSacrificeHitsVisibility(boolean isShow) {
        if (showSacrificeHits != null)
            showSacrificeHits.setVisibility(isShow?View.VISIBLE:View.GONE);
    }


    //showFirstViewOneTop
    public void setShowFirstViewOneTop(ImageView firstViewOneTop) {
        this.showFirstViewOneTop = firstViewOneTop;
    }

    public  void setShowFirstViewOneTopValue(int imageID) {
        showFirstViewOneTop.setImageResource(imageID);
    }

    public void setShowFirstViewOneTopVisibility(boolean isShow) {
        if (showFirstViewOneTop != null)
            showFirstViewOneTop.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //FirstViewOneNum
    public void setFirstViewOneNum(ImageView firstViewOneNum) {
        this.firstViewOneNum = firstViewOneNum;
    }

    public  void setFirstViewOneNumValue(int imageID) {
        firstViewOneNum.setImageResource(imageID);
    }

    //showFirstViewOneBottom
    public void setShowFirstViewOneBottom(ImageView firstViewOneBottom) {
        this.showFirstViewOneBottom = firstViewOneBottom;
    }

    public void setShowFirstViewOneBottomVisibility(boolean isShow) {
        if (showFirstViewOneBottom != null)
            showFirstViewOneBottom.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //FirstViewOneAc1
    public void setShowFirstViewOneAc1(ImageView showFirstViewOneAc1) {
        this.showFirstViewOneAc1 = showFirstViewOneAc1;
    }

    public  void setFirstViewOneAc1Value(int imageID) {
        showFirstViewOneAc1.setImageResource(imageID);
    }

    public void setShowFirstViewOneAc1Visibility(boolean isShow) {
        if (showFirstViewOneAc1 != null)
            showFirstViewOneAc1.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //FirstViewOneAc2
    public void setShowFirstViewOneAc2(ImageView showFirstViewOneAc2) {
        this.showFirstViewOneAc2 = showFirstViewOneAc2;
    }

    public  void setFirstViewOneAc2Value(int imageID) {
        showFirstViewOneAc2.setImageResource(imageID);
    }

    public void setShowFirstViewOneAc2Visibility(boolean isShow) {
        if (showFirstViewOneAc2 != null)
            showFirstViewOneAc2.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //FirstViewTwoNum
    public void setFirstViewTwoNum(ImageView firstViewTwoNum) {
        this.firstViewTwoNum = firstViewTwoNum;
    }

    public  void setFirstViewTwoNumValue(int imageID) {
        firstViewTwoNum.setImageResource(imageID);
    }

    //ShowFirstViewTwoAc
    public void setShowFirstViewTwoAc(ImageView showFirstViewTwoAc) {
        this.showFirstViewTwoAc = showFirstViewTwoAc;
    }

    public  void setFirstViewTwoAcValue(int imageID) {
        showFirstViewTwoAc.setImageResource(imageID);
    }

    public void setShowFirstViewTwoAcVisibility(boolean isShow) {
        if (showFirstViewTwoAc != null)
            showFirstViewTwoAc.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //FirstViewThreeNum
    public void setFirstViewThreeNum(ImageView firstViewThreeNum) {
        this.firstViewThreeNum = firstViewThreeNum;
    }

    public  void setFirstViewThreeNumValue(int imageID) {
        firstViewThreeNum.setImageResource(imageID);
    }

    //FirstViewThreeAc
    public void setShowFirstViewThreeAc(ImageView showFirstViewThreeAc) {
        this.showFirstViewThreeAc = showFirstViewThreeAc;
    }

    public  void setFirstViewThreeAcValue(int imageID) {
        showFirstViewThreeAc.setImageResource(imageID);
    }

    public void setShowFirstViewThreeAcVisibility(boolean isShow) {
        if (showFirstViewThreeAc != null)
            showFirstViewThreeAc.setVisibility(isShow?View.VISIBLE:View.GONE);
    }
}
