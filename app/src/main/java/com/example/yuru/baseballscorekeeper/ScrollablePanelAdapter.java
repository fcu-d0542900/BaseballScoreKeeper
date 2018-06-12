package com.example.yuru.baseballscorekeeper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YURU on 2018/6/11.
 */

public class ScrollablePanelAdapter extends PanelAdapter {

    private List<Player> item_player = new ArrayList<>();
    private List<BoardNumInfo> boardNumInfoList = new ArrayList<>();
    private List<List<OrderInfo>> ordersList =new ArrayList<>();

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

    public void setPlayerInfoView(int pos, PlayerInfoViewHolder viewHolder) {
        Player playerInfo = item_player.get(pos - 1);
        if (playerInfo != null && pos > 0) {
            viewHolder.text_playerPosition.setText(playerInfo.getPosition());
            viewHolder.text_playerName.setText(playerInfo.getName());
            if(playerInfo.getId() == -1) {
                viewHolder.text_playerNum.setText("");
            }
            else {
                viewHolder.text_playerNum.setText(Long.valueOf(playerInfo.getId()).toString());
            }
            viewHolder.text_batOrder.setText(Integer.valueOf(pos).toString());
        }
    }

    private void setOrderView(final int row, final int column, OrderViewHolder viewHolder) {
        final OrderInfo orderInfo = ordersList.get(row - 1).get(column - 1);
        if (orderInfo != null) {
            viewHolder.getScoreView.bringToFront();

            //中間菱形區域
            viewHolder.getScoreView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

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



}


