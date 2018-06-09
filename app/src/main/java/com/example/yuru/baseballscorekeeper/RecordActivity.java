package com.example.yuru.baseballscorekeeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class RecordActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

    }


    public void clickAddRecord(View view) {
        final View item = LayoutInflater.from(RecordActivity.this).inflate(R.layout.dialog_new_record, null);
        item.setPadding(3,0,3,0);
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setView(item);
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
            }
        });
        adb.show();
    }

    public void fn_choose_date(View view) {
        AlertDialog.Builder adbc = new AlertDialog.Builder(this);
        final DatePicker datePicker_gameDate=new DatePicker(RecordActivity.this);
        adbc.setView(datePicker_gameDate);
        adbc.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String str_gameDate = datePicker_gameDate.getYear() + "/" + (datePicker_gameDate.getMonth() + 1) + "/" + datePicker_gameDate.getDayOfMonth();
                Toast.makeText(getApplicationContext(), str_gameDate, Toast.LENGTH_SHORT).show();
        }
        });
        adbc.show();
    }
}
