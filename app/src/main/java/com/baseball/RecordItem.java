package com.baseball;

import android.util.Log;

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

    private BALL_TYPE ballType = BALL_TYPE.__;
    private BALL_DIRECTION ballDirection = BALL_DIRECTION.__;
    private BASE_FIRST_STEP_ONE _BASE_FIRST_STEP_ONE = BASE_FIRST_STEP_ONE.__;

    private int BALL_TOUCH_AC1;
    private int BALL_TOUCH_AC2;
    private int BALL_TOUCH_AC3;

    private int BALL_TOUCH_AC1_EXTRA;
    private int BALL_TOUCH_AC2_EXTRA;
    private int BALL_TOUCH_AC3_EXTRA;

    RecordItem(Player player,int round,int row,int column){
        this.attPlayer = player;
        this.round = round;
        this.row = row;
        this.column = column;
        isNew = true;
    }

    public void set_BASE_FIRST_STEP_ONE(BASE_FIRST_STEP_ONE _BASE_FIRST_STEP_ONE) {
        this._BASE_FIRST_STEP_ONE = _BASE_FIRST_STEP_ONE;
        save();
    }

    public void setBallDirection(BALL_DIRECTION ball_direction) {
        this.ballDirection = ball_direction;
        save();
    }

    public void setBallType(BALL_TYPE ballType) {
        this.ballType = ballType;
        save();
    }

    public void setBALL_TOUCH_AC1(int BALL_TOUCH_AC1) {
        this.BALL_TOUCH_AC1 = BALL_TOUCH_AC1;
    }

    public void setBALL_TOUCH_AC2(int BALL_TOUCH_AC2) {
        this.BALL_TOUCH_AC2 = BALL_TOUCH_AC2;
    }

    public void setBALL_TOUCH_AC3(int BALL_TOUCH_AC3) {
        this.BALL_TOUCH_AC3 = BALL_TOUCH_AC3;
    }

    public void setBALL_TOUCH_AC1_EXTRA(int BALL_TOUCH_AC1_EXTRA) {
        this.BALL_TOUCH_AC1_EXTRA = BALL_TOUCH_AC1_EXTRA;
    }

    public void setBALL_TOUCH_AC2_EXTRA(int BALL_TOUCH_AC2_EXTRA) {
        this.BALL_TOUCH_AC2_EXTRA = BALL_TOUCH_AC2_EXTRA;
    }

    public void setBALL_TOUCH_AC3_EXTRA(int BALL_TOUCH_AC3_EXTRA) {
        this.BALL_TOUCH_AC3_EXTRA = BALL_TOUCH_AC3_EXTRA;
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
        save();
    }

    public boolean isGetScore(){
        return score;
    }

    private int getImageByNumber(int num) {
        return getImageByNumber(BALL_DIRECTION.values()[num]);
    }

    private int getImageByNumber(BALL_DIRECTION direction) {
        switch (direction){
            case ONE:
                return R.drawable.throw1;
            case TWO:
                return R.drawable.throw2;
            case THREE:
                return R.drawable.throw3;
            case FOUR:
                return R.drawable.throw4;
            case FIVE:
                return R.drawable.throw5;
            case SIX:
                return R.drawable.throw6;
            case SEVEN:
            case LEFT:
                return R.drawable.throw7;
            case EIGHT:
            case MIDDLE:
                return R.drawable.throw8;
            case NINE:
            case RIGHT:
                return R.drawable.throw9;
            default:
                return R.drawable.throw1;

        }
    }

    public void updateFirstBaseUI(RecordItemFirstBase base){
        base.setShowSacrificeFlyVisibility(false);
        base.setShowSacrificeHitsVisibility(false);
        base.setShowZeroViewVisibility(false);
        base.setShowOneViewVisibility(false);
        base.setShowTwoViewVisibility(false);
        base.setShowThreeViewVisibility(false);
        base.setShowHRViewVisibility(false);
        //one
        base.setShowFirstViewOneTopVisibility(false);
        base.setShowFirstViewOneBottomVisibility(false);
        base.setShowFirstViewOneAc1Visibility(false);
        base.setShowFirstViewOneAc2Visibility(false);
        //two
        base.setShowFirstViewTwoAcVisibility(false);
        //three
        base.setShowFirstViewThreeAcVisibility(false);

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
                    default:
                        base.setShowSacrificeFlyVisibility(false);
                        base.setShowFirstViewOneTopVisibility(false);
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
                    default:
                        base.setShowSacrificeFlyVisibility(false);
                        base.setShowFirstViewOneTopVisibility(false);
                        base.setShowOneViewVisibility(false);

                }
                break;
            case TOUCH:
                base.setShowSacrificeHitsVisibility(true);
                base.setShowOneViewVisibility(true);
                base.setShowFirstViewOneBottomVisibility(true);
                base.setShowTwoViewVisibility(true);
                base.setFirstViewOneNumValue(getImageByNumber(BALL_TOUCH_AC1));
                base.setFirstViewTwoNumValue(getImageByNumber(BALL_TOUCH_AC2));
                Log.d("ahkui touch","get into rouch "+ _BASE_FIRST_STEP_ONE.toString());
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
            base.setShowActionNameViewVisibility(false);
            base.setShowActionViewVisibility(false);
            base.setShowThrowViewVisibility(false);
            base.setShowPushNumViewVisibility(false);
            base.setShowToBaseViewVisibility(false);
            //action
            base.setShowActionOneAcViewVisibility(false);
            base.setShowActionTwoAcViewVisibility(false);

        }
    }

    private void save(){
        if(isNew) {
            isNew = false;
            DatabaseService.getInstance().getDatabase().getRecord(DatabaseService.CurrentRecord).getTeam().addRecordItems(this);
        }
        DatabaseService.getInstance().write();
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
        __,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        LEFT,//左
        MIDDLE,//中
        RIGHT,//右
    }
}
