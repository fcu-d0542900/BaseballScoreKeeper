package com.example.yuru.baseballscorekeeper.Modal;

import android.annotation.SuppressLint;

import java.io.Serializable;

/**
 * 球員資料
 * Created by YURU on 2018/6/8.
 */

public class Player implements Serializable {
    private long id;  //背號
    private String name = "    ";  //姓名
    private POSITION position;  //守備位置

    public Player() {
        id = -1;
        name = "   ";
        position = POSITION.__;
    }

    public Player(long id, String name, POSITION position) {
        setId(id);
        setName(name);
        setPosition(position);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        DatabaseService.getInstance().write();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        DatabaseService.getInstance().write();

    }

    public POSITION getPosition() {
        return position;
    }

    public void setPosition(POSITION position) {
        this.position = position;
        DatabaseService.getInstance().write();

    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return String.format("%d, %s, %d", getId(), getName(), getPosition());
    }

    public enum POSITION {
        DH, P, C, _1B, _2B, _3B, SS, LF, CF, RF, __
    }


}
