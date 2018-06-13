package com.baseball;


import java.io.Serializable;

public class ScoreBoardInfo implements Serializable {
    private int score;

    public ScoreBoardInfo(){
        score=0;
    }

    public ScoreBoardInfo(int score){
        this.score=score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
