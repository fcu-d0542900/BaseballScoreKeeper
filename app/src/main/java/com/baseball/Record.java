package com.baseball;

/**
 * Created by YURU on 2018/6/13.
 */

public class Record {
    private String gameName;
    private String gameDate;
    private String awayTeam;
    private String homeTeam;

    public Record() {

    }
    public Record(String gameName,String gameDate,String awayTeam,String homeTeam) {
        this.gameName=gameName;
        this.gameDate=gameDate;
        this.awayTeam=awayTeam;
        this.homeTeam=homeTeam;
    }
    public void setGameName(String gameName) {
        this.gameName=gameName;
    }

    public String getGameName() {
        return  gameName;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }
}
