package com.baseball;

import android.util.Log;

import com.example.yuru.baseballscorekeeper.R;
import com.example.yuru.baseballscorekeeper.ScrollablePanelAdapter;

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

    private boolean isEND = false;
    private boolean isChangeHitter = false;
    private boolean isChangeDefPlayer = false;

    private com.baseball.RecordItem.BALL_TYPE ballType = RecordItem.BALL_TYPE.__;
    private BALL_DIRECTION ballDirection = BALL_DIRECTION.__;
    private BALL_TYPE DIETYPE = BALL_TYPE.__;

    private RUNS_OUT RUN_OUT_TYPE = RUNS_OUT.__;
    private HITS_NUM HIT_Num = HITS_NUM.__;

    private int BALL_TOUCH_AC1;
    private int BALL_TOUCH_AC2;

    private boolean FCUET1;
    private boolean FCUET2;
    private BALL_TYPE FCUET1v;
    private BALL_TYPE FCUET2v;


    //other
    private BALL_DIRECTION[] ballPushNum = {BALL_DIRECTION.__,BALL_DIRECTION.__,BALL_DIRECTION.__};
    private BALL_PUSH[] ballPush = {BALL_PUSH.__,BALL_PUSH.__,BALL_PUSH.__};

    private boolean[] isERROR_LEFT = {false,false,false};
    private boolean[] isERRPR_RIGHT = {false,false,false};
    private boolean[] isTo_BASE = {false,false,false};

    private BALL_DIRECTION[] ERROR_LEFT_NUM = {BALL_DIRECTION.__,BALL_DIRECTION.__,BALL_DIRECTION.__};  // 本 二 三
    private BALL_DIRECTION[] ERROR_RIGHT_NUM = {BALL_DIRECTION.__,BALL_DIRECTION.__,BALL_DIRECTION.__};  //數字 e 數字 e 選單用
    private BALL_DIRECTION[] THROW_LEFT = {BALL_DIRECTION.__,BALL_DIRECTION.__,BALL_DIRECTION.__};
    private BALL_DIRECTION[] THROW_RIGHT = {BALL_DIRECTION.__,BALL_DIRECTION.__,BALL_DIRECTION.__};  //趁傳

    private BALL_DIRECTION First_two_direction;
    private boolean First_two_data;
    private BALL_TYPE First_two_dataV;

    private BALL_DIRECTION First_three_direction;
    private boolean First_three_data;
    private BALL_TYPE First_three_dataV;

    RecordItem(Player player,int round,int row,int column){
        this.attPlayer = player;
        this.round = round;
        this.row = row;
        this.column = column;
        isNew = true;
    }

    public void setEND(boolean isEND) {
        this.isEND = isEND;
        save();
    }

    public void setDIETYPE(BALL_TYPE DIETYPE) {
        this.DIETYPE = DIETYPE;
        save();
    }

    public void setRUN_OUT_TYPE(RUNS_OUT type) {
        this.RUN_OUT_TYPE = type;
        save();
    }

    public void setHIT_Num(HITS_NUM num) {
        HIT_Num = num;
        save();
    }

    public void setHIT_Num(int num) {
        setHIT_Num(HITS_NUM.values()[num]);
    }

    public void setBallDirection(BALL_DIRECTION ball_direction) {
        this.ballDirection = ball_direction;
        save();
    }

    public void setBallDirection(int num) {  //透過數字傳方向
        this.ballDirection = BALL_DIRECTION.values()[num];
        save();
        setBallDirection(BALL_DIRECTION.values()[num]);
    }

    public void setBallType(com.baseball.RecordItem.BALL_TYPE ballType) {
        this.ballType = ballType;
        save();
    }

    public void setBallPushNum(BALL_DIRECTION ballPushNum,int base_int) {
        if(base_int>0)
            base_int = base_int-1;
        if(ballPushNum != BALL_DIRECTION.__) {
            isTo_BASE[base_int] = false;
        }
        this.ballPushNum[base_int] = ballPushNum;
        save();
    }

    public void setBallPush(BALL_PUSH ballType,int base_int) {
        if(base_int>0)
            base_int = base_int-1;
        this.ballPush[base_int] = ballType;
        save();
    }

    public void setIsTo_BASE(boolean isToBase,int base_int) {
        if(base_int>0) {
            base_int = base_int-1;
        }
        if(isToBase) {
            ballPushNum[base_int] = BALL_DIRECTION.__;
        }
        this.isTo_BASE[base_int] = isToBase;
        save();
    }

    public void setBALL_TOUCH_AC1(int BALL_TOUCH_AC1) {
        this.BALL_TOUCH_AC1 = BALL_TOUCH_AC1;
    }

    public void setBALL_TOUCH_AC2(int BALL_TOUCH_AC2) {
        this.BALL_TOUCH_AC2 = BALL_TOUCH_AC2;
    }

    public void setFCUET(boolean data1, boolean data2, BALL_TYPE data1v, BALL_TYPE data2v) {
        FCUET1 = data1;
        FCUET2 = data2;
        FCUET1v = data1v;
        FCUET2v = data2v;
    }


    public void setFirst_Two(int two_direction, boolean data, BALL_TYPE dataV) {
        First_two_direction = BALL_DIRECTION.values()[two_direction];
        First_two_data = data;
        First_two_dataV = dataV;
    }

    public void setFirst_Three(int three_direction, boolean data, BALL_TYPE dataV) {
        First_three_direction = BALL_DIRECTION.values()[three_direction];
        First_three_data = data;
        First_three_dataV = dataV;
    }

    public void setThrow(BALL_DIRECTION throw_left, BALL_DIRECTION throw_right, int base_int) {
        if(base_int>0)
            base_int = base_int-1;
        this.THROW_LEFT[base_int] = throw_left;
        this.THROW_RIGHT[base_int] = throw_right;
    }

    public void setError(BALL_DIRECTION ERROR_LEFT_NUM, boolean isERROR_LEFT,BALL_DIRECTION ERROR_RIGHT_NUM,boolean isERROR_RIGHT,int base_int) {
        if(base_int>0)
            base_int = base_int-1;
        this.ERROR_LEFT_NUM[base_int] = ERROR_LEFT_NUM;
        this.ERROR_RIGHT_NUM[base_int] = ERROR_RIGHT_NUM;
        this.isERROR_LEFT[base_int] = isERROR_LEFT;
        this.isERRPR_RIGHT[base_int] = isERROR_RIGHT;
    }


    public Player getAttPlayer() {
        return attPlayer;
    }

    public boolean changeAttPlayer(Player player){
        if(player.getId() == attPlayer.getId())
            return false;
        attPlayer = player;
        isChangeHitter = true;
        save();
        return true;
    }

   public boolean changeDefPlayer(){ //boolean換掉
       //TODO　儲存更換之球員
        isChangeDefPlayer = true;
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

    private int getImageByPushNumber(int num) {
        return getImageByPushNumber(BALL_DIRECTION.values()[num]);
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

    private int getImageByPushNumber(BALL_DIRECTION direction) {  //有()的數

        switch (direction){
            case ONE:
                return R.drawable.push1;
            case TWO:
                return R.drawable.push2;
            case THREE:
                return R.drawable.push3;
            case FOUR:
                return R.drawable.push4;
            case FIVE:
                return R.drawable.push5;
            case SIX:
                return R.drawable.push6;
            case SEVEN:
                return R.drawable.push7;
            case EIGHT:
                return R.drawable.push8;
            case NINE:
                return R.drawable.push9;
            default:
                return R.drawable.push1;
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
//        base.setShowHRViewVisibility(false);
//        base.setShowEndViewVisibility(false);
        //one
        base.setShowFirstViewOneTopVisibility(false);
        base.setShowFirstViewOneBottomVisibility(false);
        base.setShowFirstViewOneAc1Visibility(false);
        base.setShowFirstViewOneAc2Visibility(false);
        //two
        base.setShowTwoViewVisibility(false);
        base.setShowFirstViewTwoAcVisibility(false);
        //three
        base.setShowThreeViewVisibility(false);
        base.setShowFirstViewThreeAcVisibility(false);

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

                    if(First_two_direction != null && First_two_direction != BALL_DIRECTION.__) {
                        base.setShowTwoViewVisibility(true);
                        base.setFirstViewTwoNumValue(getImageByNumber(First_two_direction));
                        if(First_two_data) {
                            base.setShowFirstViewTwoAcVisibility(true);
                            base.setFirstViewTwoAcValue(getImageByBallDirection(First_two_dataV));
                        }
                    }

                    if(First_three_direction != null && First_three_direction != BALL_DIRECTION.__) {
                        base.setShowThreeViewVisibility(true);
                        base.setFirstViewThreeNumValue(getImageByNumber(First_three_direction));
                        if(First_three_data) {
                            base.setShowFirstViewThreeAcVisibility(true);
                            base.setFirstViewThreeAcValue(getImageByBallDirection(First_three_dataV));
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

    //二三本壘UI設定
    public void updateOtherBaseUI(RecordItemOtherBase base,int base_int){
        if(base_int>0)
            base_int = base_int-1;

        base.setShowActionNameViewVisibility(false);
        base.setShowActionViewVisibility(false);
        base.setShowThrowViewVisibility(false);
        base.setShowPushNumViewVisibility(false);
        base.setShowToBaseViewVisibility(false);
        //action
        base.setShowActionOneAcViewVisibility(false);
        base.setShowActionTwoAcViewVisibility(false);

        if(ballPushNum[base_int] != BALL_DIRECTION.__) {
            base.setShowPushNumViewVisibility(true);
            base.setShowPushNumValue(getImageByPushNumber(ballPushNum[base_int]));
        }
        if(isTo_BASE[base_int] == true) {
            base.setShowToBaseViewVisibility(true);
        }

        if(ballPush[base_int] == BALL_PUSH.E) {
            base.setShowActionViewVisibility(true);
            base.setActionOneNumValue(getImageByNumber(ERROR_LEFT_NUM[base_int]));
            base.setActionTwoNumValue(getImageByNumber(ERROR_RIGHT_NUM[base_int]));
            if (isERROR_LEFT[base_int]) {
                base.setShowActionOneAcViewVisibility(true);
            }
            if (isERRPR_RIGHT[base_int]) {
                base.setShowActionTwoAcViewVisibility(true);
            }
        }
        else if(ballPush[base_int] == BALL_PUSH.THROW) {
            base.setShowThrowViewVisibility(true);
            base.setThrowOneValue(getImageByNumber(THROW_LEFT[base_int]));
            base.setThrowTwoValue(getImageByNumber(THROW_RIGHT[base_int]));
        }
        else if(ballPush[base_int] != BALL_PUSH.__) {
            base.setShowActionNameViewVisibility(true);
            switch (ballPush[base_int]) {
                case DP:
                    base.setShowActionNameValue(R.drawable.double_plays);
                    break;
                case TP:
                    base.setShowActionNameValue(R.drawable.tripple_play);
                    break;
                case CS:
                    base.setShowActionNameValue(R.drawable.caught_stolen);
                    break;
                case S:
                    base.setShowActionNameValue(R.drawable.stolen_base);
                    break;
                case PO:
                    base.setShowActionNameValue(R.drawable.put_outs);
                    break;
                case W:
                    base.setShowActionNameValue(R.drawable.wild_pitch);
                    break;
                case P:
                    base.setShowActionNameValue(R.drawable.passed_ball);
                    break;
                case BK:
                    base.setShowActionNameValue(R.drawable.balks);
                    break;
                default:
                    base.setShowActionNameViewVisibility(false);
                    break;
            }
        }
    }
/*
    public void otherbase(RecordItemOtherBase base)
    {

                case THROW:
                    base.setShowThrowViewVisibility(true);

                    break;

                default:
                    break;

            }
        }
        else{
            Log.d("base物件為空","yes");
        }
    }
*/

    public void  updateCenter(RecordItemCenter center) {
        center.setShowCenterVisibility(false);
        center.setShowChangeHitterVisibility(false);
        center.setShowChangeGarrisonVisibility(false);
        center.setShowEndViewVisibility(false);
        center.setShowHRViewVisibility(false);
        center.setShowHit1ViewVisibility(false);
        center.setShowHit2ViewVisibility(false);
        center.setShowHit3ViewVisibility(false);
        center.setShowHit4ViewVisibility(false);

        if(RUN_OUT_TYPE != RUNS_OUT.__) {  //出局得分
            center.setShowCenterVisibility(true);
            switch (RUN_OUT_TYPE) {
                case RUN:
                    center.setShowCenterValue(R.drawable.runs);
                    break;
                case ONE_OUT:
                    center.setShowCenterValue(R.drawable.out1);
                    break;
                case TWO_OUT:
                    center.setShowCenterValue(R.drawable.out2);
                    break;
                case THREE_OUT:
                    center.setShowCenterValue(R.drawable.out3);
                    break;
                case LEFT_BASE:
                    center.setShowCenterValue(R.drawable.left_on_base);
                    break;
                default:
                    center.setShowCenterVisibility(false);
                    break;
            }
        }

        if(HIT_Num != HITS_NUM.__) {  //安打紅線
            switch (HIT_Num) {
                case ONE:
                    center.setShowHit1ViewVisibility(true);
                    break;
                case TWO:
                    center.setShowHit1ViewVisibility(true);
                    center.setShowHit2ViewVisibility(true);
                    break;
                case THREE:
                    center.setShowHit1ViewVisibility(true);
                    center.setShowHit2ViewVisibility(true);
                    center.setShowHit3ViewVisibility(true);
                    break;
                case FOUR:
                    center.setShowHit1ViewVisibility(true);
                    center.setShowHit2ViewVisibility(true);
                    center.setShowHit3ViewVisibility(true);
                    center.setShowHit4ViewVisibility(true);
                    center.setShowHRViewVisibility(true);
                    break;
                default:
                    break;
            }
        }

        if(isEND) {  //結束斜線
            center.setShowEndViewVisibility(true);
        }
        if(isChangeHitter) {
            center.setShowChangeHitterVisibility(true);
        }
        if(isChangeDefPlayer) {
            center.setShowChangeGarrisonVisibility(true);
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

    public enum BALL_PUSH {    //二三本壘推進
        DP,  //雙殺
        TP,  //三殺
        CS,  //盜壘失敗
        S,  //盜壘
        PO,  //投手牽制
        W,  //暴投
        P, //補逸
        BK,  //投手犯規
        E, //失誤
        THROW,  //趁傳
        __,
    }

    public enum RUNS_OUT{
        __,
        RUN,
        ONE_OUT,
        TWO_OUT,
        THREE_OUT,
        LEFT_BASE,
    }

    public enum HITS_NUM {
        __,
        ONE,
        TWO,
        THREE,
        FOUR,

    }
}
