package com.baseball;

import java.io.Serializable;
import java.util.List;

public class RecordItem implements Serializable {
    // metric infomation
    private int round;
    private int row;
    private int column;

    // user infomation
    private Player attPlayer;
    private List<Player> defPlayer;
    private boolean score=false;

    // ui information
    private boolean isShow_BASE_B_D_K_KR = false;
    private BASE_B_D_K_KR base_step; // TODO change this variable name YURU ZENGLA, i am no idea...

    public void updateUI(RecordItemCenter center,RecordItemFirstBase base,RecordItemOtherBase base1,RecordItemOtherBase base2,RecordItemOtherBase base3){
        this.updateFirstBaseUI(base);
        this.updateOtherBaseUI(base1,1);
        this.updateOtherBaseUI(base2,2);
        this.updateOtherBaseUI(base3,3);
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

    RecordItem(Player player,int round,int row,int column){
        this.attPlayer = player;
        this.round = round;
        this.row = row;
        this.column = column;
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
