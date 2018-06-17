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
    public int getBroadNum_num() {
        return broadNum+1;
    }

}
