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
            case 1:
                text="二";
            case 2:
                text="三";
            case 3:
                text="四";
            case 4:
                text="五";
            case 5:
                text="六";
            case 6:
                text="七";
            case 7:
                text="八";
            case 8:
                text="九";
            case 9:
                text="十";
            case 10:
                text="十一";
            case 11:
                text="十二";
            case 12:
                text="十三";
            case 13:
                text="十四";
            case 14:
                text="十五";
        }
        return text;
    }
    public int getBroadNum_num() {
        return broadNum+1;
    }

}
