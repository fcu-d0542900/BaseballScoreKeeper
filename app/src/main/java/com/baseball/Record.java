package com.baseball;

import android.util.Log;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by YURU on 2018/6/13.
 */

public class Record implements Serializable {
    private String name;
    private Date date;
    private Team away;
    private Team home;

    public Record() {

    }

    public Record(String name,String awayTeam,String homeTeam,Date date) {
        this.name=name;
        this.date=date;
        this.away=new Team(awayTeam);
        this.home=new Team(homeTeam);
        Log.d("record","add record: "+name);
    }

    public void setGameName(String name) {
        this.name=name;
    }

    public String getGameName() {
        return  name;
    }

    public void setGameDate(Date date) {
        this.date = date;
    }

    public Date getGameDate() {
        return date;
    }


    public Team getAwayTeam() {
        return away;
    }


    public Team getHomeTeam() {
        return home;
    }
}
