package com.example.yuru.baseballscorekeeper.Modal;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Created by YURU on 2018/6/8.
 */

public class RecordTeam implements Serializable {
    private String teamName = "";
    private int currentRound = 1;
    private List<Player> teamMember = new ArrayList<>();
    private List<RecordItem> recordItems;

    RecordTeam(String name) {
        if (name != null && !name.equals(""))
            setTeamName(name);
        else
            setTeamName(DatabaseService.getInstance().getDatabase().getTeamName());
        if (Objects.requireNonNull(name).equals(DatabaseService.getInstance().getDatabase().getTeamName())) {
            teamMember = new ArrayList<>(DatabaseService.getInstance().getDatabase().getTeamMember());
        }
        while (teamMember.size() < 9) {
            teamMember.add(new Player());
        }
        recordItems = new ArrayList<>();
    }

    public RecordItem getRecordItems(int row, int column) {
        if (recordItems.size() > 0)
            for (RecordItem item :
                    recordItems) {
                if (row == item.row && column == item.column)
                    return item;
                else if (item.row >= row && column < item.column)
                    break;

            }
        return new RecordItem(teamMember.get(row), currentRound, row, column);
    }

    public void addRecordItems(RecordItem recordItems) {
        this.recordItems.add(recordItems);
        Collections.sort(this.recordItems, new RecordItemComparator());
    }

    public void nextRound() {
        currentRound = currentRound + 1;
    }

    public String getRoundText(int round) {
        String text = "";
        switch (round - 1) {
            case 0:
                text = "一";
                break;
            case 1:
                text = "二";
                break;
            case 2:
                text = "三";
                break;
            case 3:
                text = "四";
                break;
            case 4:
                text = "五";
                break;
            case 5:
                text = "六";
                break;
            case 6:
                text = "七";
                break;
            case 7:
                text = "八";
                break;
            case 8:
                text = "九";
                break;
            case 9:
                text = "十";
                break;
            case 10:
                text = "十一";
                break;
            case 11:
                text = "十二";
                break;
            case 12:
                text = "十三";
                break;
            case 13:
                text = "十四";
                break;
            case 14:
                text = "十五";
                break;
        }
        return text;
    }

    public String getTeamName() {
        return teamName;
    }

    private void setTeamName(String name) {
        this.teamName = name;
    }

    public List<Player> getTeamMember() {
        return this.teamMember;
    }

    public String getScore(int i) {
        Log.d("ahkui",i+"");
        int score = 0;
        if (recordItems != null)
            for (RecordItem item :
                    recordItems) {
                if (item.getRound() == i+1 && item.isGetScore()) score++;
            }
        return "" + score;
    }

    public int getLastRecordItemsColumn() {
        if (recordItems.size() > 0)
            return recordItems.get(recordItems.size() - 1).column;
        return 0;
    }

    public int getRecordItemsPositionRound(int position) {
        if (recordItems.size() == 0 && position == 1)
            return 0;
        for (RecordItem item : recordItems) {
            if (item.column == position - 1)
                return item.getRound();
        }
        return -1;
    }

    public enum Faction {
        away,
        home
    }
}

class RecordItemComparator implements Comparator<RecordItem> {
    public int compare(RecordItem lhs, RecordItem rhs) {
        int value1 = Integer.compare(lhs.column, rhs.column);
        if (value1 == 0)
            return Integer.compare(lhs.row, rhs.row);
        return value1;
    }
}