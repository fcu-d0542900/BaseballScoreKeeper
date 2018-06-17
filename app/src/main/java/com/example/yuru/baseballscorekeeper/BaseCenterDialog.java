package com.example.yuru.baseballscorekeeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.baseball.Player;
import com.baseball.RecordItemCenter;

/**
 * Created by YURU on 2018/6/18.
 */

public class BaseCenterDialog {

    private RecordItemCenter recordItemCenter;

    private NewRecordActivity activity;

    private EditText editText_playerName,editText_playerNum;
    private Spinner spinner_position;

    private String[] center_choice = new String[]{"得分/出局", "安打","替換守備","替換打者","結束半局"};
    private String[] hits_choice = new String[]{"一壘安打", "二壘安打","三壘安打","全壘打"};



    public void setActivity(NewRecordActivity activity) {
        this.activity = activity;
    }

    public void setBaseCenterDialog(final ScrollablePanelAdapter.OrderViewHolder viewHolder) {
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

                                //TODO ahkui  點擊(得分 123出局 殘壘)後顯示  recordItemCenter.setShowCenterVisibility(true);
                                //點擊得分
                                view_center_choice1.findViewById(R.id.click_run).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //TODO 顯示得分 (R.drawable.runs)
                                        Toast.makeText(activity, "得分", Toast.LENGTH_SHORT).show();
                                        center_dialog.dismiss();
                                    }
                                });
                                //點擊一出局
                                view_center_choice1.findViewById(R.id.click_out1).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //TODO 顯示得分 (R.drawable.out1)
                                        Toast.makeText(activity, "一出局", Toast.LENGTH_SHORT).show();
                                        center_dialog.dismiss();
                                    }
                                });
                                //點擊二出局
                                view_center_choice1.findViewById(R.id.click_out2).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //TODO 顯示得分 (R.drawable.out3)
                                        Toast.makeText(activity, "二出局", Toast.LENGTH_SHORT).show();
                                        center_dialog.dismiss();
                                    }
                                });
                                //點擊三出局
                                view_center_choice1.findViewById(R.id.click_out3).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //TODO 顯示得分 (R.drawable.out3)
                                        Toast.makeText(activity, "三出局", Toast.LENGTH_SHORT).show();
                                        center_dialog.dismiss();
                                    }
                                });
                                //點擊殘壘
                                view_center_choice1.findViewById(R.id.click_left).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //TODO 顯示得分 (R.drawable.left_on_base)
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
                                                // TODO ahkui 設定安打紅線顯示
                                                // whitch=0 一壘安打 顯示下面(1)
                                                // whitch=1 二壘安打 顯示下面(1) (2)
                                                // whitch=2 三壘安打 顯示下面(1) (2) (3)
                                                // whitch=3 四壘安打 顯示下面(1) (2) (3) (4)
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
                                // TODO activity.currentRecord.getTeam().changeDefPlayer();

                                break;

                            //點擊替換打者
                            case 3:
                                Toast.makeText(activity.getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                // TODO activity.currentRecord.getTeam().changeAttPlayer();
                                change_player_hitter();
                                break;

                            //點擊結束半局
                            case 4:
                                Toast.makeText(activity.getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                activity.currentRecord.getTeam().nextRound();
                                //TODO ahkui   updateData
                                //activity.updateData(recordItems);
                                break;
                            default:
                                break;
                        }

                    }
                })
                .show();


    }


    public void change_player_hitter() {
        View view_set_player = LayoutInflater.from(activity).inflate(R.layout.dialog_new_player, null);
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
                    String playerName = editText_playerName.getText().toString();
                    int playerNum = Integer.valueOf(editText_playerNum.getText().toString());
                    int playerPosition = (int) spinner_position.getSelectedItemId();

                    //TODO ahkui  儲存更改球員資料 playerName playerNum  playerPosition

                    //playerInfo.setName(playerName);
                    //playerInfo.setId(playerNum);
                    //playerInfo.setPosition(Player.POSITION.values()[playerPosition]);
                    //viewHolder.text_playerName.setText(playerName);
                    //viewHolder.text_playerNum.setText(Integer.valueOf(playerNum).toString());
                    //viewHolder.text_playerPosition.setText(playerInfo.getPosition().toString().replaceAll("_",""));

                    Toast.makeText(activity.getApplicationContext(), "SET " + playerName + "," + playerNum + ","  + playerPosition, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(activity.getApplicationContext(), "請填入背號!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog_setPlayer.show();

    }

}
