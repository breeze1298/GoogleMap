package com.breeze.googlemapjava.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FormEntity.class}, version = 1)
public abstract class DatabaseClient extends RoomDatabase {
    public static final String NAME = "MyDataBase";
    public abstract FormDao formDao();

    private static DatabaseClient noteDB;

    public static DatabaseClient getInstance(Context context) {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }
    private static DatabaseClient buildDatabaseInstance(Context context) {

        return Room.databaseBuilder(context,DatabaseClient.class,NAME).allowMainThreadQueries().build();
    }

    public void cleanUp(){
        noteDB = null;
    }
}