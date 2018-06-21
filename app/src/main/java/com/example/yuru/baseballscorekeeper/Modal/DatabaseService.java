package com.example.yuru.baseballscorekeeper.Modal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

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
        try {
            input = new ObjectInputStream(context.openFileInput(filename));
            Log.d("database","file read success "+ input.readLine());
            database = (Database) input.readObject();
            input.close();
            Log.d("database","databse read success "+ database.toString());
        } catch (IOException | ClassNotFoundException e) {
            database = new Database();
            e.printStackTrace();
        }
    }

    public void write(){
        ObjectOutput out = null;

        try {
            out = new ObjectOutputStream(context.openFileOutput(filename, Context.MODE_PRIVATE));
            out.writeObject(database);
            out.close();
            Gson gson = new Gson();
            String json = gson.toJson(database);
            Log.v("json",json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
