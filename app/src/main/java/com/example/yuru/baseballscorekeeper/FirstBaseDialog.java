package com.example.yuru.baseballscorekeeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.baseball.DatabaseService;
import com.baseball.Player;
import com.baseball.RecordItemFirstBase;

import java.util.List;

/**
 * Created by YURU on 2018/6/16.
 */

public class FirstBaseDialog {

    private RecordItemFirstBase recordItemFirstBase;

    private NewRecordActivity newRecordActivity;

    //高飛犧牲
    private RadioGroup radioGroup_sacrificeFly_type,radioGroup_sacrificeFly_direction;
    private RadioButton radioButton_sacrificeFly_type_high,radioButton_sacrificeFly_type_line;
    private RadioButton radioButton_sacrificeFly_direction_7,radioButton_sacrificeFly_direction_8,radioButton_sacrificeFly_direction_9;

    //犧牲觸擊
    private Spinner spinner_actionOne,spinner_actionTwo;

    //一般
    private RadioGroup radioGroup_type;
    private RadioButton radioButton_type_high,radioButton_type_line,radioButton_type_ground;
    private Spinner spinner_direction;
    private CheckBox checkBox_elseAc_FC,checkBox_elseAc_u,checkBox_elseAc_E,checkBox_elseAc_T;


    private String[] nums = {"1","2","3","4","5","6","7","8","9"};



    public void setNewRecordActivity(NewRecordActivity newRecordActivity) {
        this.newRecordActivity = newRecordActivity;
    }

