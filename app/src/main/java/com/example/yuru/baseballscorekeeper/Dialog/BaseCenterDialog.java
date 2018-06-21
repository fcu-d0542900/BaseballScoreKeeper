package com.example.yuru.baseballscorekeeper.Dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yuru.baseballscorekeeper.Modal.Player;
import com.example.yuru.baseballscorekeeper.Modal.RecordItem;
import com.example.yuru.baseballscorekeeper.NewRecordActivity;
import com.example.yuru.baseballscorekeeper.R;
import com.example.yuru.baseballscorekeeper.Adapter.ScrollablePanelAdapter;

/**
 * Created by YURU on 2018/6/18.
 */

public class BaseCenterDialog {

    private ScrollablePanelAdapter.PlayerInfoViewHolder playerInfoViewHolder;

    private NewRecordActivity activity;

    private EditText editText_playerName,editText_playerNum;
    private Spinner spinner_position;

    private EditText editText_change_P,editText_change_C;
    private EditText editText_change_1B,editText_change_2B,editText_change_3B,editText_change_SS;
    private EditText editText_change_LF,editText_change_CF,editText_change_RF;
    private int change_P,change_C,change_1B,change_2B,change_3B,change_SS,change_LF,change_CF,change_RF;

    private String[] center_choice = new String[]{"得分/出局", "安打","替換守備","替換打者","結束半局"};
    private String[] hits_choice = new String[]{"一壘安打", "二壘安打","三壘安打","全壘打"};



    public void setActivity(NewRecordActivity activity) {
        this.activity = activity;
    }

