package com.baseball;

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
    private String filename = "db.dat";
    private static DatabaseService instance;
    private Database database;
    Context context;

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
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
