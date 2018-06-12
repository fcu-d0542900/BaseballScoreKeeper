package com.example.yuru.baseballscorekeeper;

/**
 * Created by YURU on 2018/6/8.
 */

public class Player {
    private long id;
    private String name;
    private int position_num;
    private String[] position_symbol={"DH","P","C","1B","2B","3B","SS","LF","CF","RF",""};  //設DH為0，其他照符號

    public Player() {
        id=-1;
        name="";
        position_num=10;
    }
    public Player(long id, String name, int position_num) {
        setId(id);
        setName(name);
        setPosition(position_num);
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPosition() {
        return position_symbol[position_num];
    }
    public void setPosition(int position_num) {
        if(position_num>10) {
            position_num=10;
        }
        this.position_num = position_num;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %d", getId(), getName(), getPosition());
    }
}
