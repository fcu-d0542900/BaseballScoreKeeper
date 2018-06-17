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

    BASE_FIRST_STEP_ONE _BASE_FIRST_STEP_ONE = BASE_FIRST_STEP_ONE.__;
    BALL_TYPE ballType = BALL_TYPE.__;
    BALL_DIRECTION ballDirection = BALL_DIRECTION.__;
    public void set_BASE_FIRST_STEP_ONE(BASE_FIRST_STEP_ONE _BASE_FIRST_STEP_ONE) {
        this._BASE_FIRST_STEP_ONE = _BASE_FIRST_STEP_ONE;
        save();
    }

    public enum BASE_FIRST_STEP_ONE{
        HIGH,// 高飛犧牲
        TOUCH,//觸及犧牲
        NORMAL,//一般
        BADBALL, //保送
        HITBYPITCH, //觸身球
        KILLED, //三鎮
        NOKILLED, //不死三陣
        __
    }

    public enum BALL_TYPE {
        HIGH, // 高飛球
        FLAT, // 平飛球
        FLOOR, // 滾地球
        __
    }

    public enum BALL_DIRECTION{
        LEFT,//左
        MIDDLE,//中
        RIGHT,//右
        __
        }

    public void setBallDirection(BALL_DIRECTION ball_direction) {
        this.ballDirection = ball_direction;
        save();
    }

    public void setBallType(BALL_TYPE ballType) {
        this.ballType = ballType;
        save();
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
                base.setShowFirstViewOneTopVisibility(true);
                base.setShowOneViewVisibility(true);
                switch (ballType){
                    case HIGH:
                        base.setShowFirstViewOneTopValue(R.drawable.fly_ball);
                        break;
                    case FLAT:
                        base.setShowFirstViewOneTopValue(R.drawable.line_drive);
                        break;
                    case __:
                        base.setShowOneViewVisibility(false);
                }
                switch (ballDirection){
                    case LEFT:
                        base.setFirstViewOneNumValue(R.drawable.throw7);
                        break;
                    case MIDDLE:
                        base.setFirstViewOneNumValue(R.drawable.throw8);
                        break;
                    case RIGHT:
                        base.setFirstViewOneNumValue(R.drawable.throw9);
                        break;
                }
                break;
            case TOUCH:
            case NORMAL:
                break;
            case BADBALL:
            case HITBYPITCH:
            case KILLED:
            case NOKILLED:
                base.setShowZeroViewVisibility(true);
                switch (_BASE_FIRST_STEP_ONE){
                    case BADBALL:
                        base.setShowZeroValue(R.drawable.throw_b);
                        break;
                    case HITBYPITCH:
                        base.setShowZeroValue(R.drawable.hit_by_pitch);
                        break;
                    case KILLED:
                        base.setShowZeroValue(R.drawable.killed);
                        break;
                    case NOKILLED:
                        base.setShowZeroValue(R.drawable.no_killed);
                        break;
                }
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
