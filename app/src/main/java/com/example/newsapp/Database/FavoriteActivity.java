package com.example.newsapp.Database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newsapp.Database.FavoriteDB;
import com.example.newsapp.FavoriteList;
import com.example.newsapp.R;

import java.util.List;
import java.util.jar.Attributes;

public class FavoriteActivity extends AppCompatActivity {

    EditText editText;
    Button button;

    @NonNull
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        editText = findViewById(R.id.dbET);
        button = findViewById(R.id.dbBtn);

        FavoriteDB favoriteDB = FavoriteDB.getInstance(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editText.getText().toString(); // to add the data from the user to db

                favoriteDB.favoriteDao().addName(
                        new FavoriteList(name)
                );

                List<FavoriteList> nameList =  favoriteDB.favoriteDao().getAllName(); // if u wanted to display it in the array list u can use typecasting

                for (int i=0;i<nameList.size();i++) {
                    Log.d("Data","Name" + nameList.get(i).getName());
                }
            }
        });


    }
}