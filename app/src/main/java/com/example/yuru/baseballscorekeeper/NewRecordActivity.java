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

import com.baseball.BoardNumInfo;
import com.baseball.OrderInfo;
import com.baseball.Player;
import com.baseball.ScoreBoardInfo;
import com.baseball.Team;

import java.util.ArrayList;
import java.util.List;

public class NewRecordActivity extends AppCompatActivity {

    private TextView text_gameName,text_startTime,text_endTime;
    private Boolean isSetPlayer;
    private String awayTeam,homeTeam;


    private View view_set_player;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);

        Intent intent = this.getIntent();
        awayTeam = intent.getStringExtra("awayTeam");
        homeTeam = intent.getStringExtra("homeTeam");
        isSetPlayer = intent.getBooleanExtra("isSetPlayer",false);
        text_gameName = findViewById(R.id.text_gameName);
        text_gameName.setText(intent.getStringExtra("gameName"));


        final ScrollablePanel scrollablePanel = (ScrollablePanel) findViewById(R.id.scrollable_panel);
        final ScrollablePanelAdapter scrollablePanelAdapter = new ScrollablePanelAdapter();

        final ScrollablePanel score_scrollable_panel = (ScrollablePanel) findViewById(R.id.score_scrollable_panel);
        final ScoreScrollablePanelAdapter score_scrollablePanelAdapter = new ScoreScrollablePanelAdapter();
        Log.d("run", "跑到此行");

        generateTestData(scrollablePanelAdapter);
        generateScoreData(score_scrollablePanelAdapter);
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




    private void generateTestData(ScrollablePanelAdapter scrollablePanelAdapter) {

        if(isSetPlayer == true) {
            //拿資料庫球員資料
            Toast.makeText(getApplicationContext(),"isSetPlayer", Toast.LENGTH_SHORT).show();
        }
        
        //設定球員資料
        List<Player>  playerInfoList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Player player = new Player();
            playerInfoList.add(player);  //預設為空
        }
        scrollablePanelAdapter.setNewRecordActivity(NewRecordActivity.this);
        scrollablePanelAdapter.setPlayerInfoList(playerInfoList);

        //設定局數資料
        List<BoardNumInfo> boardNumInfoList = new ArrayList<>() ;
        for (int i = 0; i < (9+1); i++) {
            boardNumInfoList.add(new BoardNumInfo(i));
        }
        scrollablePanelAdapter.setBoardNumInfoList(boardNumInfoList);


        //設定紀錄資料
        List<List<OrderInfo>> ordersList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            List<OrderInfo> orderInfoList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setGuestName("NO." + i + j);
                orderInfoList.add(orderInfo);
            }
            ordersList.add(orderInfoList);
        }
        scrollablePanelAdapter.setOrdersList(ordersList);


    }

   private void generateScoreData(ScoreScrollablePanelAdapter score_scrollablePanelAdapter){

        //設定局數資料
       List<BoardNumInfo> boardNumInfoList = new ArrayList<>();
       for (int i = 0; i < (9+2); i++) {
           boardNumInfoList.add(new BoardNumInfo(i));
       }
       score_scrollablePanelAdapter.setBoardNumInfoList(boardNumInfoList);

       //設定隊伍資料
        List<Team>  teamInfoList = new ArrayList<>();
        teamInfoList.add(new Team(awayTeam));
        teamInfoList.add(new Team(homeTeam));
        score_scrollablePanelAdapter.setTeamInfoList(teamInfoList);

       ///設定記分板資料
        List<List<ScoreBoardInfo>> scoreList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<ScoreBoardInfo> scoreInfoList = new ArrayList<>();
            for (int j = 0; j < 15; j++) {
                ScoreBoardInfo scoreInfo = new ScoreBoardInfo();
                scoreInfo.setScore(j);
                scoreInfoList.add(scoreInfo);
            }
            scoreList.add(scoreInfoList);
        }
        score_scrollablePanelAdapter.setScoreList(scoreList);

    }



}


