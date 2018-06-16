package com.baseball;

import java.io.Serializable;
import java.util.List;

public class RecordItem implements Serializable {
    private Player attPlayer;
    private List<Player> defPlayer;
    private int round;
    private boolean score=false;
    private boolean isShow_BASE_B_D_K_KR = false;
    private BASE_B_D_K_KR base_step; // TODO change this variable name YURU ZENGLA, i am no idea...

    public void updateUI(RecordItemCenter center,RecordItemFirstBase base,RecordItemOtherBase base1,RecordItemOtherBase base2,RecordItemOtherBase base3){
this .updateFirstBaseUI(base);
    }

    public void setBASE_B_D_K_KR(boolean state,BASE_B_D_K_KR value){
        this.isShow_BASE_B_D_K_KR = state;
        base_step = value;
    }

    public enum BASE_B_D_K_KR {
        B,D,K,KR,_
    }

    public void updateFirstBaseUI(RecordItemFirstBase base){
        // TODO update base ui with database
        if(isShow_BASE_B_D_K_KR) {
            base.setShowHRViewVisibility(false);
            base.setShowZeroViewVisibility(false);
            base.setShowOneViewVisibility(false);
            base.setShowTwoViewVisibility(false);
            base.setShowThreeViewVisibility(false);
            switch (base_step) {
                case B:
                    base.setShowThreeViewVisibility(true);
                    break;
                case D:
                    break;
                case K:
                    break;
                case KR:
                    break;
            }
        }
    }

    public void updateOtherBaseUI(RecordItemOtherBase base,int base_int){
        if (base_int == 0){
            // TODO update base1 ui with database
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
        attPlayer = player;
        DatabaseService.getInstance().write();

        return true;
    }

    public int getRound() {
        return round;
    }

    public void toggleScore(){
        score = !score;
        DatabaseService.getInstance().write();

    }

    public boolean isGetScore(){
        return score;
    }
}
