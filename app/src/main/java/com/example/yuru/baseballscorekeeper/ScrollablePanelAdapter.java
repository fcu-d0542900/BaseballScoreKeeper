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

    private void setPlayerInfoView(int pos, PlayerInfoViewHolder viewHolder) {
        Player playerInfo = item_player.get(pos - 1);
        if (playerInfo != null && pos > 0) {
            viewHolder.text_playerPosition.setText(playerInfo.getPosition());
            viewHolder.text_playerName.setText(playerInfo.getName());
            viewHolder.text_batOrder.setText(Integer.valueOf(pos).toString());
        }
    }

    private void setOrderView(final int row, final int column, OrderViewHolder viewHolder) {
        final OrderInfo orderInfo = ordersList.get(row - 1).get(column - 1);
        if (orderInfo != null) {
            if (orderInfo.getStatus() == OrderInfo.Status.BLANK) {
                viewHolder.view.setBackgroundResource(R.drawable.bg_white_gray_stroke);
                viewHolder.nameTextView.setText("");
                viewHolder.statusTextView.setText("");
            } else if (orderInfo.getStatus() == OrderInfo.Status.CHECK_IN) {
                viewHolder.nameTextView.setText(orderInfo.isBegin() ? orderInfo.getGuestName() : "");
                viewHolder.statusTextView.setText(orderInfo.isBegin() ? "check in" : "");
                viewHolder.view.setBackgroundResource(orderInfo.isBegin() ? R.drawable.bg_white_gray_stroke : R.drawable.bg_white_gray_stroke);
            } else if (orderInfo.getStatus() == OrderInfo.Status.REVERSE) {
                viewHolder.nameTextView.setText(orderInfo.isBegin() ? orderInfo.getGuestName() : "");
                viewHolder.statusTextView.setText(orderInfo.isBegin() ? "reverse" : "");
                viewHolder.view.setBackgroundResource(orderInfo.isBegin() ? R.drawable.bg_white_gray_stroke : R.drawable.bg_white_gray_stroke);
            }
            if (orderInfo.getStatus() != OrderInfo.Status.BLANK) {
                viewHolder.itemView.setClickable(true);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (orderInfo.isBegin()) {
                            Toast.makeText(v.getContext(), "name:" + orderInfo.getGuestName(), Toast.LENGTH_SHORT).show();
                        } else {
                            int i = 2;
                            while (column - i >= 0 && ordersList.get(row - 1).get(column - i).getId() == orderInfo.getId()) {
                                i++;
                            }
                            final OrderInfo info = ordersList.get(row - 1).get(column - i + 1);
                            Toast.makeText(v.getContext(), "name:" + info.getGuestName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                viewHolder.itemView.setClickable(false);
            }
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
        public TextView text_batOrder;


        public PlayerInfoViewHolder(View view) {
            super(view);
            this.text_playerPosition = (TextView) view.findViewById(R.id.text_playerPosition);
            this.text_playerName = (TextView) view.findViewById(R.id.text_playerName);
            this.text_batOrder = (TextView) view.findViewById(R.id.text_batOrder);
        }
    }

    private static class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView statusTextView;
        public View view;

        public OrderViewHolder(View view) {
            super(view);
            this.view = view;
            this.statusTextView = (TextView) view.findViewById(R.id.status);
            this.nameTextView = (TextView) view.findViewById(R.id.guest_name);
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