    public void setBaseCenterDialog(final ScrollablePanelAdapter.OrderViewHolder viewHolder, final int pos) {
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
                                final View view_center_choice1 = View.inflate(activity, R.layout.record_center_dialog, null);  //自訂dialog布局
                                center_choice1.setView(view_center_choice1);
                                // 設置view
                                final AlertDialog center_dialog = center_choice1.create();  //根據builder設置好的一系列數據, 来建構一個dialog

                                //點擊(得分 123出局 殘壘)後顯示  recordItemCenter.setShowCenterVisibility(true);
                                //點擊得分
                                view_center_choice1.findViewById(R.id.click_run).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //顯示得分 (R.drawable.runs)
                                        viewHolder.recordItem.setRUN_OUT_TYPE(RecordItem.RUNS_OUT.RUN);
                                        viewHolder.recordItem.toggleScore();
                                        viewHolder.updateUI(activity);
                                        activity.score_scrollablePanelAdapter.updateData();
                                        Toast.makeText(activity, "得分", Toast.LENGTH_SHORT).show();
                                        center_dialog.dismiss();
                                    }
                                });

                                //點擊一出局
                                view_center_choice1.findViewById(R.id.click_out1).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //顯示一出局 (R.drawable.out1)
                                        viewHolder.recordItem.setRUN_OUT_TYPE(RecordItem.RUNS_OUT.ONE_OUT);
                                        viewHolder.updateUI(activity);
                                        Toast.makeText(activity, "一出局", Toast.LENGTH_SHORT).show();
                                        center_dialog.dismiss();
                                    }
                                });

                                //點擊二出局
                                view_center_choice1.findViewById(R.id.click_out2).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //顯示二出局 (R.drawable.out2)
                                        viewHolder.recordItem.setRUN_OUT_TYPE(RecordItem.RUNS_OUT.TWO_OUT);
                                        viewHolder.updateUI(activity);
                                        Toast.makeText(activity, "二出局", Toast.LENGTH_SHORT).show();
                                        center_dialog.dismiss();
                                    }
                                });

                                //點擊三出局
                                view_center_choice1.findViewById(R.id.click_out3).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //顯示三出局 (R.drawable.out3)
                                        viewHolder.recordItem.setRUN_OUT_TYPE(RecordItem.RUNS_OUT.THREE_OUT);
                                        viewHolder.updateUI(activity);
                                        Toast.makeText(activity, "三出局", Toast.LENGTH_SHORT).show();
                                        center_dialog.dismiss();
                                    }
                                });
                                //點擊殘壘
                                view_center_choice1.findViewById(R.id.click_left).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //顯示殘壘 (R.drawable.left_on_base)
                                        viewHolder.recordItem.setRUN_OUT_TYPE(RecordItem.RUNS_OUT.LEFT_BASE);
                                        viewHolder.updateUI(activity);
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
                                                viewHolder.recordItem.setHIT_Num(which+1);
                                                viewHolder.updateUI(activity);
                                                // 設定安打紅線顯示
                                                // whitch=0 一壘安打 顯示下面(1)
                                                // whitch=1 二壘安打 顯示下面(1) (2)
                                                // whitch=2 三壘安打 顯示下面(1) (2) (3)
                                                // whitch=3 全壘打 顯示下面(1) (2) (3) (4)
                                                // (1)  recordItemCenter.setShowHit1ViewVisibility(true);
                                                // (2)  recordItemCenter.setShowHit2ViewVisibility(true);
                                                // (3)  recordItemCenter.setShowHit3ViewVisibility(true);
                                                // (4)  recordItemCenter.setShowHit4ViewVisibility(true);

                                                Toast.makeText(activity.getApplicationContext(), name_h, Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .show();
                                break;

                            //點擊替換守備
                            case 2:
                                Toast.makeText(activity.getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                //activity.currentRecord.getTeam().addDefPlayer();
                                change_player_garrison(viewHolder);
                                break;

                            //點擊替換打者
                            case 3:
                                Toast.makeText(activity.getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                // activity.currentRecord.getTeam().changeAttPlayer();
                                change_player_hitter(viewHolder,pos);
                                break;

                            //點擊結束半局
                            case 4:
                                Toast.makeText(activity.getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                // 顯示結束斜線 recordItemFirstBase.setShowEndViewVisibility(true)
                                viewHolder.recordItem.setEND(true);
                                activity.currentRecord.getTeam().nextRound();
                                activity.scrollablePanelAdapter.updateData();
                                activity.score_scrollablePanelAdapter.updateData();
                                break;
                            default:
                                break;
                        }

                    }
                })
                .show();


    }

    // 更改打者
    public void change_player_hitter(final ScrollablePanelAdapter.OrderViewHolder viewHolder, final int pos) {
        final View view_set_player = LayoutInflater.from(activity).inflate(R.layout.dialog_new_player, null);
        view_set_player.setPadding(10,10,10,10);
        ImageView img = view_set_player.findViewById(R.id.image_title_newPlayer);
        img.setVisibility(View.GONE);

        editText_playerName = view_set_player.findViewById(R.id.editText_playerName);
        editText_playerNum = view_set_player.findViewById(R.id.editText_playerNum);
        spinner_position = view_set_player.findViewById(R.id.spinner_position);

        AlertDialog.Builder dialog_setPlayer = new AlertDialog.Builder(activity);
        dialog_setPlayer.setView(view_set_player);

        //設定選單
        ArrayAdapter<String> adapter_position = new ArrayAdapter<>(dialog_setPlayer.getContext(),android.R.layout.simple_spinner_item,new String[]{"","P","C","1B","2B","3B","SS","LF","CF","RF"});
        adapter_position.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_position.setAdapter(adapter_position);

        dialog_setPlayer.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!editText_playerNum.getText().toString().equals("")) {
                    String playerName = editText_playerName.getText().toString();
                    int playerNum = Integer.valueOf(editText_playerNum.getText().toString());
                    int playerPosition = (int) spinner_position.getSelectedItemId();

                    //顯示 recordItemCenter.setShowChangeHitterVisibility(true);
                    //儲存更改球員資料 playerName playerNum  playerPosition
                    //更換球員顯示名稱
                    Player change_player = new Player(playerNum,playerName,Player.POSITION.values()[playerPosition]);
                    viewHolder.recordItem.changeAttPlayer(change_player);
                    viewHolder.updateUI(activity);

                    activity.currentRecord.getTeam().getTeamMember().remove(pos-1);
                    activity.currentRecord.getTeam().getTeamMember().add(pos-1,change_player);

                    activity.scrollablePanel.notifyDataSetChanged();

                    Toast.makeText(activity.getApplicationContext(), "SET " + playerName + "," + playerNum + ","  + playerPosition, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(activity.getApplicationContext(), "請填入背號!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog_setPlayer.show();

    }

    //更換守備
    public void change_player_garrison(final ScrollablePanelAdapter.OrderViewHolder viewHolder) {
        AlertDialog.Builder dialog_change_garrison = new AlertDialog.Builder(activity);
        View view_change_garrison = LayoutInflater.from(activity).inflate(R.layout.record_change_garrison, null);
        view_change_garrison.setPadding(30,10,10,10);
        dialog_change_garrison.setView(view_change_garrison);
        editText_change_P = view_change_garrison.findViewById(R.id.editText_change_P);
        editText_change_C = view_change_garrison.findViewById(R.id.editText_change_C);
        editText_change_1B = view_change_garrison.findViewById(R.id.editText_change_1B);
        editText_change_2B = view_change_garrison.findViewById(R.id.editText_change_2B);
        editText_change_3B = view_change_garrison.findViewById(R.id.editText_change_3B);
        editText_change_SS = view_change_garrison.findViewById(R.id.editText_change_SS);
        editText_change_LF = view_change_garrison.findViewById(R.id.editText_change_LF);
        editText_change_CF = view_change_garrison.findViewById(R.id.editText_change_CF);
        editText_change_RF = view_change_garrison.findViewById(R.id.editText_change_RF);

        dialog_change_garrison.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String change_garrison = "";

                //若輸入框不為空 更改其背號
                if(!editText_change_P.getText().toString().equals("")) {
                    change_garrison +=  ", P:" + editText_change_P.getText().toString();
                    change_P = Integer.valueOf(editText_change_P.getText().toString());
                }
                if(!editText_change_C.getText().toString().equals("")) {
                    change_garrison += ", C:" + editText_change_C.getText().toString();
                    change_C = Integer.valueOf(editText_change_C.getText().toString());
                }
                if(!editText_change_1B.getText().toString().equals("")) {
                    change_garrison += ", 1B:" + editText_change_1B.getText().toString();
                    change_1B = Integer.valueOf(editText_change_1B.getText().toString());
                }
                if(!editText_change_2B.getText().toString().equals("")) {
                    change_garrison += ", 2B:" + editText_change_2B.getText().toString();
                    change_2B = Integer.valueOf(editText_change_2B.getText().toString());
                }
                if(!editText_change_3B.getText().toString().equals("")) {
                    change_garrison += ", 3B:" + editText_change_3B.getText().toString();
                    change_3B = Integer.valueOf(editText_change_3B.getText().toString());
                }
                if(!editText_change_SS.getText().toString().equals("")) {
                    change_garrison += ", SS:" + editText_change_SS.getText().toString();
                    change_SS = Integer.valueOf(editText_change_SS.getText().toString());
                }
                if(!editText_change_LF.getText().toString().equals("")) {
                    change_garrison += ", LF " + editText_change_LF.getText().toString();
                    change_LF = Integer.valueOf(editText_change_LF.getText().toString());
                }
                if(!editText_change_CF.getText().toString().equals("")) {
                    change_garrison += ", CF:" + editText_change_CF.getText().toString();
                    change_LF = Integer.valueOf(editText_change_CF.getText().toString());
                }
                if(!editText_change_RF.getText().toString().equals("")) {
                    change_garrison += ", RF:" + editText_change_RF.getText().toString();
                    change_RF = Integer.valueOf(editText_change_RF.getText().toString());
                }


                viewHolder.center.setShowChangeGarrisonVisibility(true);
                //TODO YURU 填資料咯
                //現在只有顯示更換守備符號
                if(!change_garrison.equals("")) {
//                    viewHolder.recordItem.addDefPlayer(new Player(x,x,x)); //TODO YURU 填資料進去
                    viewHolder.updateUI(activity);
                }

                //TODO YURU 更換守備資料直接叫下面的 method 然後改 player 的內容就好
                //TODO YURU viewHolder.recordItem.getCurrentDefPlayer();
                viewHolder.recordItem.getCurrentDefPlayer();
                Toast.makeText(activity.getApplicationContext(), "OK " + change_garrison, Toast.LENGTH_SHORT).show();
            }
        });

        dialog_change_garrison.show();
    }

}
