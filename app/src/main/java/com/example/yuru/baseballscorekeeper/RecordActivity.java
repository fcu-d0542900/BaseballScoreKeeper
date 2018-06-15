package com.example.yuru.baseballscorekeeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.baseball.Database;
import com.baseball.DatabaseService;
import com.baseball.Record;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class RecordActivity extends AppCompatActivity {

    String teamName;
    private RecyclerView item_list;
    private RecordAdapter itemAdapter;
    private RecyclerView.LayoutManager rLayoutManager;

    private View view_new_record;
    private EditText editText_gameName,editText_gameDate;
    private EditText editText_awayTeam,editText_homeTeam;
    private Spinner spinner_awayTeam,spinner_homeTeam;
    private CheckBox checkBox_isSetPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Intent intent = this.getIntent();
        teamName = intent.getStringExtra("teamName");

        item_list = (RecyclerView) findViewById(R.id.record_list);

        // 執行RecyclerView元件的設定
        item_list.setHasFixedSize(true);
        // 建立與設定RecyclerView元件的配置元件
        rLayoutManager = new LinearLayoutManager(this);
        item_list.setLayoutManager(rLayoutManager);

        // 建立RecyclerView元件的資料來源物件
        itemAdapter = new RecordAdapter(this) {
            @Override
            public void onBindViewHolder(final ViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
            }
        };

        // 設定RecyclerView使用的資料來源物件
        item_list.setAdapter(itemAdapter);

        // 建立與設定項目操作物件
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(itemAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(item_list);
    }


    public void clickAddRecord(View view) {

        view_new_record = LayoutInflater.from(RecordActivity.this).inflate(R.layout.dialog_new_record, null);
        view_new_record.setPadding(3,0,3,0);

        editText_gameName = view_new_record.findViewById(R.id.editText_gameName);
        editText_gameDate = view_new_record.findViewById(R.id.editText_gameDate);
        editText_awayTeam = view_new_record.findViewById(R.id.editText_awayTeam);
        editText_homeTeam = view_new_record.findViewById(R.id.editText_homeTeam);
        spinner_awayTeam = view_new_record.findViewById(R.id.spinner_awayTeam);
        spinner_homeTeam = view_new_record.findViewById(R.id.spinner_homeTeam);
        checkBox_isSetPlayer = view_new_record.findViewById(R.id.checkBox_isSetPlayer);

        AlertDialog.Builder dialog_addRecord = new AlertDialog.Builder(this);

        //建立一個ArrayAdapter物件，並放置下拉選單的內容
        ArrayAdapter adapter_away = new ArrayAdapter(dialog_addRecord.getContext(),android.R.layout.simple_spinner_item,new String[]{"我的隊伍","自訂"});
        //設定下拉選單的樣式
        adapter_away.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter adapter_home = new ArrayAdapter(dialog_addRecord.getContext(),android.R.layout.simple_spinner_item,new String[]{"我的隊伍","自訂"});
        adapter_home.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_awayTeam.setAdapter(adapter_away);
        spinner_homeTeam.setAdapter(adapter_home);

        spinner_awayTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int position, long id) {
                if(adapterView.getSelectedItem().toString() == "自訂") {
                    editText_awayTeam.setVisibility(View.VISIBLE);
                }
                else {

                    editText_awayTeam.setText(teamName);
                    editText_awayTeam.setVisibility(View.GONE);
                }
                Toast.makeText(RecordActivity.this," 您選擇"+adapterView.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView parentView) {
                Toast.makeText(RecordActivity.this, "您沒有選擇任何項目", Toast.LENGTH_SHORT).show();
            }
        });
        spinner_homeTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int position, long id) {
                if(adapterView.getSelectedItem().toString() == "自訂") {
                    editText_homeTeam.setVisibility(View.VISIBLE);
                }
                else {
                    editText_homeTeam.setText(teamName);
                    editText_homeTeam.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView parentView) {
                Toast.makeText(RecordActivity.this, "您沒有選擇任何項目", Toast.LENGTH_SHORT).show();
            }
        });

        dialog_addRecord.setView(view_new_record);
        dialog_addRecord.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();

                String gameName = editText_gameName.getText().toString();
                String awayTeam = editText_awayTeam.getText().toString();
                String homeTeam = editText_homeTeam.getText().toString();
                Boolean isSetPlayer = checkBox_isSetPlayer.isChecked();

                int position = DatabaseService.getInstance().getDatabase().addRecord(new Record(gameName, awayTeam,homeTeam,Calendar.getInstance().getTime()));
                itemAdapter.update();

                Intent intent = new Intent(RecordActivity.this,NewRecordActivity.class);
                intent.putExtra("recordPosition",position);
                intent.putExtra("gameName",gameName);
                intent.putExtra("awayTeam",awayTeam);
                intent.putExtra("homeTeam",homeTeam);
                intent.putExtra("isSetPlayer",isSetPlayer);
                intent.putExtra("n",1);
                startActivityForResult(intent,111);

            }
        });
        dialog_addRecord.show();
    }

    public void fn_choose_date(View view) {
        editText_gameDate = view_new_record.findViewById(R.id.editText_gameDate);

        AlertDialog.Builder dialog_chooseDate = new AlertDialog.Builder(this);
        final DatePicker datePicker_gameDate=new DatePicker(RecordActivity.this);
        dialog_chooseDate.setView(datePicker_gameDate);
        dialog_chooseDate.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String str_gameDate = datePicker_gameDate.getYear() + "/" + (datePicker_gameDate.getMonth() + 1) + "/" + datePicker_gameDate.getDayOfMonth();
                editText_gameDate.setText(str_gameDate);
        }
        });
        dialog_chooseDate.show();
    }


}
