package com.example.yuru.baseballscorekeeper.Dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yuru.baseballscorekeeper.Adapter.ScrollablePanelAdapter;
import com.example.yuru.baseballscorekeeper.Modal.RecordItem;
import com.example.yuru.baseballscorekeeper.Modal.RecordItemOtherBase;
import com.example.yuru.baseballscorekeeper.NewRecordActivity;
import com.example.yuru.baseballscorekeeper.R;

/**
 * Created by User on 2018/6/16.
 */

public class BaseOtherDialog {

    private NewRecordActivity activity;
    private RecordItemOtherBase recordItemUI;

    private String[] nums = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String[] isError = {"", "E"};
    private String[] push = {"(1)", "(2)", "(3)", "(4)", "(5)", "(6)", "(7)", "(8)", "(9)"};
    private Spinner spinner_left, spinner_right, spinner_left_e, spinner_right_e;
    private Spinner spinner_throw_left, spinner_throw_right;


    public void setBaseUI(RecordItemOtherBase recordItemUI) {
        this.recordItemUI = recordItemUI;
    }

    public void setBaseOtherDialog(final ScrollablePanelAdapter.OrderViewHolder viewHolder, final String[] items, final int base) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        switch (base) {
            case 0:
                recordItemUI = viewHolder.base;
                break;
            case 3:
                recordItemUI = viewHolder.base3;
                break;
            case 2:
                recordItemUI = viewHolder.base2;
                break;
            default:
                break;
        }

