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
    private List<String>  numList_left, numList_right;
    private Spinner spinner_left,spinner_right;
    AlertDialog.Builder dialog_throw;
    AlertDialog dialog1;


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
                dialog.dismiss();
                switch (which){
                    //點選推進
                    case 0:
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

                            }
                        });

                        spinner_left.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(view!=null)
                                {

                                }

                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                // TODO Auto-generated method stub

                            }
                        });

                        spinner_right.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(view!=null)
                                {

                                }

                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                // TODO Auto-generated method stub

                            }
                        });
                        dialog_throw.show();
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