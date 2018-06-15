package com.baseball;

import java.io.Serializable;
import java.util.List;

public class RecordItem implements Serializable {
    private Player attPlayer;
    private List<Player> defPlayer;
    private int round;

    RecordItem(Player player,int round){
        this.attPlayer = player;
        this.round = round;
    }

    public Player getAttPlayer() {
        return attPlayer;
    }

    public boolean changeAttPlayer(Player player){
        if(player.getId() == attPlayer.getId())
            return false;
        attPlayer = player;
        return true;
    }

    public int getRound() {
        return round;
    }
}
