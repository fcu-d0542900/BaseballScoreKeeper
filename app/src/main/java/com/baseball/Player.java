package com.baseball;

import android.annotation.SuppressLint;

import java.io.Serializable;

/**
 * Created by YURU on 2018/6/8.
 */

public class Player implements Serializable {
    private long id;
    private String name ="    ";
    private POSITION position;
    public enum POSITION {
        DH,P,C,_1B,_2B,_3B,SS,LF,CF,RF,__
    }
    public Player() {
        id=-1;
        name="   ";
        position=POSITION.__;
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


}
