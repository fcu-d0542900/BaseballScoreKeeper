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
    private RecordTeam away;
    private RecordTeam home;
    private RecordTeam.Faction currenFaction;

    public Record(String name, String awayTeam, String homeTeam, Date date) {
        this.name=name;
        this.date=date;
        this.away=new RecordTeam(awayTeam, RecordTeam.Faction.away);
        this.home=new RecordTeam(homeTeam, RecordTeam.Faction.home);
        this.currenFaction = RecordTeam.Faction.away;
    }

    public void setGameName(String name) {
        this.name=name;
        DatabaseService.getInstance().write();
    }

    public String getGameName() {
        return  name;
    }

    public void setGameDate(Date date) {
        this.date = date;
        DatabaseService.getInstance().write();

    }

    public Date getGameDate() {
        return date;
    }

    public RecordTeam getTeam(){
        switch (currenFaction){
            case home:
                return getHomeTeam();
            case away:
                return getAwayTeam();
            default:
                return null;
        }
    }

    public void setCurrenFaction(RecordTeam.Faction currentFaction) {
        this.currenFaction = currentFaction;
    }

    public RecordTeam getAwayTeam() {
        return away;
    }


    public RecordTeam getHomeTeam() {
        return home;
    }
}
