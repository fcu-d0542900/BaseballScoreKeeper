package com.example.yuru.baseballscorekeeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.baseball.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    private RecyclerView item_list;
    private PlayerAdapter itemAdapter;
    private RecyclerView.LayoutManager rLayoutManager;
    private List<Player> item_player;

    private View view_new_player;
    private EditText editText_playerName,editText_playerNum;
    private Spinner spinner_position;

    String playerName;
    int playerNum,playerPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        item_list = (RecyclerView) findViewById(R.id.player_list);

        item_player = new ArrayList<>();

        item_player.add(new Player(35, "弘承", 8));
        item_player.add(new Player(16, "YURU", 10));
        item_player.add(new Player(56, "童", 7));



        // 執行RecyclerView元件的設定
        item_list.setHasFixedSize(true);
        // 建立與設定RecyclerView元件的配置元件
        rLayoutManager = new LinearLayoutManager(this);
        item_list.setLayoutManager(rLayoutManager);

        // 建立RecyclerView元件的資料來源物件
        itemAdapter = new PlayerAdapter(item_player, this) {
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



    public void clickAddPlayer(View view) {
        view_new_player = LayoutInflater.from(PlayerActivity.this).inflate(R.layout.dialog_new_player, null);
        view_new_player.setPadding(3,0,3,0);

        editText_playerName = view_new_player.findViewById(R.id.editText_playerName);
        editText_playerNum = view_new_player.findViewById(R.id.editText_playerNum);
        spinner_position = view_new_player.findViewById(R.id.spinner_position);

        AlertDialog.Builder dialog_addPlayer = new AlertDialog.Builder(this);

        dialog_addPlayer.setView(view_new_player);ArrayAdapter adapter_position = new ArrayAdapter(dialog_addPlayer.getContext(),android.R.layout.simple_spinner_item,new String[]{"","P","C","1B","2B","3B","SS","LF","CF","RF"});
        adapter_position.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_position.setAdapter(adapter_position);

        dialog_addPlayer.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!editText_playerNum.getText().toString().equals("")) {
                    playerName = editText_playerName.getText().toString();
                    playerNum = Integer.valueOf(editText_playerNum.getText().toString());
                    playerPosition = (int) spinner_position.getSelectedItemId();

                    itemAdapter.add(new Player(playerNum,playerName, playerPosition));  // 新增一個物件項目
                    item_list.scrollToPosition(item_player.size() - 1);  // 控制列表元件移到最後一個項目

                    Toast.makeText(getApplicationContext(), "ADD: "+playerName+" "+playerNum+" "+playerPosition, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "請填入背號!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog_addPlayer.show();

    }


}
