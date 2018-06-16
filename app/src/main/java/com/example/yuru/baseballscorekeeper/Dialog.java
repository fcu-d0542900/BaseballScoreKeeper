package com.example.yuru.baseballscorekeeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by User on 2018/6/16.
 */

public class Dialog {

    private NewRecordActivity newRecordActivity;
    private String[] nums = {"1","2","3","4","5","6","7","8","9"};
    private String[] isError={"","E"};
    private String[] push = {"(1)","(2)","(3)","(4)","(5)","(6)","(7)","(8)","(9)"};
    private List<String>  numList_left, numList_right;
    private Spinner spinner_left,spinner_right,spinner_left_e,spinner_right_e;
    int pos_left,pos_right,pos_push,pos_erroe_l;

    public void getTwoBaseDialog(final String[] items) {
        AlertDialog.Builder builder = new AlertDialog.Builder(newRecordActivity);

        numList_left = new ArrayList<String>();
        numList_right = new ArrayList<String>();

        for(int i = 0;i < nums.length;i++)
        {
            numList_left.add(nums[i]);
            numList_right.add(nums[i]);
        }

        //設定對話框內的項目
        builder.setItems(items, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which){
                switch (which){
                    //點選推進
                    case 0:
                        new AlertDialog.Builder(newRecordActivity)
                                .setTitle("推進")
                                .setItems(push, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String name = push[which];    //TODO  推進數字(?)
                                        Toast.makeText(newRecordActivity.getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                        //下一個選單失誤、上壘
                                        AlertDialog.Builder push_dialog = new AlertDialog.Builder(newRecordActivity);
                                        View view_push_choice1 = View.inflate(newRecordActivity, R.layout.record_actionname_dialog, null);      //自訂dialog布局
                                        push_dialog.setView(view_push_choice1);   // 設置view
                                        final AlertDialog new_push_dialog = push_dialog.create();    //根據builder設置好的一系列數據, 来建構一個dialog
                                        //點擊雙殺DP
                                        view_push_choice1.findViewById(R.id.click_dp).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(newRecordActivity, "雙殺DP", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                            }
                                        });
                                        //點擊三殺TP
                                        view_push_choice1.findViewById(R.id.click_tp).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(newRecordActivity, "三殺TP", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                            }
                                        });
                                        //點擊盜壘S
                                        view_push_choice1.findViewById(R.id.click_stolen).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(newRecordActivity, "盜壘S", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                            }
                                        });
                                        //點擊盜壘失敗CS
                                        view_push_choice1.findViewById(R.id.click_cs).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(newRecordActivity, "盜壘失敗CS", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                            }
                                        });
                                        //點擊投手牽制PO
                                        view_push_choice1.findViewById(R.id.click_po).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(newRecordActivity, "投手牽制PO", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                            }
                                        });
                                        //點擊暴投
                                        view_push_choice1.findViewById(R.id.click_w).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(newRecordActivity, "暴投W", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                            }
                                        });
                                        //點擊捕逸P
                                        view_push_choice1.findViewById(R.id.click_p).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(newRecordActivity, "捕逸P", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                            }
                                        });
                                        //點擊投手犯規BK
                                        view_push_choice1.findViewById(R.id.click_bk).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(newRecordActivity, "投手犯規BK", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                            }
                                        });
                                        //點擊失誤
                                        view_push_choice1.findViewById(R.id.click_error).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(newRecordActivity, "失誤", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                                //點擊失誤後選單
                                                pos_left=0;
                                                pos_right=0;
                                                View view_error = LayoutInflater.from(newRecordActivity).inflate(R.layout.record_error_dialog, null);
                                                AlertDialog.Builder dialog_error = new AlertDialog.Builder(newRecordActivity);
                                                dialog_error.setView(view_error);
                                                spinner_left = view_error.findViewById(R.id.spinner_left);
                                                spinner_right=view_error.findViewById(R.id.spinner_right);
                                                spinner_left_e=view_error.findViewById(R.id.spinner_left_e);
                                                spinner_right_e=view_error.findViewById(R.id.spinner_right_e);
                                                ArrayAdapter left_num = new ArrayAdapter( dialog_error.getContext(),android.R.layout.simple_spinner_item, nums);
                                                ArrayAdapter right_num = new ArrayAdapter( dialog_error.getContext(),android.R.layout.simple_spinner_item, nums);
                                                ArrayAdapter left_error = new ArrayAdapter( dialog_error.getContext(),android.R.layout.simple_spinner_item, isError);
                                                ArrayAdapter right_error = new ArrayAdapter( dialog_error.getContext(),android.R.layout.simple_spinner_item, isError);
                                                left_num.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                right_num.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                left_error.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                right_error.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                spinner_left.setAdapter(left_num);
                                                spinner_right.setAdapter(right_num);
                                                spinner_left_e.setAdapter(left_error);
                                                spinner_right_e.setAdapter(right_error);
                                                dialog_error.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Toast.makeText(newRecordActivity,""+pos_left+pos_right,Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                                //左選單1~9
                                                spinner_left.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        if(view!=null)
                                                        {
                                                            pos_left=position+1;
                                                        }

                                                    }
                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {
                                                    }
                                                });
                                               //左選單E
                                                spinner_left_e.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        if(view!=null)
                                                        {
                                                            pos_erroe_l=position;
                                                        }

                                                    }
                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {
                                                    }
                                                });
                                                //選單1~9
                                                spinner_right.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        if(view!=null)
                                                        {
                                                            pos_right=position+1;

                                                        }

                                                    }
                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });
                                                //
                                                spinner_right_e.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        if(view!=null)
                                                        {
                                                            pos_erroe_l=position;
                                                        }

                                                    }
                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {
                                                    }
                                                });
                                                dialog_error.show();

                                            }
                                        });
                                        //點擊無
                                        view_push_choice1.findViewById(R.id.click_null).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(newRecordActivity, "無", Toast.LENGTH_SHORT).show();
                                                new_push_dialog.dismiss();
                                            }
                                        });
                                        new_push_dialog.show();
                                    }
                                }).show();


                        //趁傳
                       /* pos_left=0;
                        pos_right=0;
                        View view_throwTo = LayoutInflater.from(newRecordActivity).inflate(R.layout.record_throw_dialog, null);
                        dialog_throw = new AlertDialog.Builder(newRecordActivity);
                        dialog_throw.setView(view_throwTo);
                        spinner_left = view_throwTo.findViewById(R.id.spinner_left);
                        spinner_right=view_throwTo.findViewById(R.id.spinner_right);
                        ArrayAdapter throw_left = new ArrayAdapter(dialog_throw.getContext(),android.R.layout.simple_spinner_item,numList_left);
                        ArrayAdapter throw_right = new ArrayAdapter(dialog_throw.getContext(),android.R.layout.simple_spinner_item,numList_right);
                        throw_left.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        throw_right.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_left.setAdapter(throw_left);
                        spinner_right.setAdapter(throw_left);
                        dialog_throw.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(newRecordActivity,""+pos_left+pos_right,Toast.LENGTH_SHORT).show();
                                // TODO 二三本壘->推進->取值(pos_left)-(pos_right)
                            }
                        });

                        spinner_left.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(view!=null)
                                {
                                    pos_left=position+1;
                                }

                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });

                        spinner_right.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(view!=null)
                                {
                                    pos_right=position+1;

                                }

                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        dialog_throw.show();*/
                        break;
                    //點選進壘
                    case 1:
                        break;
                    default:
                        break;
                }
                Toast.makeText(newRecordActivity, "您選擇的是"+items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();

    }

    /*public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(newRecordActivity);
        //自定义dialog的布局
        View view = View.inflate(this, R.layout.item_dialog, null);
        //设置View
        builder.setView(view);
        //根据builder设置好的一系列数据, 来构建一个dialog对象
        final AlertDialog dialog = builder.create();

        view.findViewById(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了删除", Toast.LENGTH_SHORT).show();
                //弹窗消失
                dialog.dismiss();
            }
        });

        view.findViewById(R.id.tv_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了置顶", Toast.LENGTH_SHORT).show();
                //弹窗消失
                dialog.dismiss();
            }
        });
        //builder.show();
        dialog.show();
    }*/

    public void setNewRecordActivity(NewRecordActivity newRecordActivity) {
        this.newRecordActivity = newRecordActivity;
    }

}