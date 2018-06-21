package com.example.yuru.baseballscorekeeper.Modal;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by YURU on 2018/6/16.
 */

public class RecordItemOtherBase {
    private ImageView showActionNameView,showPushNumView,showToBaseView;
    private LinearLayout showActionView,showThrowView;
    private ImageView showActionOneAcView,showActionTwoAcView;
    private ImageView actionOneNum,actionTwoNum;
    public ImageView throwOne,throwTwo;
    private RecordItemOtherBase recordItemUI;


    public void setBase1UI(RecordItemOtherBase viewHolder ){
        recordItemUI=viewHolder;
    }

    public RecordItemOtherBase getBaseUI(){
        return recordItemUI;
    }

    public void setShowActionNameView(ImageView showActionNameView) {
        this.showActionNameView = showActionNameView;
    }

    //ShowActionName TP、CS等等顯示
    public  void setShowActionNameValue(int imageID) {
        showActionNameView.setImageResource(imageID);
    }

    public void setShowActionNameViewVisibility(boolean isShow) {
        if (showActionNameView != null)
            showActionNameView.setVisibility(isShow? View.VISIBLE:View.GONE);
    }

    //ShowAction  失誤view顯示
    public void setShowActionView(LinearLayout showActionView) {
        this.showActionView = showActionView;
    }

    public void setShowActionViewVisibility(boolean isShow) {
        if (showActionView != null)
            showActionView.setVisibility(isShow? View.VISIBLE:View.GONE);
    }

    //ShowThrow
    public void setShowThrowView(LinearLayout showThrowView) {
        this.showThrowView = showThrowView;
    }

    public void setShowThrowViewVisibility(boolean isShow) {
        if (showThrowView != null)
            showThrowView.setVisibility(isShow? View.VISIBLE:View.GONE);
    }

    //ShowPushNum
    public void setShowPushNumView(ImageView showPushNumView) {
        this.showPushNumView = showPushNumView;
    }

    public  void setShowPushNumValue(int imageID) {
        showPushNumView.setImageResource(imageID);
    }

    public void setShowPushNumViewVisibility(boolean isShow) {
        if (showPushNumView != null)
            showPushNumView.setVisibility(isShow? View.VISIBLE:View.INVISIBLE);
    }

    //ShowToBase
    public void setShowToBaseView(ImageView showToBaseView) {
        this.showToBaseView = showToBaseView;
    }

    public void setShowToBaseViewVisibility(boolean isShow) {
        if (showToBaseView != null)
            showToBaseView.setVisibility(isShow? View.VISIBLE:View.INVISIBLE);
    }

    //ShowActionOneAc  左失誤
    public void setShowActionOneAcView(ImageView showActionOneAcView) {
        this.showActionOneAcView = showActionOneAcView;
    }

    public void setShowActionOneAcViewVisibility(boolean isShow) {
        if (showActionOneAcView != null)
            showActionOneAcView.setVisibility(isShow? View.VISIBLE:View.GONE);
    }

    //ShowActionTwoAc   右失誤
    public void setShowActionTwoAcView(ImageView showActionTwoAcView) {
        this.showActionTwoAcView = showActionTwoAcView;
    }

    public void setShowActionTwoAcViewVisibility(boolean isShow) {
        if (showActionTwoAcView != null)
            showActionTwoAcView.setVisibility(isShow? View.VISIBLE:View.GONE);
    }

    //ActionOneNum  左數字
    public void setActionOneNum(ImageView actionOneNum) {
        this.actionOneNum = actionOneNum;
    }

    public  void setActionOneNumValue(int imageID) {
        actionOneNum.setImageResource(imageID);
    }

    //ActionTwoNum  右數字
    public void setActionTwoNum(ImageView actionTwoNum) {
        this.actionTwoNum = actionTwoNum;
    }

    public  void setActionTwoNumValue(int imageID) {
        actionTwoNum.setImageResource(imageID);
    }

    //ThrowOne
    public void setThrowOne(ImageView throwOne) {
        this.throwOne = throwOne;
    }

    public  void setThrowOneValue(int imageID) {
        throwOne.setImageResource(imageID);
    }

    //ThrowTwo
    public void setThrowTwo(ImageView throwTwo) {
        this.throwTwo = throwTwo;
    }

    public  void setThrowTwoValue(int imageID) {
        throwTwo.setImageResource(imageID);
    }
}
