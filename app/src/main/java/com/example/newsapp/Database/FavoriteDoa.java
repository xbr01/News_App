package com.example.newsapp.Database;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.newsapp.FavoriteList;

import java.util.List;
import java.util.jar.Attributes;

@Dao
public interface FavoriteDoa {
    @Insert
    void addName(FavoriteList favoriteList); // to insert to the query

    @NonNull
    @Query("select * from name")
    List<FavoriteList> getAllName(); // changed getfavdata()

    //@Query("SELECT EXISTS (SELECT 1 FROM favoritelist WHERE id=id)")
    //public int isFavorite(int id);

    @Update
    void updateName(FavoriteList favoriteList);

    @Delete
    void deleteName(FavoriteList favoriteList);
}


