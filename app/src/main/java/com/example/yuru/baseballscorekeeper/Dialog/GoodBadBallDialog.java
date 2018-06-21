package com.example.yuru.baseballscorekeeper.Dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yuru.baseballscorekeeper.Modal.RecordItem;
import com.example.yuru.baseballscorekeeper.NewRecordActivity;
import com.example.yuru.baseballscorekeeper.R;
import com.example.yuru.baseballscorekeeper.Adapter.ScrollablePanelAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by User on 2018/6/17.
 */

public class GoodBadBallDialog {

    private NewRecordActivity activity;
    private Spinner spinner;

    public void setActivity(NewRecordActivity activity) {
        this.activity = activity;
    }

    public void setGoodBadBallDialog(final ScrollablePanelAdapter.OrderViewHolder viewHolder) {
        new AlertDialog.Builder(activity)
                .setTitle("新增/修改好壞球")
                .setItems(new String[]{"新增", "修改"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        switch (which) {
                            case 0:   //點選新增
                                setGoodbadballChoiceDialog(viewHolder);
                                break;
                            case 1:  //點選修改
                                if(viewHolder.recordItem.getPitchBall().size() > 0){
                                    setChangeGoodbadballeDialog(viewHolder);
                                }
                                else
                                    Toast.makeText(activity, "無欄位可以修改", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                }).show();

    }

    public void setGoodbadballChoiceDialog(final ScrollablePanelAdapter.OrderViewHolder viewHolder) {

        AlertDialog.Builder goodbadball_dialog = new AlertDialog.Builder(activity);
        View view_goodbadball_choice = View.inflate(activity, R.layout.record_goodbadball_dialog, null);      //自訂dialog布局
        view_goodbadball_choice.setPadding(10,10,10,10);
        goodbadball_dialog.setView(view_goodbadball_choice);   // 設置view
        final AlertDialog new_goodbadball_dialog = goodbadball_dialog.create();    //根據builder設置好的一系列數據, 来建構一個dialog

        //點擊好球
        view_goodbadball_choice.findViewById(R.id.click_strike).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "好球", Toast.LENGTH_SHORT).show();
                viewHolder.recordItem.addPitchBall(RecordItem.PITCH_BALL_TYPE.STRIKE);
                viewHolder.updateUI(activity);
                new_goodbadball_dialog.dismiss();
            }
        });

        //點擊好球(揮空)
        view_goodbadball_choice.findViewById(R.id.click_miss_strike).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "好球(揮空)", Toast.LENGTH_SHORT).show();
                viewHolder.recordItem.addPitchBall(RecordItem.PITCH_BALL_TYPE.MISS_STRIKE);
                viewHolder.updateUI(activity);
                new_goodbadball_dialog.dismiss();
            }
        });

        //點擊壞球
        view_goodbadball_choice.findViewById(R.id.click_bad_ball).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "壞球", Toast.LENGTH_SHORT).show();
                viewHolder.recordItem.addPitchBall(RecordItem.PITCH_BALL_TYPE.BALL);
                viewHolder.updateUI(activity);
                new_goodbadball_dialog.dismiss();
            }
        });

        //點擊界外球
        view_goodbadball_choice.findViewById(R.id.click_foul_ball).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "界外球", Toast.LENGTH_SHORT).show();
                viewHolder.recordItem.addPitchBall(RecordItem.PITCH_BALL_TYPE.FOUL_BALL);
                viewHolder.updateUI(activity);
                new_goodbadball_dialog.dismiss();
            }
        });

        //點擊觸擊界外
        view_goodbadball_choice.findViewById(R.id.click_bunt_foul).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "觸擊界外", Toast.LENGTH_SHORT).show();
                viewHolder.recordItem.addPitchBall(RecordItem.PITCH_BALL_TYPE.BUNT_FOUL);
                viewHolder.updateUI(activity);
                new_goodbadball_dialog.dismiss();
            }
        });

        //點擊擊出球
        view_goodbadball_choice.findViewById(R.id.click_hit_ball).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "擊出球", Toast.LENGTH_SHORT).show();
                viewHolder.recordItem.addPitchBall(RecordItem.PITCH_BALL_TYPE.HIT);
                viewHolder.updateUI(activity);
                new_goodbadball_dialog.dismiss();
            }
        });
        new_goodbadball_dialog.show();
    }

    private int currentPitchBallSelect;

    public void setChangeGoodbadballeDialog(final ScrollablePanelAdapter.OrderViewHolder viewHolder) {

        ArrayList numsList = new ArrayList();
        for(int i=0;i<viewHolder.recordItem.getPitchBall().size();i++)
        {
            numsList.add(i+1);
        }
        View view_change_goodbadball = LayoutInflater.from(activity).inflate(R.layout.record_change_goodbadball_dialog, null);
        AlertDialog.Builder dialog_change_goodbadball = new AlertDialog.Builder(activity);
        dialog_change_goodbadball.setView(view_change_goodbadball);
        spinner = view_change_goodbadball.findViewById(R.id.spinner);
        ArrayAdapter change = new ArrayAdapter( dialog_change_goodbadball.getContext(),android.R.layout.simple_spinner_item, numsList);
        change.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(change);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (view != null) {
                    currentPitchBallSelect = position;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentPitchBallSelect = -1;
            }

        });
        dialog_change_goodbadball.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {

                AlertDialog.Builder goodbadball_dialog = new AlertDialog.Builder(activity);
                View view_goodbadball_choice = View.inflate(activity, R.layout.record_goodbadball_dialog, null);      //自訂dialog布局
                view_goodbadball_choice.setPadding(10,10,10,10);
                goodbadball_dialog.setView(view_goodbadball_choice);   // 設置view
                final AlertDialog new_goodbadball_dialog = goodbadball_dialog.create();    //根據builder設置好的一系列數據, 来建構一個dialog

                //點擊好球
                view_goodbadball_choice.findViewById(R.id.click_strike).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, "好球", Toast.LENGTH_SHORT).show();
                        viewHolder.recordItem.updatePitchBall(currentPitchBallSelect,RecordItem.PITCH_BALL_TYPE.STRIKE);
                        viewHolder.updateUI(activity);
                        new_goodbadball_dialog.dismiss();
                    }
                });

                //點擊好球(揮空)
                view_goodbadball_choice.findViewById(R.id.click_miss_strike).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, "好球(揮空)", Toast.LENGTH_SHORT).show();
                        viewHolder.recordItem.updatePitchBall(currentPitchBallSelect,RecordItem.PITCH_BALL_TYPE.MISS_STRIKE);
                        viewHolder.updateUI(activity);
                        new_goodbadball_dialog.dismiss();
                    }
                });

                //點擊壞球
                view_goodbadball_choice.findViewById(R.id.click_bad_ball).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, "壞球", Toast.LENGTH_SHORT).show();
                        viewHolder.recordItem.updatePitchBall(currentPitchBallSelect,RecordItem.PITCH_BALL_TYPE.BALL);
                        viewHolder.updateUI(activity);
                        new_goodbadball_dialog.dismiss();
                    }
                });

                //點擊界外球
                view_goodbadball_choice.findViewById(R.id.click_foul_ball).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, "界外球", Toast.LENGTH_SHORT).show();
                        viewHolder.recordItem.updatePitchBall(currentPitchBallSelect,RecordItem.PITCH_BALL_TYPE.FOUL_BALL);
                        viewHolder.updateUI(activity);
                        new_goodbadball_dialog.dismiss();
                    }
                });

                //點擊觸擊界外
                view_goodbadball_choice.findViewById(R.id.click_bunt_foul).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, "觸擊界外", Toast.LENGTH_SHORT).show();
                        viewHolder.recordItem.updatePitchBall(currentPitchBallSelect,RecordItem.PITCH_BALL_TYPE.BUNT_FOUL);
                        viewHolder.updateUI(activity);
                        new_goodbadball_dialog.dismiss();
                    }
                });

                //點擊擊出球
                view_goodbadball_choice.findViewById(R.id.click_hit_ball).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, "擊出球", Toast.LENGTH_SHORT).show();
                        viewHolder.recordItem.updatePitchBall(currentPitchBallSelect,RecordItem.PITCH_BALL_TYPE.HIT);
                        viewHolder.updateUI(activity);
                        new_goodbadball_dialog.dismiss();
                    }
                });
                new_goodbadball_dialog.show();
            }
        });
        dialog_change_goodbadball.show();
    }
}


