package com.example.yuru.baseballscorekeeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

/**
 * Created by YURU on 2018/6/16.
 */

public class FirstBaseDialog {

    private NewRecordActivity newRecordActivity;
    private String[] nums = {"1","2","3","4","5","6","7","8","9"};
    private List<String> numList_left, numList_right;
    private Spinner spinner_left,spinner_right;


    public void setNewRecordActivity(NewRecordActivity newRecordActivity) {
        this.newRecordActivity = newRecordActivity;
    }

    public void setFirstBaseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(newRecordActivity);
        builder.setItems(new String[]{"擊出球","未擊出球"}, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which) {
                switch (which) {
                    case 0:
                        AlertDialog.Builder hit_builder = new AlertDialog.Builder(newRecordActivity);
                        hit_builder.setItems(new String[]{"高飛犧牲打", "觸及犧牲打", "其他"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i) {
                                    case 0:
                                        //高犧
                                        Toast.makeText(newRecordActivity, "高飛犧牲打", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1:
                                        //觸犧
                                        Toast.makeText(newRecordActivity, "觸及犧牲打", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 2:
                                        //一般
                                        Toast.makeText(newRecordActivity, "其他", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        });
                        hit_builder.show();
                        break;
                    case 1:
                        AlertDialog.Builder unhit_builder = new AlertDialog.Builder(newRecordActivity);
                        View view_unhit_dialog = LayoutInflater.from(newRecordActivity).inflate(R.layout.record_unhit_dialog, null);
                        view_unhit_dialog.setPadding(10,10,10,10);
                        unhit_builder.setView(view_unhit_dialog);
                        final AlertDialog unhit_dialog = unhit_builder.create();
                        view_unhit_dialog.findViewById(R.id.click_b).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(newRecordActivity, "保送", Toast.LENGTH_SHORT).show();
                                unhit_dialog.dismiss();
                                //TODO: ahkui 存入資料庫， 顯示圖片 B
                            }
                        });
                        view_unhit_dialog.findViewById(R.id.click_db).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(newRecordActivity, "觸身", Toast.LENGTH_SHORT).show();
                                unhit_dialog.dismiss();
                                //TODO:ahkui 存入資料庫， 顯示圖片 D
                            }
                        });
                        view_unhit_dialog.findViewById(R.id.click_k).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(newRecordActivity, "三振", Toast.LENGTH_SHORT).show();
                                unhit_dialog.dismiss();
                                //TODO:ahkui 存入資料庫， 顯示圖片 K
                            }
                        });
                        view_unhit_dialog.findViewById(R.id.click_nok).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(newRecordActivity, "不死三振", Toast.LENGTH_SHORT).show();
                                unhit_dialog.dismiss();
                                //TODO:ahkui 存入資料庫， 顯示圖片 倒K
                            }
                        });
                        unhit_dialog.show();
                        break;
                }
            }
        });
        builder.show();
    }

}
