package com.example.yuru.baseballscorekeeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baseball.BoardNumInfo;
import com.baseball.OrderInfo;
import com.baseball.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YURU on 2018/6/11.
 */

public class ScrollablePanelAdapter extends PanelAdapter {

    private NewRecordActivity newRecordActivity ;

    private List<Player> item_player = new ArrayList<>();
    private List<BoardNumInfo> boardNumInfoList = new ArrayList<>();
    private List<List<OrderInfo>> ordersList =new ArrayList<>();

    private EditText editText_playerName,editText_playerNum;
    private Spinner spinner_position;
    private View view_set_player;

    private TextView choice1;
    private String playerName;
    private int playerNum,playerPosition;

    Dialog basetwothree = new Dialog();

    final String[] center_choice = new String[]{"得分/出局", "安打","替換守備","替換打者","結束半局"};
    final String[] hits_choice = new String[]{"一壘安打", "二壘安打","三壘安打","全壘打"};

    private static final int TEAMNAME_TYPE = 4;
    private static final int PLAYERINFO_TYPE = 0;
    private static final int BOARDNUM_TYPE = 1;
    private static final int ORDER_TYPE = 2;

    @Override
    public int getRowCount() {
        return item_player.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return boardNumInfoList.size();
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {
        int viewType = getItemViewType(row, column);
        switch (viewType) {
            case BOARDNUM_TYPE:
                setBoardNumView(column, (BoardNumViewHolder) holder);
                break;
            case PLAYERINFO_TYPE:
                setPlayerInfoView(row, (PlayerInfoViewHolder) holder);
                break;
            case ORDER_TYPE:
                setOrderView(row, column, (OrderViewHolder) holder);
                break;
            case TEAMNAME_TYPE:
                break;
            default:
                setOrderView(row, column, (OrderViewHolder) holder);
        }
    }

    public int getItemViewType(int row, int column) {
        if (column == 0 && row == 0) {
            return TEAMNAME_TYPE;
        }
        if (column == 0) {
            return PLAYERINFO_TYPE;
        }
        if (row == 0) {
            return BOARDNUM_TYPE;
        }
        return ORDER_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BOARDNUM_TYPE:
                return new BoardNumViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_board_num, parent, false));

