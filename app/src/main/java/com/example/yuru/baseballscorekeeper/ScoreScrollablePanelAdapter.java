package com.example.yuru.baseballscorekeeper;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YURU on 2018/6/11.
 * 上方記錄得分紀錄欄
 */

public class ScoreScrollablePanelAdapter extends PanelAdapter {

    private List<Team> item_team = new ArrayList<>();
    private List<BoardNumInfo> boardNumInfoList = new ArrayList<>();
    private List<List<ScoreBoardInfo>> scoresList =new ArrayList<>();

    private static final int TITLENAME_TYPE = 7;
    private static final int TEAMINFO_TYPE = 5;
    private static final int BOARDNUM_TYPE = 1;
    private static final int SCOREBOARD_TYPE = 6;

    @Override
    public int getRowCount() {
        return item_team.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return boardNumInfoList.size();
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {
        int viewType = getItemViewType(row, column);
        switch (viewType) {
            case BOARDNUM_TYPE:    //局數
                setBoardNumView(column, (BoardNumViewHolder) holder);
                break;
            case TEAMINFO_TYPE:   //隊伍名稱
                setTeamInfoView(row, (TeamInfoViewHolder) holder);
                break;
            case SCOREBOARD_TYPE:    //記分板
                setScoreView(row, column, (ScoreBoardViewHolder) holder);
                break;
            case TITLENAME_TYPE:     //隊伍名稱
                break;
            default:
                setScoreView(row, column, (ScoreBoardViewHolder) holder);
        }
    }

    public int getItemViewType(int row, int column) {
        if (column == 0 && row == 0) {
            return TITLENAME_TYPE;
        }
        if (column == 0) {
            return TEAMINFO_TYPE;
        }
        if (row == 0) {
            return BOARDNUM_TYPE;
        }
        return SCOREBOARD_TYPE;
    }

    @Override  //串接xml
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BOARDNUM_TYPE:
                return new BoardNumViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.score_board_num, parent, false));
            case TEAMINFO_TYPE:
                return new TeamInfoViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.score_team_name, parent, false));
            case SCOREBOARD_TYPE:
                return new ScoreBoardViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.score_board_main, parent, false));
            case TITLENAME_TYPE:
                return new TeamTitleViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.score_team_title, parent, false));
            default:
                break;
        }
        return new ScoreBoardViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.score_board_main, parent, false));

    }

    //局數
    private void setBoardNumView(int pos, BoardNumViewHolder viewHolder) {
        BoardNumInfo boardNumInfo = boardNumInfoList.get(pos - 1);
        if (boardNumInfo != null && pos > 0) {
            viewHolder.boaredTextView.setText(boardNumInfo.getBroadNum_symbol().toString());
        }
    }

    //隊伍
    public void setTeamInfoView(int pos, final TeamInfoViewHolder viewHolder) {
        final Team teamInfo = item_team.get(pos - 1);
        if (teamInfo != null && pos>0) {
            //設定資料
            viewHolder.text_teamName.setText(teamInfo.getTeamName());

            if(teamInfo.getTeamName() == "") {
                viewHolder.text_teamName.setText("未設隊伍");
            }
            else {
                viewHolder.text_teamName.setText(teamInfo.getTeamName());
            }

        }
    }

    //記分板
        private void setScoreView(final int row, final int column, final ScoreBoardViewHolder viewHolder) {
        final ScoreBoardInfo scoreInfo = scoresList.get(row - 1).get(column - 1);

    }

    //上方局數欄
    private static class BoardNumViewHolder extends RecyclerView.ViewHolder {
        public TextView boaredTextView;

        public BoardNumViewHolder(View itemView) {
            super(itemView);
            this.boaredTextView = (TextView) itemView.findViewById(R.id.text_Scoreboard);
        }

    }

    //隊伍欄
    private static class TeamInfoViewHolder extends RecyclerView.ViewHolder {
        public TextView text_teamName;

        public TeamInfoViewHolder(View view) {
            super(view);
            this.text_teamName = (TextView) view.findViewById(R.id.text_Scoreteam);
        }
    }

    //記分板
    private static class ScoreBoardViewHolder extends RecyclerView.ViewHolder {
        public TextView getScoreView;

        public View view;

        public ScoreBoardViewHolder(View view) {
            super(view);
            this.view = view;
            this.getScoreView = (TextView) view.findViewById(R.id.text_ScoreMain);

        }
    }

    //title欄
    private static class TeamTitleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public TeamTitleViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.text_teamTitle);
        }
    }


    public void setTeamInfoList(List<Team> item_team) {
        this.item_team = item_team;
    }


    public void setBoardNumInfoList(List<BoardNumInfo> boardNumInfoList) {
        this.boardNumInfoList = boardNumInfoList;
    }

    public void setScoreList(List<List<ScoreBoardInfo>> scoresList) {
        this.scoresList = scoresList;
    }



}


