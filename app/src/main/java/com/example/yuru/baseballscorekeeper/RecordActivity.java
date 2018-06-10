package com.example.yuru.baseballscorekeeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class RecordActivity extends AppCompatActivity {

    private View item;
    private EditText editText_awayTeam,editText_homeTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        item = LayoutInflater.from(RecordActivity.this).inflate(R.layout.dialog_new_record, null);
        item.setPadding(3,0,3,0);


    }


    public void clickAddRecord(View view) {
        AlertDialog.Builder dialog_addRecord = new AlertDialog.Builder(this);
        editText_awayTeam = item.findViewById(R.id.editText_awayTeam);
        editText_homeTeam = item.findViewById(R.id.editText_homeTeam);

        //建立一個ArrayAdapter物件，並放置下拉選單的內容
        ArrayAdapter adapter_away = new ArrayAdapter(dialog_addRecord.getContext(),android.R.layout.simple_spinner_item,new String[]{"我的隊伍","自訂"});
        //設定下拉選單的樣式
        adapter_away.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter adapter_home = new ArrayAdapter(dialog_addRecord.getContext(),android.R.layout.simple_spinner_item,new String[]{"我的隊伍","自訂"});
        adapter_home.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner_away = (Spinner) item.findViewById(R.id.spinner_awayTeam);
        spinner_away.setAdapter(adapter_away);
        Spinner spinner_home = (Spinner) item.findViewById(R.id.spinner_homeTeam);
        spinner_home.setAdapter(adapter_home);

        spinner_away.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int position, long id) {
                if(adapterView.getSelectedItem().toString() == "自訂") {
                    editText_awayTeam.setVisibility(View.VISIBLE);
                }
                else {
                    editText_awayTeam.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView parentView) {
                Toast.makeText(RecordActivity.this, "您沒有選擇任何項目", Toast.LENGTH_SHORT).show();
            }
        });
        spinner_home.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int position, long id) {
                if(adapterView.getSelectedItem().toString() == "自訂") {
                    editText_homeTeam.setVisibility(View.VISIBLE);
                }
                else {
                    editText_homeTeam.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView parentView) {
                Toast.makeText(RecordActivity.this, "您沒有選擇任何項目", Toast.LENGTH_SHORT).show();
            }
        });

        dialog_addRecord.setView(item);
        dialog_addRecord.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
            }
        });
        dialog_addRecord.show();
    }

    public void fn_choose_date(View view) {
        AlertDialog.Builder dialog_chooseDate = new AlertDialog.Builder(this);
        final DatePicker datePicker_gameDate=new DatePicker(RecordActivity.this);
        dialog_chooseDate.setView(datePicker_gameDate);
        dialog_chooseDate.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String str_gameDate = datePicker_gameDate.getYear() + "/" + (datePicker_gameDate.getMonth() + 1) + "/" + datePicker_gameDate.getDayOfMonth();
                Toast.makeText(getApplicationContext(), str_gameDate, Toast.LENGTH_SHORT).show();
        }
        });
        dialog_chooseDate.show();
    }
}
