package com.example.yuru.baseballscorekeeper;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
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

import com.baseball.Player;
import com.baseball.RecordItem;
import com.baseball.RecordItemFirstBase;
import com.baseball.RecordItemOtherBase;

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

    private BaseOtherDialog baseOtherDialog;
    private BaseFirstDialog baseFirstDialog = new BaseFirstDialog();

    final String[] center_choice = new String[]{"得分/出局", "安打","替換守備","替換打者","結束半局"};
    final String[] hits_choice = new String[]{"一壘安打", "二壘安打","三壘安打","全壘打"};

    private static final int TEAMNAME_TYPE = 4;
    private static final int PLAYERINFO_TYPE = 0;
    private static final int BOARDNUM_TYPE = 1;
    private static final int ORDER_TYPE = 2;


    public ScrollablePanelAdapter(NewRecordActivity activity) {
        super();
        this.activity = activity;
    }

    @Override
    public int getRowCount() {
        return 9;
    }

    @Override
    public int getColumnCount() {
        return activity.currentRecord.getTeam().getLastRecordItemsColumn() + 1 + 1 +5;
    }


    @Override
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
                break;
            default:
                setOrderView(row, column, (OrderViewHolder) holder);
        }
    }

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

    private void setBoardNumView(int pos, BoardNumViewHolder viewHolder) {
        viewHolder.dateTextView.setText(activity.currentRecord.getTeam().getRoundText(activity.currentRecord.getTeam().getRecordItemsPositionRound(pos)));
    }

    @SuppressLint("SetTextI18n")
    private void setPlayerInfoView(int pos, final PlayerInfoViewHolder viewHolder) {
        final Player playerInfo = activity.currentRecord.getTeam().getTeamMember().get(pos - 1);
        viewHolder.text_batOrder.setText(Integer.valueOf(pos).toString());


        if (playerInfo != null && pos>0) {

            //設定資料
            viewHolder.text_playerPosition.setText(playerInfo.getPosition().toString().replaceAll("_", ""));
            viewHolder.text_playerName.setText(playerInfo.getName());
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

                    view_set_player = LayoutInflater.from(activity).inflate(R.layout.dialog_new_player, null);
                    view_set_player.setPadding(3,0,3,0);
                    ImageView img = view_set_player.findViewById(R.id.image_title_newPlayer);
                    img.setVisibility(View.GONE);

                    editText_playerName = view_set_player.findViewById(R.id.editText_playerName);
                    editText_playerNum = view_set_player.findViewById(R.id.editText_playerNum);
                    spinner_position = view_set_player.findViewById(R.id.spinner_position);

                    AlertDialog.Builder dialog_setPlayer = new AlertDialog.Builder(activity);
                    dialog_setPlayer.setView(view_set_player);

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

                                playerInfo.setName(playerName);
                                playerInfo.setId(playerNum);
                                playerInfo.setPosition(Player.POSITION.values()[playerPosition]);
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

    private void setOrderView(final int row, final int column, final OrderViewHolder viewHolder) {
        final RecordItem recordItem = activity.currentRecord.getTeam().getRecordItems(row - 1,column - 1);
        if (recordItem != null) {
            viewHolder.getScoreView.bringToFront();

            //中間菱形區域
            viewHolder.getScoreView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(activity)
                            .setItems(center_choice, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String name = center_choice[which];
                                    Toast.makeText(activity.getApplicationContext(), name, Toast.LENGTH_SHORT).show();

                                    switch (which) {
                                        //點擊得分/出局
                                        case 0:
                                            AlertDialog.Builder center_choice1 = new AlertDialog.Builder(activity);
                                            View view_center_choice1 = View.inflate(activity, R.layout.record_center_dialog, null);      //自訂dialog布局
                                            center_choice1.setView(view_center_choice1);
                                            // 設置view
                                            final AlertDialog center_dialog = center_choice1.create();    //根據builder設置好的一系列數據, 来建構一個dialog
                                            //點擊得分
                                            view_center_choice1.findViewById(R.id.click_run).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(activity, "得分", Toast.LENGTH_SHORT).show();
                                                    center_dialog.dismiss();
                                                }
                                            });
                                            //點擊一出局
                                            view_center_choice1.findViewById(R.id.click_out1).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(activity, "一出局", Toast.LENGTH_SHORT).show();
                                                    center_dialog.dismiss();
                                                }
                                            });
                                            //點擊二出局
                                            view_center_choice1.findViewById(R.id.click_out2).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(activity, "二出局", Toast.LENGTH_SHORT).show();
                                                    center_dialog.dismiss();
                                                }
                                            });
                                            //點擊三出局
                                            view_center_choice1.findViewById(R.id.click_out3).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(activity, "三出局", Toast.LENGTH_SHORT).show();
                                                    center_dialog.dismiss();
                                                }
                                            });
                                            //點擊殘壘
                                            view_center_choice1.findViewById(R.id.click_left).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(activity, "殘壘", Toast.LENGTH_SHORT).show();
                                                    center_dialog.dismiss();
                                                }
                                            });
                                            center_dialog.show();
                                            break;

                                        //點擊安打
                                        case 1:
                                            new AlertDialog.Builder(activity)
                                                    .setItems(hits_choice, new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            String name_h = hits_choice[which];
                                                            switch (which) {
                                                                //點擊一壘安打
                                                                case 0:
                                                                    Toast.makeText(activity.getApplicationContext(), name_h, Toast.LENGTH_SHORT).show();
                                                                    break;

                                                                //點擊二壘安打
                                                                case 1:
                                                                    Toast.makeText(activity.getApplicationContext(), name_h, Toast.LENGTH_SHORT).show();
                                                                    break;

                                                                //點擊壘安打
                                                                case 2:
                                                                    Toast.makeText(activity.getApplicationContext(), name_h, Toast.LENGTH_SHORT).show();
                                                                    break;

                                                                //點擊三全壘打
                                                                case 3:
                                                                    Toast.makeText(activity.getApplicationContext(), name_h, Toast.LENGTH_SHORT).show();
                                                                    break;

                                                                default:
                                                                    break;
                                                            }
                                                        }
                                                    })
                                                    .show();
                                            break;

                                        //點擊替換守備
                                        case 2:
                                            Toast.makeText(activity.getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                            // TODO activity.currentRecord.getTeam().changeDefPlayer();

                                            break;

                                        //點擊替換打者
                                        case 3:
                                            Toast.makeText(activity.getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                            // TODO activity.currentRecord.getTeam().changeAttPlayer();
                                            break;

                                        //點擊結束半局
                                        case 4:
                                            Toast.makeText(activity.getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                            activity.currentRecord.getTeam().nextRound();
                                            break;
                                        default:
                                            break;
                                    }


                                }
                            })
                            .show();
                    Toast.makeText(v.getContext(), "得分區域" +recordItem.getAttPlayer().getName(), Toast.LENGTH_SHORT).show();

                }
            });

            //一壘
            viewHolder.getFirstView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseFirstDialog.setNewRecordActivity(activity);
                    baseFirstDialog.setBaseFirstDialog(viewHolder);
                    Toast.makeText(v.getContext(), "一壘" , Toast.LENGTH_SHORT).show();

                }
            });

            //二壘
            viewHolder.getSecondView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO 玉鳳 二壘
                    baseOtherDialog = new BaseOtherDialog();
                    baseOtherDialog.setNewRecordActivity(activity);
                    baseOtherDialog.setBaseTwoDialog(viewHolder,new String[]{"推進","進壘"});
                    Toast.makeText(v.getContext(), "二壘" , Toast.LENGTH_SHORT).show();

                }
            });

            //三壘
            viewHolder.getThirdView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    baseOtherDialog = new BaseOtherDialog();
                    baseOtherDialog.setNewRecordActivity(activity);
                    baseOtherDialog.setBaseTwoDialog(viewHolder,new String[]{"推進","進壘"});

                    Toast.makeText(v.getContext(), "三壘" , Toast.LENGTH_SHORT).show();
                }
            });

            //本壘
            viewHolder.getHomeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO 玉鳳 本壘
                    baseOtherDialog = new BaseOtherDialog();
                    baseOtherDialog.setNewRecordActivity(activity);
                    baseOtherDialog.setBaseTwoDialog(viewHolder,new String[]{"推進","進壘"});
                    Toast.makeText(v.getContext(), "本壘" , Toast.LENGTH_SHORT).show();

                }
            });

            //計算好壞球
            viewHolder.getBallView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), "好壞球" , Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    private static class BoardNumViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;

        BoardNumViewHolder(View itemView) {
            super(itemView);
            this.dateTextView = itemView.findViewById(R.id.text_boardNum);

        }

    }

    private static class PlayerInfoViewHolder extends RecyclerView.ViewHolder {
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

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView getScoreView;
        TextView getFirstView;
        TextView getHomeView;
        TextView getSecondView;
        TextView getThirdView;
        TextView getBallView;

        //更改球員
        public ImageView getChangeGarrison,getChangeHitter;
        //安打
        public ImageView getHit1View,getHit2View,getHit3View,getHit4View;

        //一壘
        public ImageView getFirstViewZero,getFirstViewHR;
        public FrameLayout getFirstViewOne,getFirstViewTwo,getFirstViewThree;
        public ImageView getFirstViewOneTop,getFirstViewOneNum,getFirstViewOneBottom;
        public ImageView getFirstViewOneAc1,getFirstViewOneAc2;
        public ImageView getFirstViewTwoNum,getFirstViewTwoAc;
        public ImageView getFirstViewThreeNum,getFirstViewThreeAc;
        public ImageView getSacrificeFly,getSacrificeHits;
        public RecordItemOtherBase base1;

        //二壘
        public ImageView getSecondViewActionName,getSecondViewPushNum,getSecondViewBase;
        public LinearLayout getSecondViewAction;
        public ImageView getSecondViewActionOneNum,getSecondViewActionOneAc;
        public ImageView getSecondViewActionTwoNum,getSecondViewActionTwoAc;
        public LinearLayout getSecondViewThrow;
        public ImageView getSecondViewThrowOne,getSecondViewThrowTwo;
        public RecordItemOtherBase base2;

        //三壘
        public ImageView getThirdViewActionName,getThirdViewPushNum,getThirdViewBase;
        public LinearLayout getThirdViewAction;
        public ImageView getThirdViewActionOneNum,getThirdViewActionOneAc;
        public ImageView getThirdViewActionTwoNum,getThirdViewActionTwoAc;
        public LinearLayout getThirdViewThrow;
        public ImageView getThirdViewThrowOne,getThirdViewThrowTwo;
        public RecordItemOtherBase base3;

        //本壘
        public ImageView getHomeViewActionName,getHomeViewPushNum,getHomeViewBase;
        public LinearLayout getHomeViewAction;
        public ImageView getHomeViewActionOneNum,getHomeViewActionOneAc;
        public ImageView getHomeViewActionTwoNum,getHomeViewActionTwoAc;
        public LinearLayout getHomeViewThrow;
        public ImageView getHomeViewThrowOne,getHomeViewThrowTwo;
        public RecordItemFirstBase base;

        OrderViewHolder(View view) {
            super(view);

            this.getScoreView = view.findViewById(R.id.centerView);
            this.getFirstView = view.findViewById(R.id.firstView);
            this.getHomeView = view.findViewById(R.id.homeView);
            this.getSecondView = view.findViewById(R.id.secondView);
            this.getThirdView = view.findViewById(R.id.thirdView);
            this.getBallView= view.findViewById(R.id.ballView);

            this.getChangeGarrison = view.findViewById(R.id.image_change_garrison);
            this.getChangeHitter = view.findViewById(R.id.image_change_hitter);
            this.getHit1View = view.findViewById(R.id.image_hit1);
            this.getHit2View = view.findViewById(R.id.image_hit2);
            this.getHit3View = view.findViewById(R.id.image_hit3);
            this.getHit4View = view.findViewById(R.id.image_hit4);


            //一壘
            this.getFirstViewZero = view.findViewById(R.id.image_firstView_zero);
            this.getFirstViewOne = view.findViewById(R.id.frame_firstView_one);
            this.getFirstViewTwo = view.findViewById(R.id.frame_firstView_two);
            this.getFirstViewThree = view.findViewById(R.id.frame_firstView_three);
            this.getFirstViewHR = view.findViewById(R.id.image_HR);
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
            // TODO YURU 把它全部放進 base1
//            base1.setShowActionNameView();
//            base1.setShowActionOneAcView();
//            base1.setShowActionTwoAcView();
//            base1.setShowActionView();
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
            // TODO 把 UI set 進去這邊 YURU 這樣懂了嗎？
//            base.setShowThreeView(getHomeViewThrow);



            this.getScoreView.bringToFront();

        }
    }

    private static class TeamNameViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        TeamNameViewHolder(View view) {
            super(view);
            this.titleTextView = view.findViewById(R.id.title);
        }
    }

    public void setActivity(NewRecordActivity activity) {
        this.activity =activity;
    }





}


