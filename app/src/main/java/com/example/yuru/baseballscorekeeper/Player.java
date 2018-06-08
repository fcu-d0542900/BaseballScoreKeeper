package com.example.yuru.baseballscorekeeper;

/**
 * Created by YURU on 2018/6/8.
 */

public class Player {
    private int id;
    private String name;
    private int position;

    public Player(int id, String name, int position) {
        this.id=id;
        this.name=name;
        this.position=position;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int id) {
        this.position = position;
    }

}