            case PLAYERINFO_TYPE:
                return new PlayerInfoViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_player_info, parent, false));
            case ORDER_TYPE:
                return new OrderViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_order_info, parent, false));
            case TEAMNAME_TYPE:
                return new TeamNameViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_team_name, parent, false));

            default:
                break;
        }
        return new OrderViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_order_info, parent, false));

    }

    private void setBoardNumView(int pos, BoardNumViewHolder viewHolder) {
        BoardNumInfo boardNumInfo = boardNumInfoList.get(pos - 1);
        if (boardNumInfo != null && pos > 0) {
            viewHolder.dateTextView.setText(boardNumInfo.getBroadNum_symbol().toString());
        }
    }

    public void setPlayerInfoView(int pos, final PlayerInfoViewHolder viewHolder) {
        final Player playerInfo = item_player.get(pos - 1);
        viewHolder.text_batOrder.setText(Integer.valueOf(pos).toString());


        if (playerInfo != null && pos>0) {

            //設定資料
            viewHolder.text_playerPosition.setText(playerInfo.getPosition());
            viewHolder.text_playerName.setText(playerInfo.getName());
            if(playerInfo.getId() == -1) {
                viewHolder.text_playerNum.setText("");
            }
            else {
                viewHolder.text_playerNum.setText(Long.valueOf(playerInfo.getId()).toString());
            }

            //更改球員
            viewHolder.text_playerName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    view_set_player = LayoutInflater.from(newRecordActivity).inflate(R.layout.dialog_new_player, null);
                    view_set_player.setPadding(3,0,3,0);
                    ImageView img = view_set_player.findViewById(R.id.image_title_newPlayer);
                    img.setVisibility(View.GONE);

                    editText_playerName = view_set_player.findViewById(R.id.editText_playerName);
                    editText_playerNum = view_set_player.findViewById(R.id.editText_playerNum);
                    spinner_position = view_set_player.findViewById(R.id.spinner_position);

                    AlertDialog.Builder dialog_setPlayer = new AlertDialog.Builder(newRecordActivity);
                    dialog_setPlayer.setView(view_set_player);

                    ArrayAdapter adapter_position = new ArrayAdapter(dialog_setPlayer.getContext(),android.R.layout.simple_spinner_item,new String[]{"","P","C","1B","2B","3B","SS","LF","CF","RF"});
                    adapter_position.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_position.setAdapter(adapter_position);

                    dialog_setPlayer.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(!editText_playerNum.getText().toString().equals("")) {
                                playerName = editText_playerName.getText().toString();
                                playerNum = Integer.valueOf(editText_playerNum.getText().toString());
                                playerPosition = (int) spinner_position.getSelectedItemId();

                                playerInfo.setName(playerName);
                                playerInfo.setId(playerNum);
                                playerInfo.setPosition(playerPosition);
                                viewHolder.text_playerName.setText(playerName);
                                viewHolder.text_playerNum.setText(Integer.valueOf(playerNum).toString());
                                viewHolder.text_playerPosition.setText(playerInfo.getPosition());

                                Toast.makeText(newRecordActivity.getApplicationContext(), "SET", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(newRecordActivity.getApplicationContext(), "請填入背號!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    dialog_setPlayer.show();

                }
            });


        }
    }

    private void setOrderView(final int row, final int column, final OrderViewHolder viewHolder) {
        final OrderInfo orderInfo = ordersList.get(row - 1).get(column - 1);
        if (orderInfo != null) {
            viewHolder.getScoreView.bringToFront();

            //中間菱形區域
            viewHolder.getScoreView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(newRecordActivity)
                            .setItems(center_choice, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String name = center_choice[which];
                                    Toast.makeText(newRecordActivity.getApplicationContext(), name, Toast.LENGTH_SHORT).show();

                                    switch (which) {
                                        //點擊得分/出局
                                        case 0:
                                            AlertDialog.Builder center_choice1 = new AlertDialog.Builder(newRecordActivity);
                                            View view_center_choice1 = View.inflate(newRecordActivity, R.layout.record_center_dialog, null);      //自訂dialog布局
                                            center_choice1.setView(view_center_choice1);   //設置view
                                            final AlertDialog center_dialog = center_choice1.create();    //根據builder設置好的一系列數據, 来建構一個dialog
                                            //點擊得分
                                            view_center_choice1.findViewById(R.id.click_run).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(newRecordActivity, "得分", Toast.LENGTH_SHORT).show();
                                                    center_dialog.dismiss();
                                                }
                                            });
                                            //點擊一出局
                                            view_center_choice1.findViewById(R.id.click_out1).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(newRecordActivity, "一出局", Toast.LENGTH_SHORT).show();
                                                    center_dialog.dismiss();
                                                }
                                            });
                                            //點擊二出局
                                            view_center_choice1.findViewById(R.id.click_out2).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(newRecordActivity, "二出局", Toast.LENGTH_SHORT).show();
                                                    center_dialog.dismiss();
                                                }
                                            });
                                            //點擊三出局
                                            view_center_choice1.findViewById(R.id.click_out3).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(newRecordActivity, "三出局", Toast.LENGTH_SHORT).show();
                                                    center_dialog.dismiss();
                                                }
                                            });
                                            //點擊殘壘
                                            view_center_choice1.findViewById(R.id.click_left).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(newRecordActivity, "殘壘", Toast.LENGTH_SHORT).show();
                                                    center_dialog.dismiss();
                                                }
                                            });
                                            center_dialog.show();
                                            break;

                                        //點擊安打
                                        case 1:
                                            new AlertDialog.Builder(newRecordActivity)
                                                    .setItems(hits_choice, new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            String name_h = hits_choice[which];
                                                            switch (which) {
                                                                //點擊一壘安打
                                                                case 0:
                                                                    Toast.makeText(newRecordActivity.getApplicationContext(), name_h, Toast.LENGTH_SHORT).show();
                                                                    break;

                                                                //點擊一壘安打
                                                                case 1:
                                                                    Toast.makeText(newRecordActivity.getApplicationContext(), name_h, Toast.LENGTH_SHORT).show();
                                                                    break;

                                                                //點擊一壘安打
                                                                case 2:
                                                                    Toast.makeText(newRecordActivity.getApplicationContext(), name_h, Toast.LENGTH_SHORT).show();
                                                                    break;

                                                                //點擊一壘安打
                                                                case 3:
                                                                    Toast.makeText(newRecordActivity.getApplicationContext(), name_h, Toast.LENGTH_SHORT).show();
                                                                    break;

                                                                default:
                                                                    break;
                                                            }
                                                        }
                                                    })
                                                    .show();
                                            break;

                                        //點擊替換守備
                                        case 2:
                                            Toast.makeText(newRecordActivity.getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                            break;

                                        //點擊替換打者
                                        case 3:
                                            Toast.makeText(newRecordActivity.getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                            break;

                                        //點擊結束半局
                                        case 4:
                                            Toast.makeText(newRecordActivity.getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                            break;
                                        default:
                                            break;
                                    }


                                }
                            })
                            .show();
                    Toast.makeText(v.getContext(), "得分區域" +orderInfo.getGuestName(), Toast.LENGTH_SHORT).show();

                }
            });

            //一壘
            viewHolder.getFirstView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), "一壘" , Toast.LENGTH_SHORT).show();

                }
            });

            //二壘
            viewHolder.getSecondView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    basetwothree.setNewRecordActivity(newRecordActivity);
                    basetwothree.getTwoBaseDialog(new String[]{"推進","進壘",});
                    Toast.makeText(v.getContext(), "二壘" , Toast.LENGTH_SHORT).show();

                }
            });

            //三壘
            viewHolder.getThirdView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), "三壘" , Toast.LENGTH_SHORT).show();

                }
            });

            //本壘
            viewHolder.getHomeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), "本壘" , Toast.LENGTH_SHORT).show();

                }
            });

            //計算好壞球
            viewHolder.getBallView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), "好壞球" , Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    private static class BoardNumViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTextView;

        public BoardNumViewHolder(View itemView) {
            super(itemView);
            this.dateTextView = (TextView) itemView.findViewById(R.id.text_boardNum);
        }

    }

    private static class PlayerInfoViewHolder extends RecyclerView.ViewHolder {
        public TextView text_playerPosition;
        public TextView text_playerName;
        public TextView text_playerNum;
        public TextView text_batOrder;


        public PlayerInfoViewHolder(View view) {
            super(view);

            this.text_playerPosition = (TextView) view.findViewById(R.id.text_playerPosition);
            this.text_playerName = (TextView) view.findViewById(R.id.text_playerName);
            this.text_playerNum = (TextView) view.findViewById(R.id.text_playerNum);
            this.text_batOrder = (TextView) view.findViewById(R.id.text_batOrder);
        }
    }

    private static class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView getScoreView;
        public TextView getFirstView;
        public TextView getHomeView;
        public TextView getSecondView;
        public TextView getThirdView;
        public TextView getBallView;

        public View view;

        public OrderViewHolder(View view) {
            super(view);
            this.view = view;
            this.getScoreView = (TextView) view.findViewById(R.id.centerView);
            this.getFirstView = (TextView) view.findViewById(R.id.firstView);
            this.getHomeView = (TextView) view.findViewById(R.id.homeView);
            this.getSecondView = (TextView) view.findViewById(R.id.secondView);
            this.getThirdView = (TextView) view.findViewById(R.id.thirdView);
            this.getBallView= (TextView) view.findViewById(R.id.ballView);
            this.getScoreView.bringToFront();

        }
    }

    private static class TeamNameViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public TeamNameViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.title);
        }
    }

    public void setPlayerInfoList(List<Player> item_player) {
        this.item_player = item_player;
    }

    public void setBoardNumInfoList(List<BoardNumInfo> boardNumInfoList) {
        this.boardNumInfoList = boardNumInfoList;
    }

    public void setOrdersList(List<List<OrderInfo>> ordersList) {
        this.ordersList = ordersList;
    }

    public void setNewRecordActivity(NewRecordActivity newRecordActivity) {
        this.newRecordActivity=newRecordActivity;
    }





}


