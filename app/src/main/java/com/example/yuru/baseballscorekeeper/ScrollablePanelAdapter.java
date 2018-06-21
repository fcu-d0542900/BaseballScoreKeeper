package com.example.yuru.baseballscorekeeper;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baseball.BASE;
import com.baseball.Player;
import com.baseball.Record;
import com.baseball.RecordItem;
import com.baseball.RecordItemCenter;
import com.baseball.RecordItemFirstBase;
import com.baseball.RecordItemOtherBase;
import com.baseball.RecordTeam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YURU on 2018/6/11.
 */

public class ScrollablePanelAdapter extends PanelAdapter {

    private NewRecordActivity activity;

    private EditText editText_playerName,editText_playerNum;
    private Spinner spinner_position;
    private View view_set_player;

    private String playerName;
    private int playerNum,playerPosition;

    private BaseCenterDialog baseCenterDialog;
    private BaseOtherDialog baseOtherDialog;
    private BaseFirstDialog baseFirstDialog;
    private GoodBadBallDialog goodBadBallDialog;

    private List<List<RecordItem>> recordItems;

    private static final int TEAMNAME_TYPE = 4;
    private static final int PLAYERINFO_TYPE = 0;
    private static final int BOARDNUM_TYPE = 1;
    private static final int ORDER_TYPE = 2;

    private RecordItemOtherBase recordItemUI;

    ScrollablePanelAdapter(NewRecordActivity activity) {
        super();
        this.activity = activity;
        recordItems = new ArrayList<>();
        recordItems = activity.getUpdateData();

    }

    public void updateData() {
        recordItems = activity.getUpdateData();
        activity.scrollablePanel.notifyDataSetChanged();
    }

    @Override
    public int getRowCount() {
        return recordItems.size()+1;
    }

    @Override
    public int getColumnCount() {
        return recordItems.get(0).size()+1 ;
    }

    @Override
    // 當物件顯示於畫面時被調用，可利用此方法更新該物件之內容
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {
        int viewType = getItemViewType(row, column);
        switch (viewType) {
            case BOARDNUM_TYPE:
                setBoardNumView(column, (BoardNumViewHolder) holder);
                break;
            case PLAYERINFO_TYPE:
                setPlayerInfoView(row, (PlayerInfoViewHolder) holder);
                break;
            case ORDER_TYPE:
                setOrderView(row, column, (OrderViewHolder) holder);
                break;
            case TEAMNAME_TYPE:
                setTeamNameView((TeamNameViewHolder) holder);
                break;
            default:
                setOrderView(row, column, (OrderViewHolder) holder);
        }
    }

    // 取得item的類型
    public int getItemViewType(int row, int column) {
        if (column == 0 && row == 0) {
            return TEAMNAME_TYPE;
        }
        if (column == 0) {
            return PLAYERINFO_TYPE;
        }
        if (row == 0) {
            return BOARDNUM_TYPE;
        }
        return ORDER_TYPE;
    }

