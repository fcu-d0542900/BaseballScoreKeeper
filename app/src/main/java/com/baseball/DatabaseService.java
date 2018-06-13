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
    private DB database;
    Context context;

    DatabaseService(){
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
    public void read(){
        ObjectInputStream input;
        boolean exist = true;
        try {
            input = new ObjectInputStream(context.openFileInput(filename));
            database = (DB) input.readObject();
            Log.v("serialization","read databse");
            input.close();
            exist = false;
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
            database = new DB();
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
