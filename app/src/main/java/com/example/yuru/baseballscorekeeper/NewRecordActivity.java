package com.example.yuru.baseballscorekeeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.baseball.DatabaseService;
import com.baseball.Record;
import com.baseball.RecordItem;

import java.util.ArrayList;
import java.util.List;

public class NewRecordActivity extends AppCompatActivity {

    private TextView text_gameName,text_startTime,text_endTime;
    Record currentRecord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);

        Intent intent = this.getIntent();
        currentRecord = DatabaseService.getInstance().getDatabase().getRecord(intent.getIntExtra("recordPosition",0));
        text_gameName = findViewById(R.id.text_gameName);
        text_gameName.setText(currentRecord.getGameName());


        final ScrollablePanel scrollablePanel = findViewById(R.id.scrollable_panel);
        final ScrollablePanelAdapter scrollablePanelAdapter = new ScrollablePanelAdapter(NewRecordActivity.this);

        final ScrollablePanel score_scrollable_panel = findViewById(R.id.score_scrollable_panel);
        final ScoreScrollablePanelAdapter score_scrollablePanelAdapter = new ScoreScrollablePanelAdapter(NewRecordActivity.this);
//        generateTestData(scrollablePanelAdapter);
        scrollablePanel.setPanelAdapter(scrollablePanelAdapter);
        score_scrollable_panel.setPanelAdapter(score_scrollablePanelAdapter);

    }

    public void fn_startTime(View view) {
        text_startTime = findViewById(R.id.text_startTime);

        AlertDialog.Builder dialog_startTime = new AlertDialog.Builder(this);
        final TimePicker timePicker_startTime=new TimePicker(NewRecordActivity.this);
        dialog_startTime.setView(timePicker_startTime);
        dialog_startTime.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String str_gameDate = timePicker_startTime.getHour() + ":" + timePicker_startTime.getMinute();
                text_startTime.setText(str_gameDate);
            }
        });
        dialog_startTime.show();
    }

    public void fn_endTime(View view) {
        text_endTime = findViewById(R.id.text_endTime);

        AlertDialog.Builder dialog_endTime = new AlertDialog.Builder(this);
        final TimePicker timePicker_endTime=new TimePicker(NewRecordActivity.this);
        dialog_endTime.setView(timePicker_endTime);
        dialog_endTime.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String str_gameDate = timePicker_endTime.getHour() + ":" + timePicker_endTime.getMinute();
                text_endTime.setText(str_gameDate);
            }
        });
        dialog_endTime.show();
    }

    public void updateData(List<List<RecordItem>> recordItems) {
        recordItems.clear();
        int col = currentRecord.getTeam().getLastRecordItemsColumn() + 1+3;
        for (int i = 0; i < 9; i++) {
            List<RecordItem> tmp = new ArrayList<>();
            for (int j = 0; j <= col; j++) {
                tmp.add(currentRecord.getTeam().getRecordItems(i,j));
            }
            recordItems.add(tmp);
        }
    }
}