    public void setFirstBaseDialog() {
        recordItemFirstBase = new RecordItemFirstBase();

        AlertDialog.Builder builder = new AlertDialog.Builder(newRecordActivity);
        builder.setItems(new String[]{"擊出球","未擊出球"}, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which) {
                switch (which) {
                    case 0:
                        AlertDialog.Builder hit_builder = new AlertDialog.Builder(newRecordActivity);
                        hit_builder.setItems(new String[]{"高飛犧牲打", "觸擊犧牲打", "一般"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i) {
                                    case 0:
                                        Toast.makeText(newRecordActivity, "高飛犧牲打", Toast.LENGTH_SHORT).show();

                                        AlertDialog.Builder scrifice_fly_builder = new AlertDialog.Builder(newRecordActivity);
                                        View view_scrifice_fly_dialog = LayoutInflater.from(newRecordActivity).inflate(R.layout.record_sacrifice_fly, null);
                                        radioGroup_sacrificeFly_type = view_scrifice_fly_dialog.findViewById(R.id.radioGroup_sacrificeFly_type);
                                        radioButton_sacrificeFly_type_high = view_scrifice_fly_dialog.findViewById(R.id.radioButton_sacrificeFly_high);
                                        radioButton_sacrificeFly_type_line = view_scrifice_fly_dialog.findViewById(R.id.radioButton_sacrificeFly_line);
                                        radioGroup_sacrificeFly_direction = view_scrifice_fly_dialog.findViewById(R.id.radioGroup_sacrificeFly_direction);
                                        radioButton_sacrificeFly_direction_7 = view_scrifice_fly_dialog.findViewById(R.id.radioButton_sacrificeFly_direction_7);
                                        radioButton_sacrificeFly_direction_8 = view_scrifice_fly_dialog.findViewById(R.id.radioButton_sacrificeFly_direction_8);
                                        radioButton_sacrificeFly_direction_9= view_scrifice_fly_dialog.findViewById(R.id.radioButton_sacrificeFly_direction_9);

                                        view_scrifice_fly_dialog.setPadding(10,10,10,10);
                                        scrifice_fly_builder.setView(view_scrifice_fly_dialog);
                                        scrifice_fly_builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //設定顯示高飛犧牲打格子
                                                recordItemFirstBase.setShowSacrificeFlyVisibility(true);

                                                int select_sacrificeFly_typeID = radioGroup_sacrificeFly_type.getCheckedRadioButtonId();
                                                int select_sacrificeFly_directionID = radioGroup_sacrificeFly_direction.getCheckedRadioButtonId();
                                                String select_sacrificeFly_type_str;
                                                String select_sacrificeFly_direction_str;
                                                if(radioButton_sacrificeFly_type_high.getId() == select_sacrificeFly_typeID) {
                                                    select_sacrificeFly_type_str = "高飛球";
                                                    //TODO ahkui  存入資料庫， 顯示圖片高飛球  (R.drawable.fly_ball)

                                                }
                                                else if(radioButton_sacrificeFly_type_line.getId() == select_sacrificeFly_typeID) {
                                                    select_sacrificeFly_type_str = "平飛球";
                                                    //TODO ahkui  存入資料庫， 顯示圖片平飛球  (R.drawable.line_drive)

                                                }
                                                else {
                                                    select_sacrificeFly_type_str = "未選擇";
                                                }

                                                if(radioButton_sacrificeFly_direction_7.getId() == select_sacrificeFly_directionID) {
                                                    select_sacrificeFly_direction_str = "左外野";
                                                    //TODO ahkui  存入資料庫， 顯示圖片 7  (R.drawable.throw7)

                                                }
                                                else if(radioButton_sacrificeFly_direction_8.getId() == select_sacrificeFly_directionID) {
                                                    select_sacrificeFly_direction_str = "中外野";
                                                    //TODO ahkui  存入資料庫， 顯示圖片 8  (R.drawable.throw8)
                                                }
                                                else if(radioButton_sacrificeFly_direction_9.getId() == select_sacrificeFly_directionID) {
                                                    select_sacrificeFly_direction_str = "右外野";
                                                    //TODO ahkui  存入資料庫， 顯示圖片 9  (R.drawable.throw9)
                                                }
                                                else {
                                                    select_sacrificeFly_direction_str = "未選擇";
                                                }
                                                Toast.makeText(newRecordActivity.getApplicationContext(), "OK "+select_sacrificeFly_type_str + "," + select_sacrificeFly_direction_str, Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        scrifice_fly_builder.show();
                                        break;
                                    case 1:
                                        Toast.makeText(newRecordActivity, "觸擊犧牲打", Toast.LENGTH_SHORT).show();

                                        AlertDialog.Builder scrifice_hits_builder = new AlertDialog.Builder(newRecordActivity);
                                        View view_scrifice_hits_dialog = LayoutInflater.from(newRecordActivity).inflate(R.layout.record_sacrifice_hiits, null);
                                        spinner_actionOne = view_scrifice_hits_dialog.findViewById(R.id.spinner_actionOne);
                                        spinner_actionTwo = view_scrifice_hits_dialog.findViewById(R.id.spinner_actionTwo);

                                        view_scrifice_hits_dialog.setPadding(10,20,10,10);
                                        scrifice_hits_builder.setView(view_scrifice_hits_dialog);
                                        ArrayAdapter adapter_actionOne = new ArrayAdapter(scrifice_hits_builder.getContext(),android.R.layout.simple_spinner_item,nums);
                                        ArrayAdapter adapter_actionTwo = new ArrayAdapter(scrifice_hits_builder.getContext(),android.R.layout.simple_spinner_item,nums);
                                        adapter_actionOne.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        adapter_actionTwo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        spinner_actionOne.setAdapter(adapter_actionOne);
                                        spinner_actionTwo.setAdapter(adapter_actionTwo);

                                        scrifice_hits_builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                int actionOne = (int) spinner_actionOne.getSelectedItemId();
                                                int actionTwo = (int) spinner_actionTwo.getSelectedItemId();
                                                Toast.makeText(newRecordActivity, "OK " + (actionOne+1) + "," + (actionTwo+1), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        scrifice_hits_builder.show();

                                        break;
                                    case 2:
                                        //一般
                                        Toast.makeText(newRecordActivity, "其他", Toast.LENGTH_SHORT).show();

                                        AlertDialog.Builder first_one_builder = new AlertDialog.Builder(newRecordActivity);
                                        View view_first_one_dialog = LayoutInflater.from(newRecordActivity).inflate(R.layout.record_first_one, null);
                                        view_first_one_dialog.setPadding(10,20,10,10);
                                        first_one_builder.setView(view_first_one_dialog);
                                        radioGroup_type = view_first_one_dialog.findViewById(R.id.radioGroup_type);
                                        radioButton_type_high = view_first_one_dialog.findViewById(R.id.radioButton_type_high);
                                        radioButton_type_line = view_first_one_dialog.findViewById(R.id.radioButton_type_line);
                                        radioButton_type_ground = view_first_one_dialog.findViewById(R.id.radioButton_type_ground);
                                        spinner_direction = view_first_one_dialog.findViewById(R.id.spinner_direction);
                                        checkBox_elseAc_FC = view_first_one_dialog.findViewById(R.id.checkBox_elseAc_FC);
                                        checkBox_elseAc_u = view_first_one_dialog.findViewById(R.id.checkBox_elseAc_u);
                                        checkBox_elseAc_E = view_first_one_dialog.findViewById(R.id.checkBox_elseAc_E);
                                        checkBox_elseAc_T = view_first_one_dialog.findViewById(R.id.checkBox_elseAc_T);

                                        ArrayAdapter adapter_position = new ArrayAdapter(first_one_builder.getContext(),android.R.layout.simple_spinner_item,nums);
                                        adapter_position.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        spinner_direction.setAdapter(adapter_position);

                                        first_one_builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                int select_typeID = radioGroup_type.getCheckedRadioButtonId();
                                                String select_type_str;
                                                int select_direction = (int)spinner_direction.getSelectedItemId(); //TODO ahkui  存入資料庫， 顯示圖片 1~9
                                                
                                                String select_elseAc = "";

                                                if(radioButton_type_high.getId() == select_typeID) {
                                                    select_type_str = "高飛球";
                                                    //TODO ahkui  存入資料庫， 顯示圖片高飛球  (R.drawable.fly_ball)

                                                }
                                                else if(radioButton_type_line.getId() == select_typeID) {
                                                    select_type_str = "平飛球";
                                                    //TODO ahkui  存入資料庫， 顯示圖片平飛球  (R.drawable.line_drive)

                                                }
                                                else if(radioButton_type_ground.getId() == select_typeID) {
                                                    select_type_str = "滾地球";
                                                    //TODO ahkui  存入資料庫， 顯示圖片滾地球  (R.drawable.ground_ball)

                                                }
                                                else {
                                                    select_type_str = "未選擇";
                                                }

                                                if(checkBox_elseAc_FC.isChecked()) {
                                                    select_elseAc = select_elseAc + "," + checkBox_elseAc_FC.getText().toString();
                                                    //TODO ahkui  存入資料庫， 顯示圖片FC  (R.drawable.fielder_choice)

                                                }
                                                if(checkBox_elseAc_u.isChecked()) {
                                                    select_elseAc = select_elseAc + "," + checkBox_elseAc_u.getText().toString();
                                                    //TODO ahkui  存入資料庫， 顯示圖片u  (R.drawable.u)
                                                }
                                                if(checkBox_elseAc_E.isChecked()) {
                                                    select_elseAc = select_elseAc + "," + checkBox_elseAc_E.getText().toString();
                                                    //TODO ahkui  存入資料庫， 顯示圖片E  (R.drawable.error)
                                                }
                                                if(checkBox_elseAc_T.isChecked()) {
                                                    select_elseAc = select_elseAc + "," + checkBox_elseAc_T.getText().toString();
                                                    //TODO ahkui  存入資料庫， 顯示圖片T  (R.drawable.tag)
                                                }

                                                Toast.makeText(newRecordActivity, "OK "+select_type_str+","+(select_direction+1) +select_elseAc, Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        first_one_builder.show();

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
                                recordItemFirstBase.setShowZeroViewVisibility(true);
                                //TODO: ahkui 存入資料庫， 顯示圖片 B   (R.drawable.bad_ball)

                            }
                        });
                        view_unhit_dialog.findViewById(R.id.click_db).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(newRecordActivity, "觸身", Toast.LENGTH_SHORT).show();
                                unhit_dialog.dismiss();
                                recordItemFirstBase.setShowZeroViewVisibility(true);
                                //TODO:ahkui 存入資料庫， 顯示圖片 D  (R.drawable.hit_by_pitch)

                            }
                        });
                        view_unhit_dialog.findViewById(R.id.click_k).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(newRecordActivity, "三振", Toast.LENGTH_SHORT).show();
                                unhit_dialog.dismiss();
                                recordItemFirstBase.setShowZeroViewVisibility(true);
                                //TODO:ahkui 存入資料庫， 顯示圖片 K  (R.drawable.killed)
                            }
                        });
                        view_unhit_dialog.findViewById(R.id.click_nok).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(newRecordActivity, "不死三振", Toast.LENGTH_SHORT).show();
                                unhit_dialog.dismiss();
                                RecordItemFirstBase recordItemFirstBase = new RecordItemFirstBase();
                                recordItemFirstBase.setShowZeroViewVisibility(true);
                                //TODO:ahkui 存入資料庫， 顯示圖片 倒K  (R.drawable.no_killed)

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
