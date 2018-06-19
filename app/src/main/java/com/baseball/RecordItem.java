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

    private com.baseball.RecordItem.BALL_TYPE ballType = RecordItem.BALL_TYPE.__;
    private BALL_DIRECTION ballDirection = BALL_DIRECTION.__;
    private BALL_TYPE DIETYPE = BALL_TYPE.__;

    private int BALL_TOUCH_AC1;
    private int BALL_TOUCH_AC2;
    private int BALL_TOUCH_AC3;

    private int BALL_TOUCH_AC1_EXTRA;
    private int BALL_TOUCH_AC2_EXTRA;
    private int BALL_TOUCH_AC3_EXTRA;

    private boolean FCUET1;
    private boolean FCUET2;
    private BALL_TYPE FCUET1v;
    private BALL_TYPE FCUET2v;

    RecordItem(Player player,int round,int row,int column){
        this.attPlayer = player;
        this.round = round;
        this.row = row;
        this.column = column;
        isNew = true;
    }

    public void setDIETYPE(BALL_TYPE DIETYPE) {
        this.DIETYPE = DIETYPE;
        save();
    }

    public void setBallDirection(BALL_DIRECTION ball_direction) {
        this.ballDirection = ball_direction;
        save();
    }

    public void setBallDirection(int int_ball_direction) {  //透過數字傳方向
        BALL_DIRECTION[] ball_direction = {BALL_DIRECTION.ONE,BALL_DIRECTION.TWO,BALL_DIRECTION.THREE,BALL_DIRECTION.FOUR,BALL_DIRECTION.FIVE,BALL_DIRECTION.SIX,BALL_DIRECTION.SEVEN,BALL_DIRECTION.EIGHT,BALL_DIRECTION.NINE};
        this.ballDirection = ball_direction[int_ball_direction];
        save();
    }

    public void setBallType(com.baseball.RecordItem.BALL_TYPE ballType) {
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

    public void setFCUET(boolean data1, boolean data2, BALL_TYPE data1v, BALL_TYPE data2v) {
        FCUET1 = data1;
        FCUET2 = data2;
        FCUET1v = data1v;
        FCUET2v = data2v;
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

    private int getImageByBallDirection(BALL_TYPE direction) {
        switch (direction){
            case HIGH:
                return R.drawable.fly_ball;
            case FLAT:
                return R.drawable.line_drive;
            case FLOOR:
                return R.drawable.ground_ball;
            case BADBALL:
                return R.drawable.throw_b;
            case HITBYPITCH:
                return R.drawable.hit_by_pitch;
            case KILLED:
                return R.drawable.killed;
            case NOKILLED:
                return R.drawable.no_killed;
            case T:
                return R.drawable.tag;
            case E:
                return R.drawable.error;
            case FC:
                return R.drawable.fielder_choice;
            case U:
                return R.drawable.u;
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
        base.setShowEndViewVisibility(false);
        switch (DIETYPE){
            case HIGHDIE:
                if (ballType != BALL_TYPE.__ && ballDirection!=BALL_DIRECTION.__){
                    base.setShowSacrificeFlyVisibility(true);
                    base.setShowFirstViewOneTopVisibility(true);
                    base.setShowOneViewVisibility(true);
                    base.setShowFirstViewOneTopValue(getImageByBallDirection(ballType));
                    base.setFirstViewOneNumValue(getImageByNumber(ballDirection));
                }
                break;
            case TOUCHDIE:
                base.setShowSacrificeHitsVisibility(true);
                base.setShowOneViewVisibility(true);
                base.setShowTwoViewVisibility(true);
                base.setShowFirstViewOneBottomVisibility(true);
                base.setFirstViewOneNumValue(getImageByNumber(BALL_TOUCH_AC1));
                base.setFirstViewTwoNumValue(getImageByNumber(BALL_TOUCH_AC2));
                break;
            case NORMAL:
                if(ballType != BALL_TYPE.__){
                    base.setShowOneViewVisibility(true);
                    if(ballType == BALL_TYPE.FLOOR) {
                        base.setShowFirstViewOneBottomVisibility(true);
                    }
                    else {
                        base.setShowFirstViewOneTopVisibility(true);
                        base.setShowFirstViewOneTopValue(getImageByBallDirection(ballType));
                    }
                    base.setFirstViewOneNumValue(getImageByNumber(ballDirection));

                    if(FCUET1) {
                        base.setShowFirstViewOneAc1Visibility(true);
                        base.setFirstViewOneAc1Value(getImageByBallDirection(FCUET1v));
                        if(FCUET2) {
                            base.setShowFirstViewOneAc2Visibility(true);
                            base.setFirstViewOneAc2Value(getImageByBallDirection(FCUET2v));
                        }
                    }
                }


                break;
            case BADBALL:
            case HITBYPITCH:
            case KILLED:
            case NOKILLED:
                base.setShowZeroViewVisibility(true);
                switch (DIETYPE){
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
        else
            DatabaseService.getInstance().write();
    }

    public enum BALL_TYPE {
        HIGH,// 高飛犧牲 // 高飛球
        FLAT, // 平飛球
        FLOOR, // 滾地球
        HIGHDIE,// 高飛犧牲 // 高飛球
        TOUCHDIE,//觸及犧牲
        NORMAL,//一般
        BADBALL, //保送
        HITBYPITCH, //觸身球
        KILLED, //三鎮
        NOKILLED, //不死三陣
        FC,
        U,
        E,
        T,
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
