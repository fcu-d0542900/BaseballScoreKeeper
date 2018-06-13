package com.example.yuru.baseballscorekeeper;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.baseball.DatabaseService;
import com.baseball.Record;

public class MainActivity extends AppCompatActivity {

    private String myTeamName;
    private DatabaseService database = DatabaseService.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        database.setContext(MainActivity.this);
        test();
        Log.d("database",database.getDatabase().getTeamName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseService.getInstance().write();
    }

    public void fn_player(View view) {
        Intent intent = new Intent(MainActivity.this,PlayerActivity.class);
        intent.putExtra("n",1);
        startActivityForResult(intent,111);

    }
    public void fn_record(View view) {
        Intent intent = new Intent(MainActivity.this,RecordActivity.class);
        intent.putExtra("teamName",myTeamName);
        intent.putExtra("n",1);
        startActivityForResult(intent,111);
    }

    public void fn_setTeamName(View view) {

        AlertDialog.Builder dialog_setTeamName = new AlertDialog.Builder(this);
        dialog_setTeamName.setMessage("我的隊伍名稱:");
        final EditText editText_teamName = new EditText(MainActivity.this);
        dialog_setTeamName.setView(editText_teamName);
        dialog_setTeamName.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myTeamName = editText_teamName.getText().toString();
                database.getDatabase().setTeamName(myTeamName);
            }
        });
        dialog_setTeamName.show();

    }

    /* remove later */
    private void test(){
        DatabaseService.getInstance().getDatabase().addRecord(new Record("大專盃預賽","2018/6/8","a","b"));
        DatabaseService.getInstance().getDatabase().addRecord(new Record("人言盃","2018/6/10","a","b"));
        DatabaseService.getInstance().getDatabase().addRecord(new Record("大專盃複賽","2018/6/15","a","b"));

    }
}
