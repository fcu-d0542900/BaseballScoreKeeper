package com.example.yuru.baseballscorekeeper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yuru.baseballscorekeeper.Modal.DatabaseService;
import com.example.yuru.baseballscorekeeper.Modal.Record;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private String myTeamName;
    private DatabaseService database = DatabaseService.getInstance();

    private EditText editText_setTeamName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        database.setContext(MainActivity.this);
        Log.d("database",database.getDatabase().getTeamName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseService.getInstance().write();
    }

    //我的球員畫面
    public void fn_player(View view) {
        Intent intent = new Intent(MainActivity.this,PlayerActivity.class);
        intent.putExtra("n",1);
        startActivityForResult(intent,111);

    }

    //我的紀錄畫面
    public void fn_record(View view) {
        Intent intent = new Intent(MainActivity.this,RecordActivity.class);
        intent.putExtra("teamName",myTeamName);
        intent.putExtra("n",1);
        startActivityForResult(intent,111);
    }

    //點選棒球設定隊伍名稱
    public void fn_setTeamName(View view) {
        View view_setTeamName = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_set_team_name, null);
        view_setTeamName.setPadding(10,30,10,0);
        TextView text_setTeamName = view_setTeamName.findViewById(R.id.text_setTeamName);
        editText_setTeamName = view_setTeamName.findViewById(R.id.editText_setTeamName);
        if(database.getDatabase().getTeamName() != null) {
            text_setTeamName.setText(database.getDatabase().getTeamName());
        }

        AlertDialog.Builder dialog_setTeamName = new AlertDialog.Builder(this);
        dialog_setTeamName.setView(view_setTeamName);
        dialog_setTeamName.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            if(!editText_setTeamName.getText().toString().equals("")) {
                myTeamName = editText_setTeamName.getText().toString();
                database.getDatabase().setTeamName(myTeamName);
            }
            }
        });
        dialog_setTeamName.show();

    }

}
