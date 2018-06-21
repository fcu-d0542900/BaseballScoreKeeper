package com.example.yuru.baseballscorekeeper.Modal;

import android.annotation.SuppressLint;
import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class DatabaseService {
    public static int CurrentRecord = 0;
    @SuppressLint("StaticFieldLeak")
    private static DatabaseService instance;
    private String filename = "db.dat";
    private Database database;
    private Context context;

    public static DatabaseService getInstance() {
        if (instance == null)
            instance = new DatabaseService();
        return instance;
    }

    public Database getDatabase() {
        return database;
    }

    public void setContext(Context context) {
        this.context = context;
        read();
    }

    private void read() {
        ObjectInputStream input;
        try {
            input = new ObjectInputStream(context.openFileInput(filename));
            database = (Database) input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException e) {
            database = new Database();
            e.printStackTrace();
        }
    }

    public void write() {
        ObjectOutput out;

        try {
            out = new ObjectOutputStream(context.openFileOutput(filename, Context.MODE_PRIVATE));
            out.writeObject(database);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
