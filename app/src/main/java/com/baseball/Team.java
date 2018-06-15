package com.baseball;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YURU on 2018/6/8.
 */

public class Team implements Serializable {
    private String teamName="";
    private List<Player> teamMember = new ArrayList<>();
    private int currentRound = 0;
    private List<RecordItem> recordItems;
    private List<BoardNumInfo> recordRoundItem;
    public Team(){}

    public Team(String homeTeam,TEAM team) {
        setTeamName(homeTeam);
        switch (team){
            case away:
                currentRound =1;
            case home:
                currentRound=0;
        }
        if (homeTeam.equals(DatabaseService.getInstance().getDatabase().getTeamName())){
            teamMember = new ArrayList<>(DatabaseService.getInstance().getDatabase().getTeamMember());
        }
    }

    public RecordItem getRecordItems(int row,int round) {
        RecordItem newItem = new RecordItem(teamMember.get(row),round);
        if(round > this.currentRound){
            return newItem;
        }
        int index = 0;
        if(recordItems!=null)
            for (RecordItem item :
                    recordItems) {
                if(row == index%9 && round==item.getRound())
                    return item;
                else if(round > this.currentRound){
                    return newItem;
                }
                index ++;
            }
        return newItem;
    }
    public int getCurrentRound() {
        return currentRound;
    }

    public int nextRound() {
        currentRound = currentRound+1;
        return currentRound;
    }

    public String getRoundText(int round) {
        String text = "";
        switch(round){
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

    public void setTeamName(String name) {
        this.teamName = name;
    }

    public String getTeamName() {return teamName;}

    public List<Player> addTeamMember(String name){
        Player player = new Player();
        player.setName(name);
        this.teamMember.add(player);
        DatabaseService.getInstance().write();
        return getTeamMember();
    }

    public List<Player> getTeamMember() {
        return this.teamMember;
    }

    public String getScore(int i) {
        int score = 0;
        if(recordItems!=null)
            for (RecordItem item:
                    recordItems) {
                if (item.getRound() == i && item.isGetScore()) score++;
            }
        return ""+score;
    }

    public enum TEAM{
        away,
        home
    }
}
