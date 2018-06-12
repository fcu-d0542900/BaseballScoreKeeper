package com.example.yuru.baseballscorekeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void fn_player(View view) {
        Intent intent = new Intent(MainActivity.this,PlayerActivity.class);
        intent.putExtra("n",1);
        startActivityForResult(intent,111);

    }
    public void fn_record(View view) {
        Intent intent = new Intent(MainActivity.this,RecordActivity.class);
        intent.putExtra("n",1);
        startActivityForResult(intent,111);
    }
}
