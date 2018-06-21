package com.example.yuru.baseballscorekeeper.Adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuru.baseballscorekeeper.Modal.BoardNumInfo;
import com.example.yuru.baseballscorekeeper.Modal.RecordItem;
import com.example.yuru.baseballscorekeeper.Modal.RecordTeam;
import com.example.yuru.baseballscorekeeper.NewRecordActivity;
import com.example.yuru.baseballscorekeeper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YURU on 2018/6/11.
 * 上方記錄得分紀錄欄
 */

public class ScoreScrollablePanelAdapter extends PanelAdapter {

    private List<RecordTeam> recordTeam = new ArrayList<>();
    private List<BoardNumInfo> boardNumInfoList = new ArrayList<>();
    private NewRecordActivity activity;
    private RecordAdapter.OnItemClickListener mOnItemClickListener;
    private List<List<String>> scoreList;

    private static final int TITLENAME_TYPE = 7;
    private static final int TEAMINFO_TYPE = 5;
    private static final int BOARDNUM_TYPE = 1;
    private static final int SCOREBOARD_TYPE = 6;


    public void setOnItemClickListener(RecordAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public ScoreScrollablePanelAdapter(NewRecordActivity activity) {
        super();
        this.activity = activity;
        if (activity.currentRecord != null){
            recordTeam.clear();
            recordTeam.add(activity.currentRecord.getAwayTeam());
            recordTeam.add(activity.currentRecord.getHomeTeam());
        }
        boardNumInfoList.clear();
        for (int i=0;i<9+2;i++){
            boardNumInfoList.add(new BoardNumInfo(i));
        }
        scoreList = activity.getUpdateScore();
    }

    public void updateData() {
        scoreList = activity.getUpdateScore();
        activity.score_scrollable_panel.notifyDataSetChanged();
    }


    @Override
    public int getRowCount() {
        return recordTeam.size() + 1;
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
    @SuppressLint("SetTextI18n")
    private void setBoardNumView(int pos, BoardNumViewHolder viewHolder) {
        BoardNumInfo boardNumInfo = boardNumInfoList.get(pos - 1);
        if (boardNumInfo != null && pos > 0) {
            if(pos == boardNumInfoList.size()-1) {
                viewHolder.boaredTextView.setText("R");
            }
            else {
                viewHolder.boaredTextView.setText(Integer.valueOf(boardNumInfo.getBroadNum_num()).toString());
            }

        }
    }

    //隊伍
    private void setTeamInfoView(int pos, final TeamInfoViewHolder viewHolder) {
        final RecordTeam recordTeamInfo = recordTeam.get(pos - 1);
        if (recordTeamInfo != null && pos > 0) {
            //設定資料
            viewHolder.text_teamName.setText(recordTeamInfo.getTeamName());

            //通过为条目设置点击事件触发回调
            if (pos == 1) {    //點擊away
                if (mOnItemClickListener != null) {
                    viewHolder.text_teamName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mOnItemClickListener.onItemClick(view, 1);
                        }
                    });
                }

            }
            else if (pos == 2) {  //點擊home
                if (mOnItemClickListener != null) {
                    viewHolder.text_teamName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mOnItemClickListener.onItemClick(view, 2);
                        }
                    });
                }
            }
        }
    }


    //記分板
    private void setScoreView(final int row, final int column, final ScoreBoardViewHolder viewHolder) {
        viewHolder.scoreView.setText(scoreList.get(row-1).get(column-1));
    }

    //上方局數欄
    private static class BoardNumViewHolder extends RecyclerView.ViewHolder {
        TextView boaredTextView;

        BoardNumViewHolder(View itemView) {
            super(itemView);
            this.boaredTextView = itemView.findViewById(R.id.text_Scoreboard);
        }

    }

    //隊伍欄
    private static class TeamInfoViewHolder extends RecyclerView.ViewHolder {
        public TextView text_teamName;

        TeamInfoViewHolder(View view) {
            super(view);
            this.text_teamName = view.findViewById(R.id.text_Scoreteam);
        }
    }

    //記分板
    private static class ScoreBoardViewHolder extends RecyclerView.ViewHolder {
        TextView scoreView;

        public View view;

        ScoreBoardViewHolder(View view) {
            super(view);
            this.view = view;
            this.scoreView = view.findViewById(R.id.text_ScoreMain);

        }
    }

    //title欄
    private static class TeamTitleViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        TeamTitleViewHolder(View view) {
            super(view);
            this.titleTextView = view.findViewById(R.id.text_teamTitle);
        }
    }
}


