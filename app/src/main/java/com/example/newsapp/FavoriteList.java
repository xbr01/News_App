package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "name")
public class FavoriteList {
    //@NonNull
    //@SerializedName("id")
    //@Expose
    //@ColumnInfo
    //private String id;

    @NonNull
    @PrimaryKey
    private String name;

    /*public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/

    public FavoriteList(String name) {
        this.name = name;
    }


    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}

// model class for JSON

