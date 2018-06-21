package com.example.yuru.baseballscorekeeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.yuru.baseballscorekeeper.Adapter.RecordAdapter;
import com.example.yuru.baseballscorekeeper.Adapter.ScoreScrollablePanelAdapter;
import com.example.yuru.baseballscorekeeper.Adapter.ScrollablePanelAdapter;
import com.example.yuru.baseballscorekeeper.Modal.DatabaseService;
import com.example.yuru.baseballscorekeeper.Modal.Record;
import com.example.yuru.baseballscorekeeper.Modal.RecordItem;
import com.example.yuru.baseballscorekeeper.Modal.RecordTeam;

import java.util.ArrayList;
import java.util.List;

public class NewRecordActivity extends AppCompatActivity {

    private TextView text_gameName,text_startTime,text_endTime;
    public Record currentRecord;
    public ScrollablePanel scrollablePanel;
    public ScrollablePanelAdapter scrollablePanelAdapter;
    public ScrollablePanel score_scrollable_panel;
    public ScoreScrollablePanelAdapter score_scrollablePanelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);

        Intent intent = this.getIntent();
        currentRecord = DatabaseService.getInstance().getDatabase().getRecord(intent.getIntExtra("recordPosition",0));
        text_gameName = findViewById(R.id.text_gameName);
        text_gameName.setText(currentRecord.getGameName());
        DatabaseService.CurrentRecord = intent.getIntExtra("recordPosition",0);
        Log.d("ahkui","Record id: "+intent.getIntExtra("recordPosition",0)+"");

        //設定頁面
        scrollablePanel = findViewById(R.id.scrollable_panel);
        scrollablePanelAdapter = new ScrollablePanelAdapter(NewRecordActivity.this);
        score_scrollable_panel = findViewById(R.id.score_scrollable_panel);
        score_scrollablePanelAdapter = new ScoreScrollablePanelAdapter(NewRecordActivity.this);
        scrollablePanel.setPanelAdapter(scrollablePanelAdapter);
        score_scrollable_panel.setPanelAdapter(score_scrollablePanelAdapter);

        //點擊隊伍切換紀錄頁面
        score_scrollablePanelAdapter.setOnItemClickListener(new RecordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if(position==1){  //點擊away
                    Toast.makeText(NewRecordActivity.this,"點擊away"+position,Toast.LENGTH_LONG).show();
                    currentRecord.setCurrenFaction(RecordTeam.Faction.away);     //將目前隊伍切換away
                    DatabaseService.getInstance().getDatabase().getRecord(DatabaseService.CurrentRecord).setCurrenFaction(RecordTeam.Faction.away);
                    scrollablePanelAdapter.updateData();   //顯示頁面
                    score_scrollablePanelAdapter.updateData();
                }
                else if(position==2)  //點擊home/
                {
                    Toast.makeText(NewRecordActivity.this,"點擊home"+position,Toast.LENGTH_LONG).show();
                    currentRecord.setCurrenFaction(RecordTeam.Faction.home);     //將目前隊伍切換homw
                    DatabaseService.getInstance().getDatabase().getRecord(DatabaseService.CurrentRecord).setCurrenFaction(RecordTeam.Faction.home);
                    scrollablePanelAdapter.updateData();
                    score_scrollablePanelAdapter.updateData();
                }
            }
        });

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

    public List<List<RecordItem>> getUpdateData() {
        List<List<RecordItem>> recordItems = new ArrayList<>();
        recordItems.clear();
        int col = currentRecord.getTeam().getLastRecordItemsColumn() + 1+3;
        for (int i = 0; i < 9; i++) {
            List<RecordItem> tmp = new ArrayList<>();
            for (int j = 0; j <= col; j++) {
                tmp.add(currentRecord.getTeam().getRecordItems(i,j));
            }
            recordItems.add(tmp);
        }
        return recordItems;
    }

    public List<List<String>> getUpdateScore() {
        List<List<String>> data = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        for (int j = 0;j<9;j++){
            tmp.add(currentRecord.getAwayTeam().getScore(j));
        }
        data.add(tmp);
        List<String> tmp2 = new ArrayList<>();
        for (int j = 0;j<9;j++){
            tmp2.add(currentRecord.getHomeTeam().getScore(j));
        }
        data.add(tmp2);
        return data;
    }
}


