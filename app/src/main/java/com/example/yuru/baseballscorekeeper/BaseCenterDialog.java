package com.example.yuru.baseballscorekeeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

/**
 * Created by YURU on 2018/6/18.
 */

public class BaseCenterDialog {

    private NewRecordActivity activity;

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



}
