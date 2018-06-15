package com.baseball;

import java.io.Serializable;

/**
 * Created by YURU on 2018/6/11.
 */

public class BoardNumInfo {
    private int broadNum;
    public BoardNumInfo(int broadNum) {
        this.broadNum=broadNum;
    }
    public String getBroadNum_symbol() {
        String text = "";
        switch(broadNum){
            case 0:
                text="一";
                break;
            case 1:
                text="二";
                break;
            case 2:
                text="三";
                break;
            case 3:
                text="四";
                break;
            case 4:
                text="五";
                break;
            case 5:
                text="六";
                break;
            case 6:
                text="七";
                break;
            case 7:
                text="八";
                break;
            case 8:
                text="九";
                break;
            case 9:
                text="十";
                break;
            case 10:
                text="十一";
                break;
            case 11:
                text="十二";
                break;
            case 12:
                text="十三";
                break;
            case 13:
                text="十四";
                break;
            case 14:
                text="十五";
                break;
        }
        return text;
    }
    public int getBroadNum_num() {
        return broadNum+1;
    }

}
