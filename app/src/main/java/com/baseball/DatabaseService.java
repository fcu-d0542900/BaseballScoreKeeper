package com.baseball;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class DatabaseService {
    public static int CurrentRecord = 0;
    private String filename = "db.dat";
    @SuppressLint("StaticFieldLeak")
    private static DatabaseService instance;
    private Database database;
    private Context context;

    public Database getDatabase() {
        return database;
    }

    public void setContext(Context context){
        this.context = context;
        read();
    }

    public static DatabaseService getInstance(){
        if(instance == null)
            instance = new DatabaseService();
        return instance;
    }

    private void read(){
        ObjectInputStream input;
        boolean exist = true;
        try {
            input = new ObjectInputStream(context.openFileInput(filename));
            Log.d("database","file read success "+ input.readLine());
            database = (Database) input.readObject();
            input.close();
            exist = false;
            Log.d("database","databse read success "+ database.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(exist)
            database = new Database();
    }

    public void write(){
        ObjectOutput out = null;

        try {
            out = new ObjectOutputStream(context.openFileOutput(filename, Context.MODE_PRIVATE));
            out.writeObject(database);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
