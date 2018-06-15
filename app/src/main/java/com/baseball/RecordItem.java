package com.baseball;

import java.io.Serializable;
import java.util.List;

public class RecordItem implements Serializable {
    private Player attPlayer;
    private List<Player> defPlayer;
    private int round;
    private boolean score=false;

    private RecordItemCenter global;
    private RecordItemFirstBase base;
    private RecordItemOtherBase base1,base2,base3;

    public void updateUI(RecordItemCenter global){

    }
    public void updatebaseUI(Object base,int base_int){
        if (base_int == 0){
            RecordItemFirstBase base0 = (RecordItemFirstBase) base;
            base0.setShowOneViewVisibility(false);
        }
    }
    RecordItem(Player player,int round){
        this.attPlayer = player;
        this.round = round;
        DatabaseService.getInstance().write();

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
