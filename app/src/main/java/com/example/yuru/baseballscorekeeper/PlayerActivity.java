package com.example.yuru.baseballscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    private RecyclerView item_list;
    private PlayerAdapter itemAdapter;
    private RecyclerView.LayoutManager rLayoutManager;

    private List<Player> item_player;

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
        // 決定新項目的編號
        int newId = item_player.size() + 1;
        // 建立新增項目物件
        Player player = new Player(newId, "New" + newId, 0);
        // 新增一個項目
        itemAdapter.add(player);
        // 控制列表元件移到最後一個項目
        item_list.scrollToPosition(item_player.size() - 1);
    }


}
