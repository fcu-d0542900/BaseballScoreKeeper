package com.baseball;

import java.util.List;

public class RecordItem {
    private Player attPlayer;
    private List<Player> defPlayer;

    public Player getAttPlayer() {
        return attPlayer;
    }

    public boolean changeAttPlayer(Player player){
        if(player.getId() == attPlayer.getId())
            return false;
        attPlayer = player;
        return true;
    }


}
