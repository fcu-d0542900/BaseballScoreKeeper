package com.baseball;

/**
 * Created by YURU on 2018/6/11.
 */

public class BoardNumInfo {
    private int broadNum;
    private String[] broadNum_symbol = {"一","二","三","四","五","六","七","八","九","十","十一","十二","十三","十四","十五"};

    public BoardNumInfo(int broadNum) {
        this.broadNum=broadNum;
    }

    public void setBroadNum(int broadNum) {
        this.broadNum = broadNum;
    }

    public String getBroadNum_symbol() {
        return broadNum_symbol[broadNum];
    }
    public int getBroadNum_num() {
        return broadNum+1;
    }

}
