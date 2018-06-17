package com.baseball;

import com.example.yuru.baseballscorekeeper.R;

import java.io.Serializable;
import java.util.List;

public class RecordItem implements Serializable {
    // metric infomation
    int row;
    int column;
    private int round;
    private boolean isNew;

    // user infomation
    private Player attPlayer;
    private List<Player> defPlayer;
    private boolean score=false;

    public void updateUI(RecordItemCenter center,RecordItemFirstBase base,RecordItemOtherBase base1,RecordItemOtherBase base2,RecordItemOtherBase base3){
        this.updateFirstBaseUI(base);
        this.updateOtherBaseUI(base1,1);
        this.updateOtherBaseUI(base2,2);
        this.updateOtherBaseUI(base3,3);
    }

    BASE_FIRST_STEP_ONE _BASE_FIRST_STEP_ONE;

    public void set_BASE_FIRST_STEP_ONE(BASE_FIRST_STEP_ONE _BASE_FIRST_STEP_ONE) {
        this._BASE_FIRST_STEP_ONE = _BASE_FIRST_STEP_ONE;
    }

    public enum BASE_FIRST_STEP_ONE{
        HIGH,
        TOUCH,
        NORMAL,
        BADBALL,
        HITBYPITCH,
        KILLED,
        NOKILLED
    }


    public void updateFirstBaseUI(RecordItemFirstBase base){
        // TODO update base ui with database
        base.setShowSacrificeFlyVisibility(false);
        base.setShowOneViewVisibility(false);
        base.setShowHRViewVisibility(false);
        base.setShowSacrificeHitsVisibility(false);
        base.setShowThreeViewVisibility(false);
        base.setShowTwoViewVisibility(false);
        base.setShowZeroViewVisibility(false);
        base.setShowFirstViewThreeAcVisibility(false);
        base.setShowFirstViewTwoAcVisibility(false);
        base.setShowFirstViewOneAc1Visibility(false);
        base.setShowFirstViewOneAc2Visibility(false);
        switch (_BASE_FIRST_STEP_ONE){
            case HIGH:
                base.setShowSacrificeFlyVisibility(true);
                break;
            case BADBALL:
            case HITBYPITCH:
            case KILLED:
            case NOKILLED:
                base.setShowZeroViewVisibility(true);
                break;
        }
        switch (_BASE_FIRST_STEP_ONE){
            case BADBALL:
                base.setShowZeroValue(R.drawable.bad_ball);
                break;
            case HITBYPITCH:
                base.setShowZero                base.setShowZeroValue(R.drawable.bad_ball);
                base.setShowZeroValue(R.drawable.bad_ball);
                ViewVisibility(true);
                break;
            case KILLED:
                base.setShowZeroViewVisibility(true);
                break;
            case NOKILLED:
                base.setShowZeroViewVisibility(true);
                break;
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
        isNew = true;
    }

    public Player getAttPlayer() {
        return attPlayer;
    }

    public boolean changeAttPlayer(Player player){
        if(player.getId() == attPlayer.getId())
            return false;
        attPlayer = player;
        save();
        return true;
    }

    public int getRound() {
        return round;
    }

    public void toggleScore(){
        score = !score;

    }

    public boolean isGetScore(){
        return score;
    }

    private void save(){
        if(isNew) {
            isNew = false;
            DatabaseService.getInstance().getDatabase().getRecord(DatabaseService.CurrentRecord).getTeam().addRecordItems(this);
        }
        DatabaseService.getInstance().write();
    }
}