    @Override
    //根據不同類型創建不同item的ViewHolder
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BOARDNUM_TYPE:
                return new BoardNumViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_board_num, parent, false));
            case PLAYERINFO_TYPE:
                return new PlayerInfoViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_player_info, parent, false));
            case ORDER_TYPE:
                return new OrderViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_order_info, parent, false));
            case TEAMNAME_TYPE:
                return new TeamNameViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_team_name, parent, false));
            default:
                break;
        }
        return new OrderViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_order_info, parent, false));

    }

   //設定局數顯示
    private void setBoardNumView(int pos, BoardNumViewHolder viewHolder) {
        viewHolder.dateTextView.setText(activity.currentRecord.getTeam().getRoundText(activity.currentRecord.getTeam().getRecordItemsPositionRound(pos)));
    }

    //設定球員顯示畫面
    @SuppressLint("SetTextI18n")
    private void setPlayerInfoView(int pos, final PlayerInfoViewHolder viewHolder) {
        final Player playerInfo = activity.currentRecord.getTeam().getTeamMember().get(pos - 1);  //取得player物件
        viewHolder.text_batOrder.setText(Integer.valueOf(pos).toString());

        Log.d(">>>>player",playerInfo.getName());

        if (playerInfo != null && pos>0) {

            //設定資料
            viewHolder.text_playerPosition.setText(playerInfo.getPosition().toString().replaceAll("_", ""));
            viewHolder.text_playerName.setText(playerInfo.getName());
            //設定球員背號
            if(playerInfo.getId() == -1) {
                viewHolder.text_playerNum.setText("");
            }
            else {
                viewHolder.text_playerNum.setText(Long.valueOf(playerInfo.getId()).toString());
            }

            //更改球員
            viewHolder.text_playerName.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("InflateParams")
                @Override
                public void onClick(View v) {
                    //透過 LayoutInflater的inflate()方法將xml定義的一個布局找出来
                    view_set_player = LayoutInflater.from(activity).inflate(R.layout.dialog_new_player, null);
                    view_set_player.setPadding(3,0,3,0);
                    ImageView img = view_set_player.findViewById(R.id.image_title_newPlayer);
                    img.setVisibility(View.GONE);
                    editText_playerName = view_set_player.findViewById(R.id.editText_playerName);
                    editText_playerNum = view_set_player.findViewById(R.id.editText_playerNum);
                    spinner_position = view_set_player.findViewById(R.id.spinner_position);

                    AlertDialog.Builder dialog_setPlayer = new AlertDialog.Builder(activity);  //透過Builder創建AlertDialog
                    dialog_setPlayer.setView(view_set_player);  //透過Builder創建AlertDialog

                    // spinner下拉選單設定
                    ArrayAdapter<String> adapter_position = new ArrayAdapter<>(dialog_setPlayer.getContext(),android.R.layout.simple_spinner_item,new String[]{"","P","C","1B","2B","3B","SS","LF","CF","RF"});
                    adapter_position.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_position.setAdapter(adapter_position);

                    dialog_setPlayer.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(!editText_playerNum.getText().toString().equals("")) {
                                playerName = editText_playerName.getText().toString();
                                playerNum = Integer.valueOf(editText_playerNum.getText().toString());
                                playerPosition = (int) spinner_position.getSelectedItemId();

                                //設定使用者輸入的資料到物件裡
                                playerInfo.setName(playerName);
                                playerInfo.setId(playerNum);
                                playerInfo.setPosition(Player.POSITION.values()[playerPosition]);
                                //在畫面顯示使用者輸入資料
                                viewHolder.text_playerName.setText(playerName);
                                viewHolder.text_playerNum.setText(Integer.valueOf(playerNum).toString());
                                viewHolder.text_playerPosition.setText(playerInfo.getPosition().toString().replaceAll("_",""));

                                Toast.makeText(activity.getApplicationContext(), "SET", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(activity.getApplicationContext(), "請填入背號!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    dialog_setPlayer.show();

                }
            });


        }
    }

    //設定紀錄版畫面
    private void setOrderView(final int row, final int column, final OrderViewHolder viewHolder) {
        final RecordItem recordItem = recordItems.get(row -1).get(column-1);
        viewHolder.setRecordItem(recordItem);
        if (recordItem != null) {
            viewHolder.getScoreView.bringToFront();

            //按下中間菱形區域
            viewHolder.getScoreView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //設定baseCenterDialog
                    baseCenterDialog = new BaseCenterDialog();
                    baseCenterDialog.setActivity(activity);  //設定dialog切換的activity
                    baseCenterDialog.setBaseCenterDialog(viewHolder,row);  //設定按下後的dialog並顯示
                    Toast.makeText(v.getContext(), "得分區域" +recordItem.getAttPlayer().getName(), Toast.LENGTH_SHORT).show();
                }
            });

            //按下一壘
            viewHolder.getFirstView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //設定baseFirstDialog並顯示
                    baseFirstDialog = new BaseFirstDialog();
                    baseFirstDialog.setActivity(activity);
                    baseFirstDialog.setBaseFirstDialog(viewHolder);
                    Toast.makeText(v.getContext(), "一壘" , Toast.LENGTH_SHORT).show();

                }
            });

            //按下二壘
            viewHolder.getSecondView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseOtherDialog = new BaseOtherDialog();      //設定baseOtherDialog並顯示
                    baseOtherDialog.setActivity(activity);
                    baseOtherDialog.setBaseUI(viewHolder.base2);
                    baseOtherDialog.setBaseOtherDialog(viewHolder,new String[]{"推進(數字)","進壘(箭頭)"}, 2);
                    Toast.makeText(v.getContext(), "二壘" , Toast.LENGTH_SHORT).show();

                }
            });

            //按下三壘
            viewHolder.getThirdView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseOtherDialog = new BaseOtherDialog();  //設定baseOtherDialog並顯示
                    baseOtherDialog.setActivity(activity);
                    baseOtherDialog.setBaseUI(viewHolder.base3);
                    baseOtherDialog.setBaseOtherDialog(viewHolder,new String[]{"推進(數字)","進壘(箭頭)"}, 3);
                    Toast.makeText(v.getContext(), "三壘" , Toast.LENGTH_SHORT).show();
                }
            });

            //按下本壘
            viewHolder.getHomeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseOtherDialog = new BaseOtherDialog();  //設定baseOtherDialog並顯示
                    baseOtherDialog.setActivity(activity);
                    baseOtherDialog.setBaseUI(viewHolder.base);
                    baseOtherDialog.setBaseOtherDialog(viewHolder,new String[]{"推進(數字)","進壘(箭頭)"},0);
                    Toast.makeText(v.getContext(), "本壘" , Toast.LENGTH_SHORT).show();

                }
            });

            //計算好壞球
            viewHolder.getBallView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goodBadBallDialog = new GoodBadBallDialog();   //設定goodBadBallDialog並顯示
                    goodBadBallDialog.setNewRecordActivity(activity);
                    goodBadBallDialog.setGoodBadBallDialog(viewHolder);
                    Toast.makeText(v.getContext(), "好壞球" , Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    //設定隊名顯示畫面
    private void setTeamNameView(TeamNameViewHolder viewHolder) {
        //去資料庫拿資料並設定到view裡
        viewHolder.titleTextView.setText(activity.currentRecord.getTeam().getTeamName());
    }

    //找出各個view的畫面元件
    static class BoardNumViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        BoardNumViewHolder(View itemView) {
            super(itemView);
            this.dateTextView = itemView.findViewById(R.id.text_boardNum);
        }
    }

    //連接xml之id
    static class PlayerInfoViewHolder extends RecyclerView.ViewHolder {
        public TextView text_playerPosition;
        public TextView text_playerName;
        public TextView text_playerNum;
        public TextView text_batOrder;
        PlayerInfoViewHolder(View view) {
            super(view);
            this.text_playerPosition = view.findViewById(R.id.text_playerPosition);
            this.text_playerName = view.findViewById(R.id.text_playerName);
            this.text_playerNum = view.findViewById(R.id.text_playerNum);
            this.text_batOrder = view.findViewById(R.id.text_batOrder);
        }
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        RecordItem recordItem;

        TextView getScoreView;
        TextView getFirstView;
        TextView getHomeView;
        TextView getSecondView;
        TextView getThirdView;
        TextView getBallView;

        //更改球員
        ImageView getChangeGarrison,getChangeHitter;

        //center
        ImageView getCenterView;
        //安打
        ImageView getHit1View,getHit2View,getHit3View,getHit4View;
        public RecordItemCenter center;

        //一壘
        ImageView getFirstViewZero,getFirstViewHR;
        FrameLayout getFirstViewOne,getFirstViewTwo,getFirstViewThree;
        ImageView getFirstViewOneTop,getFirstViewOneNum,getFirstViewOneBottom;
        ImageView getFirstViewOneAc1,getFirstViewOneAc2;
        ImageView getFirstViewTwoNum,getFirstViewTwoAc;
        ImageView getFirstViewThreeNum,getFirstViewThreeAc;
        ImageView getSacrificeFly,getSacrificeHits;
        //
        ImageView getEnd;
        RecordItemFirstBase base1;

        //二壘
        ImageView getSecondViewActionName,getSecondViewPushNum,getSecondViewBase;
        LinearLayout getSecondViewAction;
        ImageView getSecondViewActionOneNum,getSecondViewActionOneAc;
        ImageView getSecondViewActionTwoNum,getSecondViewActionTwoAc;
        LinearLayout getSecondViewThrow;
        ImageView getSecondViewThrowOne,getSecondViewThrowTwo;
        public RecordItemOtherBase base2;

        //三壘
        ImageView getThirdViewActionName,getThirdViewPushNum,getThirdViewBase;
        LinearLayout getThirdViewAction;
        ImageView getThirdViewActionOneNum,getThirdViewActionOneAc;
        ImageView getThirdViewActionTwoNum,getThirdViewActionTwoAc;
        LinearLayout getThirdViewThrow;
        ImageView getThirdViewThrowOne,getThirdViewThrowTwo;
        RecordItemOtherBase base3;

        //本壘
        ImageView getHomeViewActionName,getHomeViewPushNum,getHomeViewBase;
        LinearLayout getHomeViewAction;
        ImageView getHomeViewActionOneNum,getHomeViewActionOneAc;
        ImageView getHomeViewActionTwoNum,getHomeViewActionTwoAc;
        LinearLayout getHomeViewThrow;
        ImageView getHomeViewThrowOne,getHomeViewThrowTwo;
        public RecordItemOtherBase base;


        OrderViewHolder(View view) {
            super(view);

            base1  = new RecordItemFirstBase();
            base2  = new RecordItemOtherBase();
            base3  = new RecordItemOtherBase();
            base  = new RecordItemOtherBase();
            center = new RecordItemCenter();

            this.getScoreView = view.findViewById(R.id.centerView);
            this.getFirstView = view.findViewById(R.id.firstView);
            this.getHomeView = view.findViewById(R.id.homeView);
            this.getSecondView = view.findViewById(R.id.secondView);
            this.getThirdView = view.findViewById(R.id.thirdView);
            this.getBallView= view.findViewById(R.id.ballView);

            this.getChangeGarrison = view.findViewById(R.id.image_change_garrison);
            this.getChangeHitter = view.findViewById(R.id.image_change_hitter);
            this.getEnd = view.findViewById(R.id.image_end);
            this.getFirstViewHR = view.findViewById(R.id.image_HR);

            //center
            this.getCenterView = view.findViewById(R.id.image_centerView);
            this.getHit1View = view.findViewById(R.id.image_hit1);
            this.getHit2View = view.findViewById(R.id.image_hit2);
            this.getHit3View = view.findViewById(R.id.image_hit3);
            this.getHit4View = view.findViewById(R.id.image_hit4);

            center.setShowCenterView(getCenterView);
            center.setShowHit1View(getHit1View);
            center.setShowHit2View(getHit2View);
            center.setShowHit3View(getHit3View);
            center.setShowHit4View(getHit4View);

            center.setShowChangeGarrison(getChangeGarrison);
            center.setShowChangeHitter(getChangeHitter);
            center.setShowEndView(getEnd);
            center.setShowHRView(getFirstViewHR);


            //一壘
            this.getFirstViewZero = view.findViewById(R.id.image_firstView_zero);
            this.getFirstViewOne = view.findViewById(R.id.frame_firstView_one);
            this.getFirstViewTwo = view.findViewById(R.id.frame_firstView_two);
            this.getFirstViewThree = view.findViewById(R.id.frame_firstView_three);
            this.getFirstViewOneTop = view.findViewById(R.id.image_firstView_oneTop);
            this.getFirstViewOneNum = view.findViewById(R.id.image_firstView_oneNum);
            this.getFirstViewOneBottom = view.findViewById(R.id.image_firstView_oneBottom);
            this.getFirstViewOneAc1 = view.findViewById(R.id.image_firstView_one_ac1);
            this.getFirstViewOneAc2 = view.findViewById(R.id.image_firstView_one_ac2);
            this.getFirstViewTwoNum = view.findViewById(R.id.image_firstView_twoNum);
            this.getFirstViewTwoAc = view.findViewById(R.id.image_firstView_two_ac);
            this.getFirstViewThreeNum = view.findViewById(R.id.image_firstView_threeNum);
            this.getFirstViewThreeAc = view.findViewById(R.id.image_firstView_three_ac);
            this.getSacrificeFly = view.findViewById(R.id.image_sacrifice_fly);
            this.getSacrificeHits = view.findViewById(R.id.image_sacrifice_hits);

            base1.setShowZeroView(getFirstViewZero);
            base1.setShowOneView(getFirstViewOne);
            base1.setShowTwoView(getFirstViewTwo);
            base1.setShowThreeView(getFirstViewThree);
            base1.setShowFirstViewOneTop(getFirstViewOneTop);
            base1.setFirstViewOneNum(getFirstViewOneNum);
            base1.setShowFirstViewOneBottom(getFirstViewOneBottom);
            base1.setShowFirstViewOneAc1(getFirstViewOneAc1);
            base1.setShowFirstViewOneAc2(getFirstViewOneAc2);
            base1.setFirstViewTwoNum(getFirstViewTwoNum);
            base1.setShowFirstViewTwoAc(getFirstViewTwoAc);
            base1.setFirstViewThreeNum(getFirstViewThreeNum);
            base1.setShowFirstViewThreeAc(getFirstViewThreeAc);
            base1.setShowSacrificeFly(getSacrificeFly);
            base1.setShowSacrificeHits(getSacrificeHits);





            //二壘
            this.getSecondViewActionName = view.findViewById(R.id.image_secondView_actionName);
            this.getSecondViewAction = view.findViewById(R.id.linear_secondView_acion);
            this.getSecondViewActionOneNum = view.findViewById(R.id.image_secondView_actionOne);
            this.getSecondViewActionOneAc = view.findViewById(R.id.image_secondView_actionOne_ac);
            this.getSecondViewActionTwoNum = view.findViewById(R.id.image_secondView_actionTwo);
            this.getSecondViewActionTwoAc = view.findViewById(R.id.image_secondView_actionTwo_ac);
            this.getSecondViewThrow = view.findViewById(R.id.linear_secondView_throw);
            this.getSecondViewThrowOne = view.findViewById(R.id.image_secondView_throwOne);
            this.getSecondViewThrowTwo = view.findViewById(R.id.image_secondView_throwTwo);
            this.getSecondViewPushNum = view.findViewById(R.id.image_secondView_pushNumber);
            this.getSecondViewBase = view.findViewById(R.id.image_base_1to2);
            base2.setShowActionNameView(getSecondViewActionName);
            base2.setShowActionView(getSecondViewAction);
            base2.setActionOneNum(getSecondViewActionOneNum);
            base2.setShowActionOneAcView(getSecondViewActionOneAc);
            base2.setActionTwoNum(getSecondViewActionTwoNum);
            base2.setShowActionTwoAcView(getSecondViewActionTwoAc);
            base2.setShowThrowView(getSecondViewThrow);
            base2.setThrowOne(getSecondViewThrowOne);
            base2.setThrowTwo(getSecondViewThrowTwo);
            base2.setShowPushNumView(getSecondViewPushNum);
            base2.setShowToBaseView(getSecondViewBase);


            //三壘
            this.getThirdViewActionName = view.findViewById(R.id.image_thirdView_actionName);
            this.getThirdViewAction = view.findViewById(R.id.linear_thirdView_action);
            this.getThirdViewActionOneNum = view.findViewById(R.id.image_thirdView_actionOne);
            this.getThirdViewActionOneAc = view.findViewById(R.id.image_thirdView_actionOne_ac);
            this.getThirdViewActionTwoNum = view.findViewById(R.id.image_thirdView_actionTwo);
            this.getThirdViewActionTwoAc = view.findViewById(R.id.image_thirdView_actionTwo_ac);
            this.getThirdViewThrow = view.findViewById(R.id.linear_thirdView_throw);
            this.getThirdViewThrowOne = view.findViewById(R.id.image_thirdView_throwOne);
            this.getThirdViewThrowTwo = view.findViewById(R.id.image_thirdView_throwTwo);
            this.getThirdViewPushNum = view.findViewById(R.id.image_thirdView_pushNumber);
            this.getThirdViewBase = view.findViewById(R.id.image_base_2to3);
            base3.setShowActionNameView(getThirdViewActionName);
            base3.setShowActionView(getThirdViewAction);
            base3.setActionOneNum(getThirdViewActionOneNum);
            base3.setShowActionOneAcView(getThirdViewActionOneAc);
            base3.setActionTwoNum(getThirdViewActionTwoNum);
            base3.setShowActionTwoAcView(getThirdViewActionTwoAc);
            base3.setShowThrowView(getThirdViewThrow);
            base3.setThrowOne(getThirdViewThrowOne);
            base3.setThrowTwo(getThirdViewThrowTwo);
            base3.setShowPushNumView(getThirdViewPushNum);
            base3.setShowToBaseView(getThirdViewBase);

            //本壘
            this.getHomeViewActionName = view.findViewById(R.id.image_homeView_actionName);
            this.getHomeViewAction = view.findViewById(R.id.linear_homeView_acion);
            this.getHomeViewActionOneNum = view.findViewById(R.id.image_homeView_actionOne);
            this.getHomeViewActionOneAc = view.findViewById(R.id.image_homeView_actionOne_ac);
            this.getHomeViewActionTwoNum = view.findViewById(R.id.image_homeView_actionTwo);
            this.getHomeViewActionTwoAc = view.findViewById(R.id.image_homeView_actionTwo_ac);
            this.getHomeViewThrow = view.findViewById(R.id.linear_homeView_throw);
            this.getHomeViewThrowOne = view.findViewById(R.id.image_homeView_throwOne);
            this.getHomeViewThrowTwo = view.findViewById(R.id.image_homeView_throwTwo);
            this.getHomeViewPushNum = view.findViewById(R.id.image_homeView_pushNumber);
            this.getHomeViewBase = view.findViewById(R.id.image_base_3tohome);
            base.setShowActionNameView(getHomeViewActionName);
            base.setShowActionView(getHomeViewAction);
            base.setActionOneNum(getHomeViewActionOneNum);
            base.setShowActionOneAcView(getHomeViewActionOneAc);
            base.setActionTwoNum(getHomeViewActionTwoNum);
            base.setShowActionTwoAcView(getHomeViewActionTwoAc);
            base.setShowThrowView(getHomeViewThrow);
            base.setThrowOne(getHomeViewThrowOne);
            base.setThrowTwo(getHomeViewThrowTwo);
            base.setShowPushNumView(getHomeViewPushNum);
            base.setShowToBaseView(getHomeViewBase);


            this.getScoreView.bringToFront();
        }

        void setRecordItem(RecordItem recordItem) {
            this.recordItem = recordItem;
            recordItem.updateCenter(center);
            recordItem.updateFirstBaseUI(base1);
            recordItem.updateOtherBaseUI(base2,2);
            recordItem.updateOtherBaseUI(base3,3);
            recordItem.updateOtherBaseUI(base,0);
        }

        void updateUI(NewRecordActivity activity){
            recordItem.updateCenter(center);
            recordItem.updateFirstBaseUI(base1);
            recordItem.updateOtherBaseUI(base2,2);
            recordItem.updateOtherBaseUI(base3,3);
            recordItem.updateOtherBaseUI(base,0);
            activity.scrollablePanel.notifyDataSetChanged();
            activity.score_scrollable_panel.notifyDataSetChanged();
        }
    }


    static class TeamNameViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TeamNameViewHolder(View view) {
            super(view);
            this.titleTextView = view.findViewById(R.id.text_teamName);
        }
    }
}