        //設定對話框內的項目
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    //點選推進
                    case 0:
                        new AlertDialog.Builder(activity)
                                .setTitle("推進")
                                .setItems(push, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, final int which) {
                                        String name = push[which];
                                        viewHolder.recordItem.setBallDirection(which + 1);  //設定推進數字
                                        viewHolder.recordItem.setBallPushNum(RecordItem.BALL_DIRECTION.values()[which + 1], base);
                                        //viewHolder.updateUI(activity);
                                        // 選擇完後  switch (which)   recordItemOtherBase.setShowPushNumViewVisibility(true);
                                        // 圖片id  recordItemOtherBase.setShowPushNumValue();
                                        // 存入資料庫， 顯示推進數字 (R.drawable.push1) ~ (R.drawable.push9)

                                        Toast.makeText(activity.getApplicationContext(), name, Toast.LENGTH_SHORT).show();

                                        //下一個選單失誤、上壘
                                        AlertDialog.Builder push_dialog = new AlertDialog.Builder(activity);
                                        View view_push_choice1 = View.inflate(activity, R.layout.record_actionname_dialog, null);      //自訂dialog布局
                                        push_dialog.setView(view_push_choice1);   // 設置view
                                        final AlertDialog new_push_dialog = push_dialog.create();    //根據builder設置好的一系列數據, 来建構一個dialog

                                        //(DP,TP,S,CS,PO,W,P,BK)   選擇完後   recordItemOtherBase.setShowActionNameViewVisibility(true);
                                        //沒有失誤喔喔喔 !!!!
                                        //點擊雙殺DP
                                        view_push_choice1.findViewById(R.id.click_dp).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(activity, "雙殺DP", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();   //按下選項後dialog消失
                                                viewHolder.recordItem.setBallPush(RecordItem.BALL_PUSH.DP, base);  //設定為DP類型
                                                viewHolder.updateUI(activity);
                                                //存入資料庫， 顯示圖片 DP  (R.drawable.double_plays)
                                            }
                                        });

                                        //點擊三殺TP
                                        view_push_choice1.findViewById(R.id.click_tp).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(activity, "三殺TP", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                                viewHolder.recordItem.setBallPush(RecordItem.BALL_PUSH.TP, base);
                                                viewHolder.updateUI(activity);
                                                // 存入資料庫， 顯示圖片 TP  (R.drawable.tripple_play)
                                            }
                                        });

                                        //點擊盜壘S
                                        view_push_choice1.findViewById(R.id.click_stolen).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(activity, "盜壘S", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                                viewHolder.recordItem.setBallPush(RecordItem.BALL_PUSH.S, base);
                                                viewHolder.updateUI(activity);
                                                //存入資料庫， 顯示圖片 S  (R.drawable.stolen_base)
                                            }
                                        });

                                        //點擊盜壘失敗CS
                                        view_push_choice1.findViewById(R.id.click_cs).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(activity, "盜壘失敗CS", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                                viewHolder.recordItem.setBallPush(RecordItem.BALL_PUSH.CS, base);
                                                viewHolder.updateUI(activity);
                                                //存入資料庫， 顯示圖片 CS  (R.drawable.caught_stolen)
                                            }
                                        });

                                        //點擊投手牽制PO
                                        view_push_choice1.findViewById(R.id.click_po).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(activity, "投手牽制PO", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                                viewHolder.recordItem.setBallPush(RecordItem.BALL_PUSH.PO, base);
                                                viewHolder.updateUI(activity);
                                                // 存入資料庫， 顯示圖片 PO  (R.drawable.put_outs)
                                            }
                                        });

                                        //點擊暴投W
                                        view_push_choice1.findViewById(R.id.click_w).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(activity, "暴投W", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                                viewHolder.recordItem.setBallPush(RecordItem.BALL_PUSH.W, base);
                                                viewHolder.updateUI(activity);
                                                // 存入資料庫， 顯示圖片 W  (R.drawable.put_outs)
                                            }
                                        });

                                        //點擊捕逸P
                                        view_push_choice1.findViewById(R.id.click_p).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(activity, "捕逸P", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                                viewHolder.recordItem.setBallPush(RecordItem.BALL_PUSH.P, base);
                                                viewHolder.updateUI(activity);
                                                //存入資料庫， 顯示圖片 P  (R.drawable.passed_ball)
                                            }
                                        });

                                        //點擊投手犯規BK
                                        view_push_choice1.findViewById(R.id.click_bk).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(activity, "投手犯規BK", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                                viewHolder.recordItem.setBallPush(RecordItem.BALL_PUSH.BK, base);
                                                viewHolder.updateUI(activity);
                                                //存入資料庫， 顯示圖片 BK  (R.drawable.balks)
                                            }
                                        });

                                        //點擊失誤E
                                        view_push_choice1.findViewById(R.id.click_error).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(activity, "失誤", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                                setErrorDialog(viewHolder, base);  // 點擊失誤後的選單
                                                viewHolder.updateUI(activity);
                                            }
                                        });

                                        //點擊無
                                        view_push_choice1.findViewById(R.id.click_null).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(activity, "無", Toast.LENGTH_SHORT).show();
                                                viewHolder.recordItem.setBallPush(RecordItem.BALL_PUSH.__, base);
                                                viewHolder.updateUI(activity);
                                                new_push_dialog.dismiss();
                                            }
                                        });
                                        new_push_dialog.show();
                                    }
                                }).show();
                        break;

                    //點選進壘
                    case 1:
                        new AlertDialog.Builder(activity)
                                .setItems(new String[]{"趁傳", "失誤", "無"}, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, final int which) {
                                        switch (which) {
                                            case 0:   //點選趁傳
                                                final View view_throwTo = LayoutInflater.from(activity).inflate(R.layout.record_throw_dialog, null);
                                                AlertDialog.Builder dialog_throw = new AlertDialog.Builder(activity);
                                                view_throwTo.setPadding(10, 10, 10, 10);
                                                dialog_throw.setView(view_throwTo);
                                                //設定spinner下拉選單
                                                spinner_throw_left = view_throwTo.findViewById(R.id.spinner_throw_left);
                                                spinner_throw_right = view_throwTo.findViewById(R.id.spinner_throw_right);
                                                ArrayAdapter throw_left = new ArrayAdapter(dialog_throw.getContext(), android.R.layout.simple_spinner_item, nums);
                                                ArrayAdapter throw_right = new ArrayAdapter(dialog_throw.getContext(), android.R.layout.simple_spinner_item, nums);
                                                throw_left.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                throw_right.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                spinner_throw_left.setAdapter(throw_left);
                                                spinner_throw_right.setAdapter(throw_left);
                                                dialog_throw.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        viewHolder.recordItem.setIsTo_BASE(true, base);
                                                        // 存入資料庫， 顯示箭頭  (判斷哪一格顯示不同箭頭)
                                                        int select_throw_left = (int) spinner_throw_left.getSelectedItemId();
                                                        //  存入資料庫， 顯示圖片  數字 select_throw_left+1  (R.drawable.throw 數字)
                                                        int select_throw_right = (int) spinner_throw_right.getSelectedItemId();
                                                        //  存入資料庫， 顯示圖片  數字 select_throw_right+1  (R.drawable.throw 數字)
                                                        viewHolder.recordItem.setBallPush(RecordItem.BALL_PUSH.THROW, base);
                                                        viewHolder.recordItem.setThrow(RecordItem.BALL_DIRECTION.values()[select_throw_left + 1], RecordItem.BALL_DIRECTION.values()[select_throw_right + 1], base);
                                                        viewHolder.updateUI(activity);
                                                        Toast.makeText(activity, "OK " + (select_throw_left + 1) + "," + (select_throw_right + 1), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                                dialog_throw.show();  //顯示dialog
                                                break;
                                            case 1:   //點選失誤
                                                viewHolder.recordItem.setIsTo_BASE(true, base);
                                                setErrorDialog(viewHolder, base);  // 點擊失誤後的選單
                                                break;
                                            case 2:   //點選無
                                                viewHolder.recordItem.setIsTo_BASE(true, base);
                                                viewHolder.recordItem.setBallPush(RecordItem.BALL_PUSH.__, base);
                                                viewHolder.updateUI(activity);
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }).show();
                        break;
                    default:
                        break;
                }
                Toast.makeText(activity, "您選擇的是" + items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();

    }


    public void setActivity(NewRecordActivity activity) {
        this.activity = activity;
    }

    //失誤選單 數字E-數字E
    private void setErrorDialog(final ScrollablePanelAdapter.OrderViewHolder viewHolder, final int base) {
        // viewHolder.recordItem.setBallPush(RecordItem.BALL_PUSH.E);
        @SuppressLint("InflateParams") View view_error = LayoutInflater.from(activity).inflate(R.layout.record_error_dialog, null);
        AlertDialog.Builder dialog_error = new AlertDialog.Builder(activity);
        view_error.setPadding(0, 10, 0, 10);
        dialog_error.setView(view_error);
        spinner_left = view_error.findViewById(R.id.spinner_left);
        spinner_right = view_error.findViewById(R.id.spinner_right);
        spinner_left_e = view_error.findViewById(R.id.spinner_left_e);
        spinner_right_e = view_error.findViewById(R.id.spinner_right_e);
        ArrayAdapter<String> left_num = new ArrayAdapter<>(dialog_error.getContext(), android.R.layout.simple_spinner_item, nums);
        ArrayAdapter<String> right_num = new ArrayAdapter<>(dialog_error.getContext(), android.R.layout.simple_spinner_item, nums);
        ArrayAdapter<String> left_error = new ArrayAdapter<>(dialog_error.getContext(), android.R.layout.simple_spinner_item, isError);
        ArrayAdapter<String> right_error = new ArrayAdapter<>(dialog_error.getContext(), android.R.layout.simple_spinner_item, isError);
        left_num.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        right_num.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        left_error.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        right_error.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_left.setAdapter(left_num);
        spinner_right.setAdapter(right_num);
        spinner_left_e.setAdapter(left_error);
        spinner_right_e.setAdapter(right_error);

        //點擊ok
        dialog_error.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                //   recordItemOtherBase.setShowActionViewVisibility(true);

                String select_left_E = "";
                String select_right_E = "";
                boolean is_left_E = false;
                boolean is_right_E = false;

                int select_spinner_left = (int) spinner_left.getSelectedItemId();   //存入資料庫， 顯示圖片  數字select_spinner_left+1  (R.drawable.throw 數字)
                int select_spinner_right = (int) spinner_right.getSelectedItemId();  // 存入資料庫， 顯示圖片  數字 select_spinner_right+1  (R.drawable.throw 數字)
                int select_spinner_left_E = (int) spinner_left_e.getSelectedItemId();
                int select_spinner_right_E = (int) spinner_right_e.getSelectedItemId();
                if (select_spinner_left_E == 1) {
                    is_left_E = true;
                    // recordItemOtherBase.setShowActionOneAcViewVisibility(true);
                }
                if (select_spinner_right_E == 1) {
                    is_right_E = true;
                    //  recordItemOtherBase.setShowActionTwoAcViewVisibility(true);
                }
                viewHolder.recordItem.setBallPush(RecordItem.BALL_PUSH.E, base);
                viewHolder.recordItem.setError(RecordItem.BALL_DIRECTION.values()[select_spinner_left + 1], is_left_E, RecordItem.BALL_DIRECTION.values()[select_spinner_right + 1], is_right_E, base);
                viewHolder.updateUI(activity);
                Toast.makeText(activity, "OK ," + (select_spinner_left + 1) + select_left_E + "," + (select_spinner_right + 1) + select_right_E, Toast.LENGTH_SHORT).show();
            }
        });
        dialog_error.show();

    }

}