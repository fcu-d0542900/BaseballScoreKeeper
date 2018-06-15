package com.baseball;

import java.io.Serializable;
import java.util.List;

public class RecordItem implements Serializable {
    private Player attPlayer;
    private List<Player> defPlayer;
    private int round;
    private boolean score=false;

    RecordItem(Player player,int round){
        this.attPlayer = player;
        this.round = round;        DatabaseService.getInstance().write();

    }

    public Player getAttPlayer() {
        return attPlayer;
    }

    public boolean changeAttPlayer(Player player){
        if(player.getId() == attPlayer.getId())
            return false;
        attPlayer = player;        DatabaseService.getInstance().write();

        return true;
    }

    public int getRound() {
        return round;
    }

    public void toggleScore(){
        score = !score;        DatabaseService.getInstance().write();

    }

    public boolean isGetScore(){
        return score;
    }
}
