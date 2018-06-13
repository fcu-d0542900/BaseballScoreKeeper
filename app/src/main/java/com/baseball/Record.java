package com.baseball;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Record implements Serializable {
    private Team ally = new Team();
    private Team enemy = new Team();
    private Date date;

    Record() {
        date = Calendar.getInstance().getTime();
    }

    public Team getAlly() {
        return ally;
    }

    public Team getEnemy() {
        return enemy;
    }
}
