package com.example.newsapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.newsapp.FavoriteList;

@Database(entities = {FavoriteList.class},version = 1,exportSchema = false)
public abstract class FavoriteDB extends RoomDatabase {
    private static final String DB_NAME = "userdb";

    public static FavoriteDB INSTANCE;

    public static synchronized FavoriteDB getInstance(Context context){
        if(INSTANCE == null) {  // if the instance is null it will create it and run
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FavoriteDB.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public abstract FavoriteDoa favoriteDao();
}
