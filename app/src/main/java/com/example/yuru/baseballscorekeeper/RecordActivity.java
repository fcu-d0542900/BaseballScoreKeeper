package com.example.yuru.baseballscorekeeper;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;


public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
    }


    public void clickAddRecord(View view) {
        final View item = LayoutInflater.from(RecordActivity.this).inflate(R.layout.dialog_new_record, null);
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setView(item);
        adb.show();
    }
}
